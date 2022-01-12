package web.dao;

import web.models.Role;

import java.util.Set;

public interface RoleDao {

    Set<Role> allRoles();

    Role findRoleByName(String roleName);

    void addRole(Role role);

    void deleteRole(Role role);

    void addDefaultRoles();
}
