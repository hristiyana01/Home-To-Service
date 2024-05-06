package Backend.hometoservice.service.implementation;

import Backend.hometoservice.model.UserRole;
import Backend.hometoservice.repository.UserRoleRepository;
import Backend.hometoservice.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserRoleServiceImplementation implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    @Override
    public UserRole createUserRole(UserRole userRole) {
        UserRole role = UserRole.builder()
                .name(userRole.getName())
                .build();
        userRoleRepository.save(role);
        return role;
    }

    @Override
    public List<UserRole> findAllUserRoles() {
        return userRoleRepository.findAll();
    }
}
