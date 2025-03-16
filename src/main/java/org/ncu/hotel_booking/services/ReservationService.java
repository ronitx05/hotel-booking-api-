package org.ncu.hotel_booking.services;

import org.ncu.hotel_booking.entities.Reservation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReservationService {
    List<Reservation> findAll();
    Reservation findById(Long id);
    @Transactional
    void save(Reservation reservation);
    @Transactional
    void deleteById(Long id);
}