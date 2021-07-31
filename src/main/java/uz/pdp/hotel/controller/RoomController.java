package uz.pdp.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotel.entity.Hotel;
import uz.pdp.hotel.entity.Room;
import uz.pdp.hotel.payload.RoomDto;
import uz.pdp.hotel.repository.HotelRepository;
import uz.pdp.hotel.repository.RoomRepository;

import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;
    @GetMapping("/{hotelId}")
    public Page<Room> getRoomsByHotelId(@PathVariable Integer hotelId, @RequestParam int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Room> roomPage = roomRepository.findAllByHotelId(hotelId, pageable);
        return roomPage;
    }
    @PostMapping
    public String addMethod(@RequestBody RoomDto roomDto){
        Room room=new Room();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent()){
            return "Doesn't exist";
        }
        room.setHotel(optionalHotel.get());
        roomRepository.save(room);
        return "Room saved!";
    }
    @DeleteMapping("{id}")
    public String deleteMethod(@PathVariable Integer id){
        try {
            roomRepository.deleteById(id);
            return "Room deleted";
        } catch (Exception e){
            return "Error in deleting";
        }
    }
    @PutMapping("{id}")
    public String updateMethod(@RequestBody RoomDto roomDto, @PathVariable Integer id){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()){
            Room room = optionalRoom.get();
            room.setNumber(roomDto.getNumber());
            room.setFloor(roomDto.getFloor());
            room.setSize(roomDto.getSize());
            Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
            if (!optionalHotel.isPresent()){
                return "Doesn't exist";
            }
            room.setHotel(optionalHotel.get());
            roomRepository.save(room);
            return "Room updated";
        }
        return "This room is not found";
    }
}
