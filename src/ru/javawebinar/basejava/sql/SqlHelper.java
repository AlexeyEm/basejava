package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.storage.AbstractStorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public <T> T executeStatement(String statement, PreparatorStatement preparator) {
        final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(statement)) {
            T t = (T) preparator.prepare(ps);
            return t;
        } catch (SQLException e) {
            if (e.getSQLState().equalsIgnoreCase("23505")) {
                LOG.warning("Resume already exist");
                throw new ExistStorageException("");
            } else {
                throw new StorageException(e.getSQLState());
            }
        }
    }

    public interface PreparatorStatement<T> {
        T prepare(PreparedStatement ps) throws SQLException;
    }
}