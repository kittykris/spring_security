package web.dao;

import org.springframework.stereotype.Repository;
import web.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role getRoleById(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void deleteRoleById(long id) {
        entityManager.remove(entityManager.find(Role.class, id));
    }

    @Override
    public List<Role> allRoles() {
        return entityManager.createQuery("SELECT role from Role role", Role.class)
                .getResultList();
    }

    @Override
    public Set<Role> findRoleById(List<Long> rolesId) {
        return new HashSet<>(entityManager
                .createQuery("select role from Role role where role.id in :roles", Role.class)
                .setParameter("roles", rolesId).getResultList());
    }
}
