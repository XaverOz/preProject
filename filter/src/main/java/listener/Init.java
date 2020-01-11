package listener;
import model.User;
import util.UserDBService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class Init implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        List<User> users = UserDBService.getUserDBService().getAllUser();
        users.forEach(a->UserDBService.getUserDBService().deleteUser(a.getId()));
        UserDBService.getUserDBService().addUser("admin", 14);
        UserDBService.getUserDBService().addUser("user", 14);
        users = UserDBService.getUserDBService().getAllUser();
        User admin = null;
        User user = null;
        if (users.get(0).getName().equals("admin")) {
            admin = users.get(0);
            user = users.get(1);
        } else {
            admin = users.get(0);
            user = users.get(1);
        }
        admin.setRole("admin");
        admin.setPassword("admin");
        user.setRole("user");
        user.setPassword("1234");
        UserDBService.getUserDBService().updateUser(user);
        UserDBService.getUserDBService().updateUser(admin);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        // empty
    }
}

