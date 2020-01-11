package service;

import model.Role;
import model.User;
import java.util.List;

public interface UserService {

    public Role getRoleByName(String name);

    public boolean addUser(String name, int age);

    public boolean deleteUser(long id);

    public void updateUser(User user);

    public void addUser(User user);

    public User getUserById(long id);

    public List<User> getAllUser();

    public User getUserByName(String name);

    public void initDB();

}