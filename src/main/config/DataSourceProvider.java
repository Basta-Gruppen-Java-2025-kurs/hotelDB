package main.config;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public final class DataSourceProvider {

    public static DataSource create(DatabaseProperties properties) throws SQLException {
        MysqlDataSource ds = new MysqlDataSource();

        ds.setServerName(properties.host());
        ds.setPort(properties.port());
        ds.setDatabaseName(properties.database());
        ds.setUser(properties.user());
        ds.setPassword(properties.password());
        ds.setContinueBatchOnError(false);
        ds.setServerTimezone("UTC");

        return ds;
    }
}