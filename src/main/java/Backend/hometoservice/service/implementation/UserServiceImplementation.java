package Backend.hometoservice.service.implementation;

import Backend.hometoservice.dto.UserDto;
import Backend.hometoservice.model.User;
import Backend.hometoservice.repository.UserRepository;
import Backend.hometoservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    @Override
    public User createUser(UserDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .phone(userDto.getPhone())
                .build();
        userRepository.save(user);
        return user;
    }
}
