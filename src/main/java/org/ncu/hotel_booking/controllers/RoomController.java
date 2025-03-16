package org.ncu.hotel_booking.controllers;

import org.ncu.hotel_booking.entities.Room;
import org.ncu.hotel_booking.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public List<Room> findAll() {
        return roomService.findAll();
    }

    @GetMapping("/{id}")
    public Room findById(@PathVariable Long id) {
        return roomService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Room room) {
        roomService.save(room);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Room room) {
        room.setId(id);
        roomService.update(room);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        roomService.deleteById(id);
    }

    @GetMapping("/sorted")
    public List<Room> findAllSortedByPrice() {
        return roomService.findAllSortedByPrice();
    }

    @GetMapping("/paginated")
    public List<Room> findAllPaginated(@RequestParam int page, @RequestParam int size) {
        return roomService.findAllPaginated(page, size);
    }
}