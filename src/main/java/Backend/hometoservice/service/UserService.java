package Backend.hometoservice.service;

import Backend.hometoservice.dto.UserDto;
import Backend.hometoservice.dto.detailedUserDto.DetailedUserDto;
import Backend.hometoservice.dto.detailedUserDto.DetailedUserResponseDto;
import Backend.hometoservice.model.User;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(UserDto userDto);
   // User updateUserEmail(Integer id, String email);
    User updateUserData(Integer id, UserDto userDto);
    Optional<User> getUserById(Integer userId);
    List<User> getAllUsers();
    DetailedUserResponseDto getUserDetails(Integer userId) throws NotFoundException;
   // UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
