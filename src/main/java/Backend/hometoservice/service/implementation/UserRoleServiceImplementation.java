package Backend.hometoservice.service.implementation;

//import Backend.hometoservice.authorization.dto.ERole;
import Backend.hometoservice.authorization.dto.ERole;
import Backend.hometoservice.dto.AddRoleToUserRequestDto;
import Backend.hometoservice.model.Role;
import Backend.hometoservice.model.User;
import Backend.hometoservice.model.UserRole;
//import Backend.hometoservice.repository.RolesRepository;
import Backend.hometoservice.repository.RolesRepository;
import Backend.hometoservice.repository.UserRepository;
import Backend.hometoservice.repository.UserRolesRepository;
//import Backend.hometoservice.service.UserRoleService;
import Backend.hometoservice.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserRoleServiceImplementation implements UserRoleService {
    @Autowired
    private final RolesRepository rolesRepository;
    @Autowired
    private final UserRolesRepository userRolesRepository;
    @Autowired
    private final UserRepository userRepository;

//    @Override
//    public Role createRole(Role roleDto) {
//        Role role = Role.builder()
//                .name(roleDto.getName())
//                .build();
//        rolesRepository.save(role);
//        return role;
//    }
    @Override
    public List<UserRole> findAllUserRoles() {
        return userRolesRepository.findAll();
    }

    @Override
    public void deleteUserRoleById(Integer userRoleId) {
        if(!(userRolesRepository.existsById(userRoleId))) {
            System.out.println("User role doesn't exist!");
        }
        userRolesRepository.deleteById(userRoleId);
    }

    @Override
    public List<Role> AddDefaultRoles() {
        return List.of();
    }

//    @Override
//    public List<Role> AddDefaultRoles() {
//        List<Role> roles = new ArrayList<>();
//        for (ERole role : ERole.values()) {
//            var roleToAdd = new Role();
//            roleToAdd.setName(role.name());
//            roles.add(roleToAdd);
//        }
//
//        return rolesRepository.saveAll(roles);
//    }

    @Override
    public UserRole addRoleToUser(AddRoleToUserRequestDto addRoleToUseRequest) {
        Optional<User> user = userRepository.findById(addRoleToUseRequest.getUserId());
        if(user.isEmpty()) {
            throw new IllegalArgumentException("User not found by the provided id.");
        }

        ERole roleParsed = ERole.valueOf(addRoleToUseRequest.getRole());
        var role = rolesRepository.findByName(roleParsed);

        UserRole userRole = UserRole.builder()
                .role(role.get())
                .user(user.get())
                .build();

        return userRolesRepository.save(userRole);
    }
    public UserRole findByRole(ERole name) {
        return userRolesRepository.findByRole(name).get();
    }

}
