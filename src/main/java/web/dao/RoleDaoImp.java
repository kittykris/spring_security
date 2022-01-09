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
    public Set<Role> allRoles() {
        return new HashSet<>(entityManager
                .createQuery("SELECT role from Role role", Role.class)
                .getResultList());
    }
}
