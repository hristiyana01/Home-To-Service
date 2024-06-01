package Backend.hometoservice.controller;

import Backend.hometoservice.dto.UserDto;
import Backend.hometoservice.dto.detailedPost.DetailedPostResponseDto;
import Backend.hometoservice.dto.detailedUserDto.DetailedUserResponseDto;
import Backend.hometoservice.model.User;
import Backend.hometoservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
@RestController
public class UsersController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        var createdUser = userService.createUser(userDto);
     //   return ResponseEntity.ok().body(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

//    @PutMapping("/update-email/{id}")
//    public ResponseEntity<User> updateUserEmail(@PathVariable Integer id, @RequestParam String email) {
//        var updateUser = userService.updateUserEmail(id, email);
//        return ResponseEntity.ok().body(updateUser);
//    }
    @PutMapping("/update-data/{id}")
    public ResponseEntity<User> updateUserData(@PathVariable Integer id, @RequestBody UserDto userDto) {
        var updateUser = userService.updateUserData(id, userDto);
        return ResponseEntity.ok().body(updateUser);
    }
    @GetMapping("/get/{id}")
    public Optional<User> getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        var users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @Operation(summary= "Gets post by ID")
    @GetMapping("/{userId}")
    public ResponseEntity<DetailedUserResponseDto> getDetailedUserDataByUserId(@PathVariable Integer userId) throws NotFoundException {
        DetailedUserResponseDto user = userService.getUserDetails(userId);
        return ResponseEntity.ok().body(user);
    }
}
