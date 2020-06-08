package dao.SQL;

import dao.UserDAO;
import data.Database_Connection;
import data.statements.UserStatement;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SQLUserDao extends Database_Connection implements UserDAO {

    private UserStatement userStatement = new UserStatement();

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
        String sqlStatement = userStatement.selectAllUsers();
        PreparedStatement statement = getPreparedStatementBy(new ArrayList<>(), sqlStatement);
        return getUsers(statement);
    }

    @Override
    public List<User> getAllByRole(int role) {
        String sqlStatement = userStatement.selectAllUsersByRole();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(role), sqlStatement);
        return getUsers(statement);
    }

    @Override
    public List<User> getStudentsByGroupId(int groupID) {
        String sqlStatement = userStatement.selectAllStudentsByGroupId();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(groupID), sqlStatement);
        return getUsers(statement);
    }

    @Override
    public User getById(int id) {
        String sqlStatement = userStatement.selectUserById();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(id), sqlStatement);
        return getUser(statement);
    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        String sqlStatement = userStatement.selectUserByLoginAndPassword();
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(login, password), sqlStatement);
        return getUser(statement);
    }

    @Override
    public User getByLoginAndRole(String login, int role) {
        String sqlStatement = userStatement.selectUserByLoginAndRole();
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(login, role), sqlStatement);
        return getUser(statement);
    }

    @Override
    public User getByLogin(String login) {
        String sqlStatement = userStatement.selectUserByLogin();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(login), sqlStatement);
        return getUser(statement);
    }

    @Override
    public User getByEmail(String email) {
        String sqlStatement = userStatement.selectUserByEmail();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(email), sqlStatement);
        return getUser(statement);
    }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        String sqlStatement = userStatement.selectUserByPhoneNumber();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(phoneNumber), sqlStatement);
        return getUser(statement);
    }

    @Override
    public boolean add(User user) {
        String sqlStatement = userStatement.insertUserStatement();
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(user.getName(), user.getLogin(),
                user.getEmail(), user.getPassword(), user.getPhoneNumber(), user.getRole()), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean update(User user) {
        String sqlStatement = userStatement.updateUserStatement();
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(user.getName(), user.getLogin(),
                user.getEmail(), user.getPassword(), user.getPhoneNumber(), user.getRole(), user.getId()), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean delete(User user) {
        String sqlStatement = userStatement.deleteUserStatement();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(user.getId()), sqlStatement);
        return update(statement);
    }
}
