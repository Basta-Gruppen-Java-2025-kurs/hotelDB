package main;

import main.config.*;
import main.dao.RoomDAO;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        AppProperties properties = AppProperties.loadFromClasspath();
        DatabaseProperties db = DatabasePropertiesLoader.load(properties);
        DataSource dataSource = DataSourceProvider.create(db);
        Repository repository = new Repository(dataSource);

        RoomDAO roomDAO = new RoomDAO(repository);

        System.out.println(roomDAO.findById(1).get());
    }
}