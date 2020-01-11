package service;

import model.Role;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.DBHelper;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserServiceHibernate implements UserService {
    private static Configuration configuration = null;

    private static SessionFactory sessionFactory = null;

    @Autowired
    public UserServiceHibernate(DBHelper dbHelper) {
        if (configuration == null) {
            configuration = dbHelper.getConfiguration();
         }
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
            initDB();
        }
    }

    private SessionFactory createSessionFactory() {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public Role getRoleByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Role where name =:name");
        query.setParameter("name", name);
        Role role = (Role) query.uniqueResult();
        session.close();
        return role;
    }

    public boolean deleteUser(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("Delete FROM User where id =:id");
        query.setParameter("id", id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public void addUserFindRole(User user) {
        Session session = sessionFactory.openSession();
        Role role = getRoleByName(user.getRole().getName());
        user.setRole(role);
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Role role = getRoleByName(user.getRole().getName());
        user.setRole(role);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public User getUserById(long id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User where id =:id");
        query.setParameter("id", id);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    public List<User> getAllUser() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM User ");
        List<User> users = query.list();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    public User getUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User where login =:login");
        query.setParameter("login", login);
        User user = null;
        try {
            user = (User) query.uniqueResult();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void addRole(Role role) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(role);
        session.getTransaction().commit();
        session.close();
    }

    public void initDB() {
        User admin = new User();
        User user = new User();
        admin.setPassword("admin");
        admin.setEmail("admin@yandex.ru");
        admin.setLogin("admin");
        user.setPassword("1234");
        user.setEmail("user@yandex.ru");
        user.setLogin("user");
        Role roleAdmin = new Role("ADMIN");
        Role roleUser = new Role("USER");
        addRole(roleAdmin);
        addRole(roleUser);
        user.setRole(roleUser);
        addUser(user);
        admin.setRole(roleAdmin);
        addUser(admin);
    }
}