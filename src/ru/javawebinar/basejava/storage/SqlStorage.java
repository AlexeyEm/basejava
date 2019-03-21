package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class SqlStorage implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        sqlHelper.executeStatement("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public Resume get(String uuid) {
        return new Resume(uuid,
                sqlHelper.executeStatement("SELECT * FROM resume r WHERE r.uuid =?", (ps) -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    return rs.getString("full_name");
                })
        );
    }

    @Override
    public void update(Resume r) {
        int result = sqlHelper.executeStatement("UPDATE resume SET full_name = ? WHERE uuid = ?", ps -> {
            ps.setString(1, r.getFullName());
            ps.setString(2, r.getUuid());
            return ps.executeUpdate();
        });
        if (result == 2) {
            LOG.warning("Resume " + r.getUuid() + " not exist");
            throw new NotExistStorageException(r.getUuid());
        }
    }

    @Override
    public void save(Resume r) {
        sqlHelper.executeStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)", ps -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        boolean result = sqlHelper.executeStatement("DELETE FROM resume WHERE uuid =?", ps -> {
            ps.setString(1, uuid);
            return ps.execute();
        });
        if (!result) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        final Resume[] arrayResumes = new Resume[size()];
        sqlHelper.executeStatement("SELECT * FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                arrayResumes[i] = new Resume(rs.getString("uuid"), rs.getString("full_name"));
                i++;
            }
            return null;
        });
        return Arrays.asList(arrayResumes);
    }

    @Override
    public int size() {
        return sqlHelper.executeStatement("SELECT COUNT(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }
}