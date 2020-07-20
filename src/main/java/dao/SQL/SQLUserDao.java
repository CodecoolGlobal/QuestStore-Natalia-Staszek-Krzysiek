package dao.SQL;

import dao.UserDAO;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SQLUserDao extends Database_Connection implements UserDAO {


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
        String sqlStatement = "SELECT * FROM users";
        PreparedStatement statement = getPreparedStatementBy(new ArrayList<>(), sqlStatement);
        return getUsers(statement);
    }

    @Override
    public List<User> getAllByRole(int role) {
        String sqlStatement = "SELECT * FROM users WHERE id_role = ?;";
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(role), sqlStatement);
        return getUsers(statement);
    }

    @Override
    public List<User> getStudentsByGroupId(int groupID) {
        String sqlStatement = "SELECT name,login,email,password,phone_number,id_role FROM users JOIN students_details " +
                "ON users.id = students_details.id_user WHERE students_details.id_class=?;";

        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(groupID), sqlStatement);
        return getUsers(statement);
    }

    @Override
    public User getById(int id) {
        String sqlStatement = "SELECT * FROM users WHERE id = ?";
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(id), sqlStatement);
        return getUser(statement);
    }


    @Override
    public User getByLoginAndPassword(String login, String password) {
        String sqlStatement = "SELECT * FROM users WHERE login = ? AND password = ?;";
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(login, password), sqlStatement);
        return getUser(statement);
    }

    @Override
    public User getByLoginAndRole(String login, int role) {
        String sqlStatement = "SELECT * FROM users WHERE login = ? AND id_role = ?;";
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(login, role), sqlStatement);
        return getUser(statement);
    }

    @Override
    public User getByLogin(String login) {
        String sqlStatement = "SELECT * FROM users WHERE login = ?;";
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(login), sqlStatement);
        return getUser(statement);
    }

    @Override
    public User getByEmail(String email) {
        String sqlStatement = "SELECT * FROM users WHERE email = ?;";
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(email), sqlStatement);
        return getUser(statement);
    }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        String sqlStatement = "SELECT * FROM users WHERE phone_number = ?;";
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(phoneNumber), sqlStatement);
        return getUser(statement);
    }

    @Override
    public boolean add(User user) {
        String sqlStatement = "INSERT INTO users (name,login,email,password,phone_number,id_role) VALUES (?,?,?,?,?,?);";
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(user.getName(), user.getLogin(),
                user.getEmail(), user.getPassword(), user.getPhoneNumber(), user.getRole()), sqlStatement);
        return true;
    }

    @Override
    public boolean update(User user) {
        String sqlStatement = "UPDATE users SET name=?,login=?,email=?,password=?,phone_number=?,id_role=? WHERE id=?;";
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(user.getName(), user.getLogin(),
                user.getEmail(), user.getPassword(), user.getPhoneNumber(), user.getRole(), user.getId()), sqlStatement);
        return true;
    }

    @Override
    public boolean delete(User user) {
        String sqlStatement = "DELETE FROM users WHERE id = ?;";
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(user.getId()), sqlStatement);
        return true;
    }
}