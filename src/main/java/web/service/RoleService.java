package web.service;

import web.models.Role;

import java.util.Set;

public interface RoleService {

    Set<Role> allRoles();

    Role findRoleByName(String roleName);
}
