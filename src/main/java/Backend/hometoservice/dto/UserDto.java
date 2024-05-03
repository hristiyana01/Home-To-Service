package Backend.hometoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String email;
    private String username;
    private String name;
    private String surname;
    private String password;
    private String phone;
    private String address;
    private String country;
    private String city;
    private String zip;
    private Integer userRoleId;
    private String location;
}
