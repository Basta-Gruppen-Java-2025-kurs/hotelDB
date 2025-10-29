package main.config;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface StatementBinder {
    void bind(PreparedStatement ps) throws SQLException;
}