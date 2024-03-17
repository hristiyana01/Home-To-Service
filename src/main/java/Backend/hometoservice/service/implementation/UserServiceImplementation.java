package Backend.hometoservice.service.implementation;

import Backend.hometoservice.dto.UserDto;
import Backend.hometoservice.model.User;
import Backend.hometoservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements Backend.hometoservice.service.UserService {
    private final UserRepository userRepository;

    @Override
    public User createUser(UserDto userDto) {
        User user = User.builder()
                .user_role_id(1)
                .email(userDto.getEmail())
                .name(userDto.getUsername())
                .surname(userDto.getSurname())
                .password(userDto.getPassword())
                .country(userDto.getCountry())
                .city(userDto.getCity())
                .zip(userDto.getZip())
                .phone(userDto.getPhone())
                .address(userDto.getAddress())
                .registeredDate(Instant.now())
                .updatedDate(Instant.now())
                .build();
        userRepository.save(user);
        return user;
    }

//    @Override
//    public User updateUserEmail(Integer id, String email) {
//       Optional<User> user = userRepository.findById(id);
//       if(user.isEmpty()) {
//           throw new IllegalArgumentException("User not found by the provided id.");
//       }
//       User updatedUser = user.get();
//       updatedUser.setEmail(email);
//
//       return userRepository.save(updatedUser);
//    }

    @Override
    public User updateUserData(Integer id, UserDto userDto) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new IllegalArgumentException("User not found by the provided id.");
        }
        User updatedUser = user.get();
        updatedUser.setEmail(userDto.getEmail()!= null ? userDto.getEmail() : updatedUser.getEmail());
        updatedUser.setName(userDto.getUsername()!= null ? userDto.getUsername() : updatedUser.getEmail());
        updatedUser.setSurname(userDto.getSurname()!= null ? userDto.getSurname() : updatedUser.getSurname());
        updatedUser.setPassword(userDto.getPassword()!= null ? userDto.getPassword() : updatedUser.getPassword());
        updatedUser.setUser_role_id(userDto.getUserRoleId()!= null ? userDto.getUserRoleId() : updatedUser.getUser_role_id());
        updatedUser.setPhone(userDto.getPhone()!= null ? userDto.getPhone() : updatedUser.getPhone());
        updatedUser.setAddress(userDto.getAddress()!= null ? userDto.getAddress() : updatedUser.getAddress());

        updatedUser.setUpdatedDate(Instant.now());
        return userRepository.save(updatedUser);
    }
    @Override
    public Optional<User> getUserById(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
