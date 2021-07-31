package uz.pdp.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hotel.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Integer> {
}
