package Backend.hometoservice.controller;

import Backend.hometoservice.model.UserRole;
import Backend.hometoservice.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user-role")
@AllArgsConstructor
@RestController
public class UserRoleController {
    private final UserRoleService userRoleService;

//    @PostMapping("/create")
//    public UserRole createUserRole(@RequestBody UserRole userRole) {
//        UserRole role = userRoleService.createUserRole(userRole);
//        return role;
//    }
    @PostMapping("/create")
    public String createUserRole(@ModelAttribute UserRole userRole) {
        UserRole role = userRoleService.createUserRole(userRole);
        return "user-role-created";
    }

}
