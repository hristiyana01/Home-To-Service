package Backend.hometoservice.service;

import Backend.hometoservice.authorization.dto.ERole;
import Backend.hometoservice.dto.AddRoleToUserRequestDto;
import Backend.hometoservice.model.Role;
import Backend.hometoservice.model.UserRole;

import java.util.List;

public interface UserRoleService {
    //Role createRole(Role role);
    List<UserRole> findAllUserRoles();
    UserRole findByRole(ERole name);
    void deleteUserRoleById(Integer userRoleId);
    List<Role> AddDefaultRoles();
    UserRole addRoleToUser(AddRoleToUserRequestDto addRoleToUserRequestDto);
}
