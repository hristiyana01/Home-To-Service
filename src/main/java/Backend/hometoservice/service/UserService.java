package Backend.hometoservice.service;

import Backend.hometoservice.dto.UserDto;
import Backend.hometoservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(UserDto userDto);
   // User updateUserEmail(Integer id, String email);
    User updateUserData(Integer id, UserDto userDto);
    Optional<User> getUserById(Integer userId);
    List<User> getAllUsers();

    List<User> findAll();
    User save(User user);

    Optional<User> findById(Integer id);
}
