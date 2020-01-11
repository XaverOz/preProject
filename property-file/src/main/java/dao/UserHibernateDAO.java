package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;

import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private static SessionFactory sessionFactory;

    public UserHibernateDAO() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = DBHelper.getDBHelper().getConfiguration();
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
        Query query = session.createQuery("UPDATE User SET age =:age, name =:name  WHERE id =:id");
        query.setParameter("age", user.getAge());
        query.setParameter("name", user.getName());
        query.setParameter("id", user.getId());
        query.executeUpdate();
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
        Query query = session.createQuery("FROM User");
        List<User> users = query.list();
        session.close();
        return users;
    }
}