package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.models.Role;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    public Role getRoleById(long id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public void deleteRoleById(long id) {
        roleDao.deleteRoleById(id);
    }

    @Override
    public List<Role> allRoles() {
        return roleDao.allRoles();
    }

    @Override
    public Set<Role> findRoleById(List<Long> rolesId) {
        return roleDao.findRoleById(rolesId);
    }
}
