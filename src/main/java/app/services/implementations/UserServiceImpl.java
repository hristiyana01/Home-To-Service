package app.services.implementations;

import app.dtos.AuthenticationRequest;
import app.dtos.AuthenticationResponse;
import app.dtos.user.CreateUserDto;
import app.dtos.user.DetailedUserResponseDto;
import app.dtos.user.UserDto;
import app.enums.Roles;
import app.enums.Status;
import app.models.Post;
import app.models.User;
import app.repositories.PostRepository;
import app.repositories.ReviewRepository;
import app.repositories.UserRepository;
import app.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) throws Exception {
        try {
            var foundUser = userRepository.findUserByEmail(authenticationRequest.getEmail());
            var user = foundUser.get();
            if (!user.getPassword().equals(authenticationRequest.getPassword())) {
                return new AuthenticationResponse(null, "Incorrect username or password");
            }

            String jwt = Jwts.builder()
                    .setSubject(foundUser.get().getId().toString())
                    .claim("role", foundUser.get().getRole())
                    .claim("id", foundUser.get().getId())
                    .claim("email", foundUser.get().getEmail())
                    .claim("username", foundUser.get().getUsername())
                    .claim("name", foundUser.get().getName())
                    .claim("surname", foundUser.get().getSurname())
                    .claim("phone_number", foundUser.get().getPhone_number())
                    .claim("city", foundUser.get().getCity())
                    .claim("country", foundUser.get().getCountry())
                    .signWith(SignatureAlgorithm.HS256, "io78f6ea8B&%865vd8sd8a5dv3d24f24f2")
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 10 hours expiration
                    .compact();

            return new AuthenticationResponse(jwt, null);
        } catch (Exception e) {
            return new AuthenticationResponse(null, "Incorrect username or password");
        }
    }

    @Override
    public User createUser(CreateUserDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .password(userDto.getPassword())
                .country(userDto.getCountry())
                .city(userDto.getCity())
                .phone_number(userDto.getPhone())
                .registered_date(Instant.now())
                .role(Roles.SELLER.name())
                .build();

        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUserData(Integer id, UserDto userDto) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found by the provided id.");
        }

        User updatedUser = user.get();
        updatedUser.setEmail(userDto.getEmail() != null ? userDto.getEmail() : updatedUser.getEmail());
        updatedUser.setName(userDto.getUsername() != null ? userDto.getUsername() : updatedUser.getEmail());
        updatedUser.setSurname(userDto.getSurname() != null ? userDto.getSurname() : updatedUser.getSurname());
        updatedUser.setPassword(userDto.getPassword() != null ? userDto.getPassword() : updatedUser.getPassword());
        updatedUser.setPhone_number(userDto.getPhone() != null ? userDto.getPhone() : updatedUser.getPhone_number());
        updatedUser.setCountry(userDto.getCountry() != null ? userDto.getCountry() : updatedUser.getCountry());
        updatedUser.setCity(userDto.getCity() != null ? userDto.getCity() : updatedUser.getCity());

        return userRepository.save(updatedUser);
    }

    @Override
    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public DetailedUserResponseDto getUserDetails(Integer userId) throws NotFoundException {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new NotFoundException("User with id " + userId + " not found.");
        }

        List<Post> posts = postRepository.findAllByUserIdAndStatus(userId, Status.ACTIVE);

        DetailedUserResponseDto detailedUserResponseDto = new DetailedUserResponseDto();
        detailedUserResponseDto.setPosts(posts);

        return detailedUserResponseDto;
    }
}
