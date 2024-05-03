package Backend.hometoservice.controller;

import Backend.hometoservice.dto.UserDto;
import Backend.hometoservice.model.User;
import Backend.hometoservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
@RestController
public class UsersController {
    private final UserService userService;

    //    @PostMapping("/register")
//    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
//        var createdUser = userService.createUser(userDto);
//     //   return ResponseEntity.ok().body(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//    }
    @PostMapping("/register")
    public String createUser(@Valid @ModelAttribute("user") UserDto userDto, Model model) {
        User user = userService.createUser(userDto);
        model.addAttribute("user", user);
        //   return ResponseEntity.ok().body(user);
        return "add-user";
    }
    //??
//    @PostMapping("/adduser")
//    public String addUser(@Valid User user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "add-user";
//        }
//    }

    //    @PutMapping("/update-email/{id}")
//    public ResponseEntity<User> updateUserEmail(@PathVariable Integer id, @RequestParam String email) {
//        var updateUser = userService.updateUserEmail(id, email);
//        return ResponseEntity.ok().body(updateUser);
//    }
    @PutMapping("/update-data/{id}")
    public ResponseEntity<User> updateUserData(@PathVariable Integer id, @ModelAttribute UserDto userDto) {
        var updateUser = userService.updateUserData(id, userDto);
        return ResponseEntity.ok().body(updateUser);
    }

    @GetMapping("/get/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        var users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "redirect:/add-user";

     //   userService.save(user);
      //  return"redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "update-user";
    }
}
