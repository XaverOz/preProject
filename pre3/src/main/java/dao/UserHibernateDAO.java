package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private static SessionFactory sessionFactory;

    public UserHibernateDAO() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/pre1");
        configuration.setProperty("hibernate.connection.username", "zaa");
        configuration.setProperty("hibernate.connection.password", "Control1");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        return configuration;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
    public boolean addUser(String name, int age) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new User(name, age));
        session.getTransaction().commit();
        return true;
    }
    public boolean deleteUser(long id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("Delete FROM User where id =:id");
        query.setParameter("id", id);
        query.executeUpdate();
        session.close();
        return true;
    }
    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("UPDATE User SET age =:age, name =:name  WHERE id =:id");
        query.setParameter("age", user.getAge());
        query.setParameter("name", user.getName());
        query.setParameter("id", user.getId());
        query.executeUpdate();
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
        Query query = session.createQuery("FROM User");
        List<User> users = query.list();
        session.close();
        return users;
    }
}