package main.config;

import static java.lang.Integer.parseInt;

public final class DatabasePropertiesLoader {

    public static DatabaseProperties load(AppProperties properties) {
        return new DatabaseProperties(
                properties.getProperty("db.server.name"),
                parseInt(properties.getProperty("db.port")),
                properties.getProperty("db.name"),
                properties.getProperty("db.user"),
                properties.getProperty("db.password")
        );
    }
}