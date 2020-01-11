package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    private static Connection connection = null;

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("pre1?").          //db name
                    append("user=zaa&").          //login
                    append("password=Control1");       //password
            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public UserJdbcDAO() {
        if (connection == null) {
            connection = getMysqlConnection();
        }
    }

    public boolean addUser(String name, int age) {
        try(PreparedStatement stmt = connection.prepareStatement("insert into User (name, age) values (?, ?)")) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
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
        try (PreparedStatement stmt = connection.prepareStatement("update User set name = ? , age = ? where id = ?")) {
            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getAge());
            stmt.setLong(3, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(long id) {
        try(Statement stmt = connection.createStatement()) {
            stmt.execute("select id, name, age from User where id="+String.valueOf(id));
            ResultSet resultSet = stmt.getResultSet();
            resultSet.next();
            User user = new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getInt(3));
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
            stmt.execute("select id, name, age from User");
            ResultSet resultSet = stmt.getResultSet();
            while(resultSet.next()) {
                clients.add(new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getInt(3)));
            }
            resultSet.close();
            return clients;
        } catch ( SQLException e) {
            e.printStackTrace();
            return clients;
        }
    }
}
