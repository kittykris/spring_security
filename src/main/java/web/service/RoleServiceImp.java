package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.models.Role;

import java.util.Set;

@Service
public class RoleServiceImp implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Override
    public Set<Role> allRoles() {
        return roleDao.allRoles();
    }

    @Override
    public Role findRoleByName(String roleName) {
        return roleDao.findRoleByName(roleName);
    }
}
