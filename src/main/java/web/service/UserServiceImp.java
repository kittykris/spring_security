package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.models.Role;
import web.models.User;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    private UserDao userDao;

    @Autowired
    private RoleService roleService;


    @Autowired
    private PasswordEncoder bCryptEncoder;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) {
        //todo encrypt encoder
        //user.setPassword(bCryptEncoder.encode(user.getPassword()));
        user.setRoles(existingRoles(user));
        userDao.addUser(user);
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void updateUser(long id, User user) {
        user.setRoles(existingRoles(user));
        userDao.updateUser(id, user);
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    public List<User> userList() {
        return userDao.userList();
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDao.getUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("There no user with this username found");
        }
        return user;
    }

    private Set<Role> existingRoles(User user) {
        Set<Role> roles = new HashSet<>();
        user.getRoles().forEach(role -> {
            Role existing = roleService.findRoleByName(role.getName());
            if (existing.getId() != null) {
                roles.add(existing);
            }
        });
        return roles;
    }
}
