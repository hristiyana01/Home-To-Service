package Backend.hometoservice.controller;

import Backend.hometoservice.dto.UserDto;
import Backend.hometoservice.model.User;
import Backend.hometoservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/users")
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
}
