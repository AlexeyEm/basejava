package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class SqlStorage implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.executeStatement("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.executeStatement("" +
                        "    SELECT * FROM resume r " +
                        " LEFT JOIN contact c " +
                        "        ON r.uuid = c.resume_uuid " +
                        "     WHERE r.uuid =? " +
                        " UNION ALL" +
                        "    SELECT '','',* FROM section" +
                        "     WHERE resume_uuid =?",
                ps -> {
                    ps.setString(1, uuid);
                    ps.setString(2, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume r = new Resume(uuid, rs.getString("full_name"));
                    do {
                        if (!rs.getString("uuid").equals("")) {
                            addContact(rs, r);
                        } else {
                            addSection(rs, r);
                        }
                    }
                    while (rs.next());
                    return r;
                });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                        ps.setString(1, r.getFullName());
                        ps.setString(2, r.getUuid());
                        if (ps.executeUpdate() == 0) {
                            LOG.warning("Resume " + r.getUuid() + " not exist");
                            throw new NotExistStorageException(r.getUuid());
                        }
                    }
                    deleteContacts(conn, r);
                    insertContacts(conn, r);
                    deleteSections(conn, r);
                    insertSections(conn, r);
                    return null;
                }
        );
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, r.getUuid());
                        ps.setString(2, r.getFullName());
                        ps.execute();
                    }
                    insertContacts(conn, r);
                    insertSections(conn, r);
                    return null;
                }
        );
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.executeStatement("DELETE FROM resume WHERE uuid =?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                LOG.warning("Resume " + uuid + " not exist");
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> map = new LinkedHashMap<>();
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume ORDER BY full_name, uuid")) {
                ResultSet rsResumes = ps.executeQuery();
                while (rsResumes.next()) {
                    String uuid = rsResumes.getString("uuid");
                    Resume resume = new Resume(uuid, rsResumes.getString("full_name"));
                    map.put(uuid, resume);
                }
            }
            try (PreparedStatement psContacts = conn.prepareStatement("SELECT * FROM contact ORDER BY resume_uuid")) {
                ResultSet rsContacts = psContacts.executeQuery();
                while (rsContacts.next()) {
                    String uuid = rsContacts.getString("resume_uuid");
                    Resume resume = map.get(uuid);
                    addContact(rsContacts, resume);
                    map.put(uuid, resume);
                }
            }
            try (PreparedStatement psSections = conn.prepareStatement("SELECT * FROM section ORDER BY resume_uuid")) {
                ResultSet rsSections = psSections.executeQuery();
                while (rsSections.next()) {
                    String uuid = rsSections.getString("resume_uuid");
                    Resume resume = map.get(uuid);
                    addSection(rsSections, resume);
                    map.put(uuid, resume);
                }
            }
            return new ArrayList<>(map.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.executeStatement("SELECT COUNT(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void insertContacts(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void addContact(ResultSet rs, Resume r) throws SQLException {
        if (rs.getString("resume_uuid") != null) {
            String value = rs.getString("value");
            ContactType type = ContactType.valueOf(rs.getString("type"));
            r.addContact(type, value);
        }
    }

    private void insertSections(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, AbstractSection> e : r.getSections().entrySet()) {
                String uuid = r.getUuid();
                String value = "";
                SectionType keyName = e.getKey();
                switch (keyName) {
                    case OBJECTIVE:
                    case PERSONAL:
                        value = e.getValue().toString();
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = ((ListSection) e.getValue()).getItems();
                        value = String.join("\n", list);
                        break;
                    default:
                        break;
                }
                ps.setString(1, uuid);
                ps.setString(2, keyName.name());
                ps.setString(3, value);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void addSection(ResultSet rs, Resume r) throws SQLException {
        String type = rs.getString("type");
        SectionType sectionType = SectionType.valueOf(type);
        String value = rs.getString("value");
        AbstractSection abstractSection = null;
        switch (type) {
            case "OBJECTIVE":
            case "PERSONAL":
                abstractSection = new TextSection(value);
                break;
            case "ACHIEVEMENT":
            case "QUALIFICATIONS":
                List<String> list = Arrays.asList(value.split("\n"));
                abstractSection = new ListSection(list);
                break;
            default:
                break;
        }
        r.addSection(sectionType, abstractSection);
    }

    private void deleteContacts(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid = ?")) {
            ps.setString(1, r.getUuid());
            ps.execute();
        }
    }

    private void deleteSections(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM section WHERE resume_uuid = ?")) {
            ps.setString(1, r.getUuid());
            ps.execute();
        }
    }
}