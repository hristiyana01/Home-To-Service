package Backend.hometoservice.dto;

import Backend.hometoservice.model.Role;
import Backend.hometoservice.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponseDto {
    private String token;
    private String refreshToken;
    private User user;
    private Role roleType;
}