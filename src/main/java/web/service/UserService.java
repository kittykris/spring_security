package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    void addUser(User user);

    User getUserById(long id);

    void updateUser(long id, User user);

    void deleteUser(long id);

    List<User> userList();

    boolean isUsernameUnique(String userName);
}
