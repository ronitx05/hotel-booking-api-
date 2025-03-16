package org.ncu.hotel_booking.repositories;

import org.ncu.hotel_booking.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Room> findAll() {
        String sql = "SELECT * FROM rooms";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Room.class));
    }

    public Room findById(Long id) {
        String sql = "SELECT * FROM rooms WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Room.class));
    }

    public void save(Room room) {
        String sql = "INSERT INTO rooms (room_number, type, price) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, room.getRoomNumber(), room.getType(), room.getPrice());
    }

    public void update(Room room) {
        String sql = "UPDATE rooms SET room_number = ?, type = ?, price = ? WHERE id = ?";
        jdbcTemplate.update(sql, room.getRoomNumber(), room.getType(), room.getPrice(), room.getId());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM rooms WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Room> findAllSortedByPrice() {
        String sql = "SELECT * FROM rooms ORDER BY price ASC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Room.class));
    }

    public List<Room> findAllPaginated(int page, int size) {
        int offset = page * size;
        String sql = "SELECT * FROM rooms LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new Object[]{size, offset}, new BeanPropertyRowMapper<>(Room.class));
    }
}