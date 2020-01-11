package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    private static Connection connection = null;

    public UserJdbcDAO() {
        if (connection == null) {
            connection = DBHelper.getDBHelper().getConnection();
        }
    }

    public boolean addUser(String name, int age) {
        try(PreparedStatement stmt = connection.prepareStatement("insert into User (name, age, role, password) values (?, ?, ?, ?)")) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, "user");
            stmt.setString(4, "1234");
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(long id) {
        try (PreparedStatement stmt = connection.prepareStatement(" DELETE FROM User where id = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateUser(User user) {
        try (PreparedStatement stmt = connection.prepareStatement("update User set name = ? , age = ?, password = ? , role = ? where id = ?")) {
            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getAge());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setLong(5, user.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(long id) {
        try(Statement stmt = connection.createStatement()) {
            stmt.execute("select id, name, age, role, password from User where id="+String.valueOf(id));
            ResultSet resultSet = stmt.getResultSet();
            resultSet.next();
            User user = new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getInt(3));
            user.setRole(resultSet.getString(4));
            user.setPassword(resultSet.getString(5));
            resultSet.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getAllUser() {
        List<User> clients = new ArrayList<>();
        try(Statement stmt = connection.createStatement()) {
            stmt.execute("select id, name, age, role, password from User");
            ResultSet resultSet = stmt.getResultSet();
            while(resultSet.next()) {
                clients.add(new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getInt(3)));
                clients.get(clients.size() - 1).setRole(resultSet.getString(4));
                clients.get(clients.size() - 1).setPassword(resultSet.getString(5));
            }
            resultSet.close();
            return clients;
        } catch ( SQLException e) {
            e.printStackTrace();
            return clients;
        }
    }

    public User getUserByName(String name) {
        User user = null;
        try(Statement stmt = connection.createStatement()) {
            stmt.execute("select id, age, password, role  from User where name='"+name+"'");
            ResultSet resultSet = stmt.getResultSet();
            resultSet.next();
            user = new User();
            user.setId(resultSet.getLong(1));
            user.setName(name);
            user.setAge(resultSet.getInt(2));
            user.setPassword(resultSet.getString(3));
            user.setRole(resultSet.getString(4));
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            user = null;
        }
        return user;
    }
}
