package app.services;

import app.dtos.AuthenticationRequest;
import app.dtos.AuthenticationResponse;
import app.dtos.user.CreateUserDto;
import app.dtos.user.DetailedUserResponseDto;
import app.dtos.user.UserDto;
import app.models.User;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(CreateUserDto userDto);

    User updateUserData(Integer id, UserDto userDto);

    Optional<User> getUserById(Integer userId);

    List<User> getAllUsers();

    DetailedUserResponseDto getUserDetails(Integer userId) throws NotFoundException;

    AuthenticationResponse login(AuthenticationRequest authenticationRequest) throws Exception;
}
