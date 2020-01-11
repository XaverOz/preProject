package util;

import model.Role;
import model.User;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class DBHelper {

    public Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Role.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/pre2");
        configuration.setProperty("hibernate.connection.username", "zaa");
        configuration.setProperty("hibernate.connection.password", "Control1");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        return configuration;
    }
}