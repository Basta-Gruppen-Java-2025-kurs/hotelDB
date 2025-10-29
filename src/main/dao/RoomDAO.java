package main.dao;

import main.config.Repository;
import main.entity.Room;

import java.sql.SQLException;
import java.util.Optional;

public record RoomDAO(Repository repository) {

    public Optional<Room> findById(int id) throws SQLException {
        final String sql = "SELECT * FROM room WHERE room_id = ?";

        return repository.queryOne(
                sql,
                ps -> ps.setInt(1, id),
                rs -> new Room(
                        rs.getInt("room_id"),
                        rs.getString("type"),
                        rs.getDouble("price"),
                        rs.getInt("number"),
                        rs.getBoolean("available"))
        );
    }
}