package util;

import dao.UserDAO;
import dao.UserDAOFactory;
import dao.UserHibernateDAO;
import dao.UserHibernateDAOFactory;
import model.User;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class UserDBService {

    private static UserDAOFactory daoFactory = null;
    private static UserDBService userDBService = null;
    public static final String configDAO = "/DAO.property";

    private UserDBService() {
    }

    public static UserDBService getUserDBService() {
        if(userDBService == null) {
            userDBService = new UserDBService();
        }
        if(daoFactory == null) {
            Properties prop = new Properties();
            try {
                InputStream is = UserDBService.class.getResourceAsStream(configDAO);
                prop.load(is);
                daoFactory = (UserDAOFactory) Class.forName(prop.getProperty("dao_factory")).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                daoFactory = new UserHibernateDAOFactory();
            }
        }
        return userDBService;
    }

    public List<User> getAllUser() {
        return daoFactory.getUserDAO().getAllUser();
    }

    public boolean addUser(String name, int age) {
        return daoFactory.getUserDAO().addUser(name, age);
    }

    public boolean deleteUser(long id) {
        return daoFactory.getUserDAO().deleteUser(id);
    }

    public User getUserById(long id) {
        return daoFactory.getUserDAO().getUserById(id);
    }

    public void updateUser(User user) {
        daoFactory.getUserDAO().updateUser(user);
    }


}
