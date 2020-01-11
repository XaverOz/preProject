package dao;

import model.User;

import java.util.List;

public interface UserDAO {
        public boolean addUser(String name, int age);
        public boolean deleteUser(long id);
        public void updateUser(User user);
        public User getUserById(long id);
        public List<User> getAllUser();
        public User getUserByName(String name);
}
