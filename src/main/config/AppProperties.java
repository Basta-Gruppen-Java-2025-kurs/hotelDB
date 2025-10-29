package main.config;

import main.exception.PropertiesException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

import static java.lang.String.format;
import static java.lang.Thread.currentThread;
import static java.nio.file.Files.newInputStream;
import static java.nio.file.StandardOpenOption.READ;
import static java.util.Objects.requireNonNull;

public record AppProperties(Properties properties) {

    private static final String PROPERTIES_PATH = "./application.properties";

    public static AppProperties loadFromClasspath() {
        Properties properties = new Properties();

        final String path = requireNonNull(currentThread().getContextClassLoader()
                .getResource(PROPERTIES_PATH))
                .getPath();
        try {
            properties.load(newInputStream(Path.of(path), READ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new AppProperties(properties);
    }

    public String getProperty(String key) {
        final String value = properties.getProperty(key);
        if (value == null)
            throw new PropertiesException(format("Property '%s' not found", key));

        return value;
    }
}