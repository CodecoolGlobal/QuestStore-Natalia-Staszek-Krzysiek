package dao.SQL;

import dao.UserDAO;
import data.Database_Connection;
import data.statements.UserStatements;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLUserDao extends Database_Connection implements UserDAO {

    private UserStatements userStatements = new UserStatements();

    private User getUser(PreparedStatement statement) {
        User user = null;
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("phone_number"),
                        resultSet.getInt("id_role"));
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            disconnect();
        }
        return user;
    }


    private List<User> getUsers(PreparedStatement statement) {
        List<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("phone_number"),
                        resultSet.getInt("id_role")));
                statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            disconnect();
        }
        return users;
    }

    @Override
    public List<User> getAll() {
        String sqlStatement = userStatements.selectAllUsers();
        PreparedStatement statement = getPreparedStatementBy(new ArrayList<>(), sqlStatement);
        return getUsers(statement);
    }

    @Override
    public List<User> getAllByRole(String role) {
        return null;
    }

    @Override
    public List<User> getStudentsByGroupId(int id) {
        return null;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        return null;
    }

    @Override
    public User getByLoginAndRole(String login, String role) {
        return null;
    }

    @Override
    public User getByLogin(String login) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public boolean add(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public void setDatabasePath(String path) {

    }
}
