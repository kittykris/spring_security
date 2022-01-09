package web.dao;

import web.models.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    void addRole(Role role);

    Role getRoleById(long id);

    void deleteRoleById(long id);

    List<Role> allRoles();

    Set<Role> findRoleById(List<Long> rolesId);
}
