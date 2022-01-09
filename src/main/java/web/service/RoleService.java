package web.service;

import web.models.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    void addRole(Role role);

    Role getRoleById(long id);

    void deleteRoleById(long id);

    List<Role> allRoles();

    Set<Role> findRoleById(List<Long> rolesId);
}
