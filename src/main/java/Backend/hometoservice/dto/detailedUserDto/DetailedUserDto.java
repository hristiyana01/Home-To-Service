package Backend.hometoservice.dto.detailedUserDto;

import Backend.hometoservice.model.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailedUserDto {
    private String email;
    private String username;
    private String name;
    private String surname;
    private String phone;
    private String address;
    private String location;
    private String country;
    private String city;
    private String zip;
    private List<Review> reviews;
}
