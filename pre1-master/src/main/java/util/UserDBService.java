package util;

import dao.UserDAO;
import dao.UserJdbcDAO;
import model.User;
import java.util.List;

public class UserDBService {

    private static UserDAO dao = null;

    public UserDBService() {
    }

    public List<User> getAllUser() {
        UserDAO dao = getUserDAO();
        return dao.getAllUser();
    }

    public boolean addUser(String name, int age) {
        return getUserDAO().addUser(name, age);
    }

    public boolean deleteUser(long id) {
        UserDAO dao = getUserDAO();
        return dao.deleteUser(id);
    }

    public User getUserById(long id) {
        UserDAO dao = getUserDAO();
        return dao.getUserById(id);
    }

    private static UserDAO getUserDAO() {
        if(dao == null) {
            dao = new UserJdbcDAO();
        }
        return dao;
    }

    public void updateUser(User user) {
        getUserDAO().updateUser(user);
    }


}
