package Backend.hometoservice.controller;

import Backend.hometoservice.model.UserRole;
import Backend.hometoservice.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user-role")
@AllArgsConstructor
@RestController
public class UserRoleController {
    private final UserRoleService userRoleService;

    @PostMapping("/create")
    public UserRole createUserRole(@RequestBody UserRole userRole) {
        UserRole role = userRoleService.createUserRole(userRole);
        return role;
    }

    @GetMapping("/getAll")
    public List<UserRole> getAllUserRoles() {
        return userRoleService.findAllUserRoles();
    }

}
