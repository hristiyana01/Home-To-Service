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
import Backend.hometoservice.authorization.dto.LoginRequest;
import Backend.hometoservice.config.AuthRequest;
import Backend.hometoservice.dto.AuthenticationResponseDto;
import Backend.hometoservice.dto.JwtTokenDto;
import Backend.hometoservice.dto.UserDto;
import Backend.hometoservice.model.Role;
import Backend.hometoservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) throws GeneralSecurityException, IOException {

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

//    @PostMapping("/registration")
//    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody UserDto userDto, LoginRequest loginRequestRoleType) {
//        return ResponseEntity.ok(authenticationService.register(userDto, loginRequestRoleType));
//    }
//
//    @PostMapping("/authentication")
//    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody UserDto userDto, LoginRequest loginRequestRoleType) {
//        return ResponseEntity.ok(authenticationService.authenticate(userDto, loginRequestRoleType));
//    }
}