package org.ncu.hotel_booking.services;

import org.ncu.hotel_booking.entities.Room;
import java.util.List;

public interface RoomService {
    List<Room> findAll();
    Room findById(Long id);
    void save(Room room);
    void update(Room room);
    void deleteById(Long id);
    List<Room> findAllSortedByPrice();
    List<Room> findAllPaginated(int page, int size);
}