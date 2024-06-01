package Backend.hometoservice.controller;////package Backend.hometoservice.controller;
////
////import Backend.hometoservice.model.User;
////import Backend.hometoservice.repository.UserRepository;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.web.bind.annotation.*;
////
////@RestController
////@RequestMapping("/api")
////public class AuthController {
////
////    @Autowired
////    private UserRepository userRepository;
////
////    @Autowired
////    private PasswordEncoder passwordEncoder;
////
////    @PostMapping("/register")
////    public String registerUser(@RequestBody User user) {
////        user.setPassword(passwordEncoder.encode(user.getPassword()));
////        userRepository.save(user);
////        return "User registered successfully";
////    }
////
////    @PostMapping("/login")
////    public String login() {
////        return "Login successful";
////    }
////}
//import Backend.hometoservice.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api")
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/login")
//    public String login(@RequestBody AuthRequest authRequest) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
//            );
//
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            return "Login successful: " + userDetails.getUsername();
//        } catch (AuthenticationException e) {
//            return "Login failed: " + e.getMessage();
//        }
//    }
//}
//
import Backend.hometoservice.AuthRequest;
import Backend.hometoservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return "Login successful: " + userDetails.getUsername();
        } catch (AuthenticationException e) {
            return "Login failed: " + e.getMessage();
        }
    }
}
