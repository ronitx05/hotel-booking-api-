package org.ncu.hotel_booking.repositories;

import org.ncu.hotel_booking.entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ReservationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Reservation> findAll() {
        String sql = "SELECT * FROM reservations";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Reservation.class));
    }

    public Reservation findById(Long id) {
        String sql = "SELECT * FROM reservations WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Reservation.class));
    }

    public void save(Reservation reservation) {
        String sql = "INSERT INTO reservations (room_id, customer_name, check_in_date, check_out_date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, reservation.getRoomId(), reservation.getCustomerName(), reservation.getCheckInDate(), reservation.getCheckOutDate());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM reservations WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public boolean hasOverlappingReservations(Long roomId, LocalDate checkInDate, LocalDate checkOutDate) {
        String sql = "SELECT COUNT(*) FROM reservations WHERE room_id = ? AND (check_in_date < ? AND check_out_date > ?)";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{roomId, checkOutDate, checkInDate}, Integer.class);
        return count != null && count > 0;
    }
}