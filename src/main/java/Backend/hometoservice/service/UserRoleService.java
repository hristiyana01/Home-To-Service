package Backend.hometoservice.service;

import Backend.hometoservice.model.UserRole;

import java.util.List;

public interface UserRoleService {
    UserRole createUserRole(UserRole userRole);
    List<UserRole> findAllUserRoles();
}
