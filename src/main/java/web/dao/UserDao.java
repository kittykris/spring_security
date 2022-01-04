package web.dao;

import web.models.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    User getUserById(long id);
    void updateUser(long id, User user);
    void deleteUser(long id);
    List<User> userList();
}
