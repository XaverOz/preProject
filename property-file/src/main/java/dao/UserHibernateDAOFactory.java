package dao;

public class UserHibernateDAOFactory implements UserDAOFactory {
    private UserDAO userDAO = null;

    public UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserHibernateDAO();
        }
        return userDAO;
    }
}
