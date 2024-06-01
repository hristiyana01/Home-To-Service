package Backend.hometoservice.controller;

import Backend.hometoservice.authorization.dto.ERole;
import Backend.hometoservice.dto.AddRoleToUserRequestDto;
import Backend.hometoservice.model.Role;
import Backend.hometoservice.model.UserRole;
import Backend.hometoservice.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user-role")
@AllArgsConstructor
@RestController
public class UserRoleController {
    private final UserRoleService userRoleService;

    @PostMapping("/add-default-roles")
    public ResponseEntity<List<Role>> AddDefaultRoles() {
        List<Role> roles = userRoleService.AddDefaultRoles();
        return ResponseEntity.ok(roles);
    }

    @PostMapping("/add-role-to-user")
    public UserRole createUserRole(@RequestBody AddRoleToUserRequestDto addRoleToUserRequest) {
        //todo admin access only
        UserRole role = userRoleService.addRoleToUser(addRoleToUserRequest);
        return role;
    }

    @GetMapping("/getAll")
    public List<UserRole> getAllUserRoles() {
        return userRoleService.findAllUserRoles();
    }

    //only for sysadmin
    @DeleteMapping("/delete/{userRoleId}")
    public ResponseEntity<UserRole> deleteUserRoleById(@PathVariable Integer userRoleId) {
        userRoleService.deleteUserRoleById(userRoleId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{role}")
    public ResponseEntity<UserRole> findUserByRole(@PathVariable ERole role) {
        userRoleService.findByRole(role);
        return ResponseEntity.ok().build();
    }
}
