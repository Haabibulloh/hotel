package uz.pdp.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotel.entity.Hotel;
import uz.pdp.hotel.repository.HotelRepository;

import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;
    @GetMapping
    public Page<Hotel> getMethod(@RequestParam int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Hotel> hotelPage = hotelRepository.findAll(pageable);
        return hotelPage;
    }
    @PostMapping
    public  String addMethod(@RequestBody Hotel hotel){
        Hotel newHotel=new Hotel();
        newHotel.setName(hotel.getName());
        hotelRepository.save(newHotel);
        return "Hotel saved";
    }
    @DeleteMapping("{id}")
    public String deleteMethod(@PathVariable Integer id){
        try {
            hotelRepository.deleteById(id);
            return "Hotel deleted";
        } catch (Exception e){
            return "Error in deleting";
        }
    }
    @PutMapping("{id}")
    public String updateMethod(@RequestBody Hotel hotel, @PathVariable Integer id){
        Optional<Hotel> optionalHotel =hotelRepository.findById(id);
        if (!optionalHotel.isPresent()){
            return "Doesn't exist";
        }
        Hotel editingHotel = optionalHotel.get();
        editingHotel.setName(hotel.getName());
        hotelRepository.save(editingHotel);
        return "Hotel updated";
    }
}
