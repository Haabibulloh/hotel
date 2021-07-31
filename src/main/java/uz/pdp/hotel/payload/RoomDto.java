package uz.pdp.hotel.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDto {
    private Integer id;
    private String number;
    private Integer floor;
    private Integer size;
    private Integer hotelId;
}
