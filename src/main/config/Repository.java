package main.config;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Arrays;
import java.util.Optional;

import static java.util.Optional.empty;

public record Repository(DataSource dataSource) {

    public <T> Optional<T> queryOne(String sql,
                                    StatementBinder binder,
                                    ResultSetMapper<T> mapper) throws SQLException {
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            binder.bind(ps);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return Optional.of(mapper.map(rs));

                return empty();
            }
        }
    }

    public void executeBatch(String... sql) throws SQLException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            try (Statement statement = connection.createStatement()) {
                for (String command : sql) {
                    statement.addBatch(command);
                }
                statement.executeBatch();

                int[] results = statement.executeBatch();
                System.out.println(Arrays.toString(results));
            }

            connection.commit();
        } catch (SQLException exception) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            throw exception;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}