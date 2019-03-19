package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.ConnectionFactory;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class SqlStorage implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    private final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        SqlHelper.executeStatement(connectionFactory, "DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public Resume get(String uuid) {
        final String[] fullName = new String[1];
        SqlHelper.executeStatement(connectionFactory, "SELECT * FROM resume r WHERE r.uuid =?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            fullName[0] = rs.getString("full_name");
        });
        return new Resume(uuid, fullName[0]);
    }

    @Override
    public void update(Resume r) {
        SqlHelper.executeStatement(connectionFactory, "UPDATE resume SET full_name = ? WHERE uuid = ?", ps -> {
            ps.setString(1, r.getFullName());
            ps.setString(2, r.getUuid());
            ps.execute();
        });
    }

    @Override
    public void save(Resume r) {
        getNotExistedSearchKey(r.getUuid());
        SqlHelper.executeStatement(connectionFactory, "INSERT INTO resume (uuid, full_name) VALUES (?,?)", ps -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
        });
    }

    @Override
    public void delete(String uuid) {
        getExistedSearchKey(uuid);
        SqlHelper.executeStatement(connectionFactory, "DELETE FROM resume WHERE uuid =?", ps -> {
            ps.setString(1, uuid);
            ps.execute();
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        final Resume[] arrayResumes = new Resume[size()];
        SqlHelper.executeStatement(connectionFactory, "SELECT * FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            for (int i = 0; rs.next(); i++) {
                arrayResumes[i] = new Resume(rs.getString("uuid"), rs.getString("full_name"));
            }
        });
        return Arrays.asList(arrayResumes);
    }

    @Override
    public int size() {
        final int[] result = new int[1];
        SqlHelper.executeStatement(connectionFactory, "SELECT COUNT(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result[0] = rs.getInt(1);
            }
        });
        return result[0];
    }

    private boolean isExist(String uuid) {
        final boolean[] result = new boolean[1];
        SqlHelper.executeStatement(connectionFactory, "SELECT * FROM resume r WHERE r.uuid =?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            result[0] = rs.next();
        });
        return result[0];
    }

    private void getExistedSearchKey(String uuid) {
        if (!isExist(uuid)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
    }

    private void getNotExistedSearchKey(String uuid) {
        if (isExist(uuid)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
    }
}