package Backend.hometoservice.controller;

import Backend.hometoservice.service.implementation.UserDetailsServiceImplementation;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import Backend.hometoservice.model.User;

@RestController
public class LoginController {

    //private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImplementation userDetailsService;


    public LoginController(
            //AuthenticationManager authenticationManager,
                           UserDetailsServiceImplementation userDetailsService) {
        //this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/api/login")
    public String login(@RequestBody User user) {
        return "";

//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
//        return userDetailsService.generateToken(userDetails);
    }
}