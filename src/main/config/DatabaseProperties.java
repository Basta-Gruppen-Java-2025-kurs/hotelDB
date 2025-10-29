package main.config;

public record DatabaseProperties(
        String host,
        int port,
        String database,
        String user,
        String password) {
}