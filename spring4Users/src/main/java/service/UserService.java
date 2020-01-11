package service;

import model.Role;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import util.DBHelper;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserService {

    private static Configuration configuration = null;

    private static SessionFactory sessionFactory = null;

    @Autowired
    public UserService(DBHelper dbHelper) {
        if (configuration == null) {
            configuration = dbHelper.getConfiguration();
        }
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
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

    public boolean addUser(String name, int age) {
        //Session session = sessionFactory.openSession();
        //session.beginTransaction();
        User user = new User(name, age);
        user.setPassword("1234");
        Role roleUser = getRoleByName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        user.setRoles(roles);
        //session.save(user);
        //session.getTransaction().commit();
        addUser(user);
        return true;
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

    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();
        session.close();
    }

    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        user.getRoles().stream().filter(a->session.get(a.getClass(), a.getId()) != null).forEach(a->session.merge(a));
        user.getRoles().stream().filter(a->session.get(a.getClass(), a.getId()) == null).forEach(a->session.save(a));
        session.getTransaction().commit();
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
        Query query = session.createQuery("FROM User");
        List<User> users = query.list();
        session.close();
        return users;
    }

    public User getUserByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User where name =:name");
        query.setParameter("name", name);
        User user = null;
        try {
            user = (User) query.uniqueResult();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void initDB() {
        User admin = new User("admin", 13);
        User user = new User("user", 13);
        admin.setPassword("admin");
        user.setPassword("1234");
        Role roleAdmin = new Role("ADMIN");
        Role roleUser = new Role("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        user.setRoles(roles);
        addUser(user);
        roles.add(roleAdmin);
        admin.setRoles(roles);
        addUser(admin);
    }
}
