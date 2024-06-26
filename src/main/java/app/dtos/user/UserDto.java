package app.dtos.user;

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
    private Integer userRoleId;
    private String phone;
    private String country;
    private String city;
    private boolean isAdmin;

}
