package dao;

public class UserJdbcDAOFactory implements UserDAOFactory {
    private UserDAO userDAO = null;

    public UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserJdbcDAO();
        }
        return userDAO;
    }
}
