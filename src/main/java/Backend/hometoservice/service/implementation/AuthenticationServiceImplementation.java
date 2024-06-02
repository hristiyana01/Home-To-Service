package Backend.hometoservice.service.implementation;

import Backend.hometoservice.authorization.JwtService;
import Backend.hometoservice.authorization.dto.LoginRequest;
import Backend.hometoservice.dto.AuthenticationResponseDto;
import Backend.hometoservice.dto.JwtTokenDto;
import Backend.hometoservice.dto.UserDto;
import Backend.hometoservice.model.Role;
import Backend.hometoservice.model.User;
import Backend.hometoservice.repository.RolesRepository;
import Backend.hometoservice.repository.UserRepository;
import Backend.hometoservice.service.AuthenticationService;
import io.jsonwebtoken.io.IOException;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImplementation implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RolesRepository roleRepository;

//    @Override
//    public AuthenticationResponseDto login(JwtTokenDto jwtTokenDto) throws GeneralSecurityException, IOException {
//        UserDto userDto = jwtService.generateJwtToken(jwtTokenDto.getJwtToken());
//        AuthenticationResponseDto authenticationResponse;
//
//        try {
//            authenticationResponse = authenticate(userDto, userDto);
//
//            return authenticationResponse;
//        } catch (Exception exception) {
//            authenticationResponse = register(userDto, googleTokenDto.getLoginRequestRoleType());
//
//            return authenticationResponse;
//        }
//    }
//
//    @Override
//    public AuthenticationResponseDto register(UserDto userDto, LoginRequest loginRequestRoleType) throws Exception {
//        User appUser = new User();
//
//        if (loginRequestRoleType.getRole() == Roles.MODERATOR || loginRequestRoleType.getRole() == Roles.SELLER) {
//            appUser = userRepository.findUserByEmail(userDto.getEmail())
//                    .orElseThrow(() -> new Exception("User not found"));
//
//        } else {
//            appUser.setEmail(userDto.getEmail());
//        }
//
//        appUser.setName(userDto.getName());
//        appUser.setSurname(userDto.getSurname());
//        appUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
//
//        AuthenticationResponseDto authenticationResponseDto = buildAuthenticationResponseDto(appUser, userDto, loginRequestRoleType.getRole());
//
//        userRepository.save(appUser);
//
//        return authenticationResponseDto;
//    }

//    @Override
//    public AuthenticationResponseDto authenticate(UserDto userDto, LoginRequest loginRequestRoleType) throws AuthenticationException {
//        //TODO: test if authenticate checks the data from the db
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
//
//        User user = userRepository.findUserByEmail(userDto.getEmail())
//                .orElseThrow(() -> new UsernameNotFoundException("No user found"));
//
//        AuthenticationResponseDto authenticationResponseDto = buildAuthenticationResponseDto(user, userDto, loginRequestRoleType.getRole());
//
//        return authenticationResponseDto;
//    }

//    @Override
//    public JwtTokenDto jwtFromRefreshToken(RefreshTokenDto refreshTokenDto) {
//        String refreshToken = refreshTokenDto.getRefreshToken();
//
//        String email = jwtService.extractUsername(refreshToken);
//
//        User appUserFromClaims = userRepository.findAppUserByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("No user found"));
//
//        return JwtTokenDto.builder()
//                .jwtToken(jwtService.generateJwtToken(appUserFromClaims))
//                .build();
//    }

//    private AuthenticationResponseDto buildAuthenticationResponseDto(User appUser, UserDto userDto, Roles loginRequestRole) throws AuthenticationException {
//        Roles loginResponseRoleType = null;
//        Map<String, Object> extraClaims = new HashMap<>();
//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//        switch (loginRequestRole) {
//            case MODERATOR -> {
//                boolean isAppUserAdmin = roleRepository.findByUser(appUser)
//                        .equals(roleRepository.findByUser(appUser));
////                        .stream()
////                        .findAny()
////                        .map(role -> role.getRole().equals(Roles.MODERATOR))
////                        .orElse(false);
//
//
////                //TODO: If sysadmin can never log in as tenant/admin
////                if (sysadmin.isPresent()) {
////                    loginResponseRoleType = SYSADMIN;
////                    authorities.add(new SimpleGrantedAuthority(loginResponseRoleType.name()));
////                } else if (tenant.isPresent()) {
////                    loginResponseRoleType = TENANT;
////                    authorities.add(new SimpleGrantedAuthority(loginResponseRoleType.name()));
////                } else if (isAppUserAdmin) {
////                    loginResponseRoleType = ADMIN;
////                    authorities.add(new SimpleGrantedAuthority(loginResponseRoleType.name()));
////                }
//            }
//            case SELLER -> {
//                loginResponseRoleType = Roles.SELLER;
//                authorities.add(new SimpleGrantedAuthority(loginResponseRoleType.name()));
//            }
////            case APPUSER -> {
////                loginResponseRoleType = USER;
////                authorities.add(new SimpleGrantedAuthority(loginResponseRoleType.name()));
////            }
//            default -> throw new AuthenticationException("Wrong role request type");
//        }
//
//        extraClaims.put("authorities", authorities);
//        String jwtToken = jwtService.generateJwtToken(extraClaims, appUser);
//        //String refreshToken = jwtService.generateRefreshToken(appUser);
//
//        return AuthenticationResponseDto.builder()
//                .token(jwtToken)
//                //.refreshToken(refreshToken)
//                .user(appUser)
//                .roleType(roleRepository.findByUser(appUser))
//                .build();
//    }
}
