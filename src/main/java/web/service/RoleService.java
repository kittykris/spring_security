package web.service;

import web.models.Role;
import web.models.User;

import java.util.Set;

public interface RoleService {

    Set<Role> allRoles();

    Role findRoleByName(String roleName);

    Set<Role> existingRoles(User user);
}
