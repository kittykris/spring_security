package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.models.Role;
import web.models.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService{

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Set<Role> allRoles() {
        return roleDao.allRoles();
    }

    @Override
    public Role findRoleByName(String roleName) {
        return roleDao.findRoleByName(roleName);
    }

    @Override
    public Set<Role> existingRoles(User user) {
        Set<Role> roles = new HashSet<>();
        user.getRoles().forEach(role -> {
            Role existing = roleDao.findRoleByName(role.getName());
            if (existing.getId() != null) {
                roles.add(existing);
            }
        });
        return roles;
    }
}
