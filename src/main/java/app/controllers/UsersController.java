package app.controllers;

import app.dtos.AuthenticationRequest;
import app.dtos.AuthenticationResponse;
import app.dtos.user.CreateUserDto;
import app.dtos.user.DetailedUserResponseDto;
import app.dtos.user.UserDto;
import app.models.User;
import app.repositories.PostRepository;
import app.repositories.ReviewRepository;
import app.services.UserService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UsersController {
    private final UserService userService;
    private final PostRepository postRepository;
    private final ReviewRepository reviewRepository;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto userDto) {
        var createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            AuthenticationResponse response = userService.login(authenticationRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new AuthenticationResponse(null, "Unauthorized"));
        }
    }

    @PutMapping("/update-data/{id}")
    public ResponseEntity<User> updateUserData(@PathVariable Integer id, @RequestBody UserDto userDto) {
        var updateUser = userService.updateUserData(id, userDto);
        return ResponseEntity.ok().body(updateUser);
    }

    @GetMapping("/getuser/{id}")
    public Optional<User> getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        var users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<DetailedUserResponseDto> getDetailedUserDataByUserId(@PathVariable("userId") Integer userId) throws NotFoundException {
        var posts = postRepository.findPostsByUserId(userId);
        DetailedUserResponseDto detailedUserResponseDto = new DetailedUserResponseDto();
        detailedUserResponseDto.setPosts(posts.get());
        return ResponseEntity.ok().body(detailedUserResponseDto);
    }
}
