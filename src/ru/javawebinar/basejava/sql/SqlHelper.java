package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    public static PreparedStatement executeStatement(ConnectionFactory cf, String statement, PreparatorStatement preparator) {
        try (Connection conn = cf.getConnection();
             PreparedStatement ps = conn.prepareStatement(statement)) {
            preparator.prepare(ps);
            return ps;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public interface PreparatorStatement {
        void prepare(PreparedStatement ps) throws SQLException;
    }
}
