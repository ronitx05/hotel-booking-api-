package org.ncu.hotel_booking.services;

import org.ncu.hotel_booking.entities.Room;
import org.ncu.hotel_booking.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public void save(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void update(Room room) {
        roomRepository.update(room);
    }

    @Override
    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public List<Room> findAllSortedByPrice() {
        return roomRepository.findAllSortedByPrice();
    }

    @Override
    public List<Room> findAllPaginated(int page, int size) {
        return roomRepository.findAllPaginated(page, size);
    }
}