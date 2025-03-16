package org.ncu.hotel_booking.services;

import org.ncu.hotel_booking.entities.Reservation;
import org.ncu.hotel_booking.entities.Room;
import org.ncu.hotel_booking.repositories.ReservationRepository;
import org.ncu.hotel_booking.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(Reservation reservation) {
        try {
            Room room = roomRepository.findById(reservation.getRoomId());
            if (room == null) {
                throw new RuntimeException("Room not found");
            }

            if (reservationRepository.hasOverlappingReservations(reservation.getRoomId(), reservation.getCheckInDate(), reservation.getCheckOutDate())) {
                throw new RuntimeException("Room is already reserved for the given dates");
            }

            reservationRepository.save(reservation);
        } catch (RuntimeException e) {
            System.err.println("Error creating reservation: " + e.getMessage());
            throw new RuntimeException("Failed to create reservation: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            Reservation reservation = reservationRepository.findById(id);
            if (reservation == null) {
                throw new RuntimeException("Reservation not found");
            }

            reservationRepository.deleteById(id);
        } catch (RuntimeException e) {
            System.err.println("Error deleting reservation: " + e.getMessage());
            throw new RuntimeException("Failed to delete reservation: " + e.getMessage());
        }
    }
}