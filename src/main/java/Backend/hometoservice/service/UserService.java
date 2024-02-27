package Backend.hometoservice.service;

import Backend.hometoservice.dto.UserDto;
import Backend.hometoservice.model.User;

public interface UserService {
    User createUser(UserDto userDto);
}
