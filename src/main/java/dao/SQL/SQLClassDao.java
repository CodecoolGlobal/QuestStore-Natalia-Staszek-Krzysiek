package dao.SQL;

import dao.ClassDAO;
import data.Database_Connection;
import data.statements.ClassStatement;
import model.Class;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SQLClassDao extends Database_Connection implements ClassDAO {

    ClassStatement classStatement = new ClassStatement();

    @Override
    public List<Class> getAll() {
        String sqlStatement = classStatement.selectAllClasses();
        List<Class> classes = new ArrayList<>();
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                classes.add(new Class(
                        resultSet.getInt("id"),
                        resultSet.getString("name")));
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            disconnect();
        }
        return classes;
    }

    @Override
    public Class getByName(String name) {
        String sqlStatement = classStatement.selectTeamByName();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(name), sqlStatement);
        return getClass(statement);
    }

    @Override
    public Class getById(int id) {
        String sqlStatement = classStatement.selectGroupById();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(id), sqlStatement);
        return getClass(statement);
    }

    private Class getClass(PreparedStatement statement) {
        Class aClass = null;
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                aClass = new Class(
                        resultSet.getInt("id"),
                        resultSet.getString("name"));
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            disconnect();
        }
        return aClass;
    }

    @Override
    public boolean add(Class group) {
        String sqlStatement = classStatement.insertGroupStatement();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(group.getTeamName()),
                sqlStatement);
        return update(statement);
    }

    @Override
    public boolean delete(Class group) {
        String sqlStatement = classStatement.deleteGroupStatement();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(group.getId()), sqlStatement);
        return update(statement);
    }

    @Override
    public List<String> getGroupsNamesByMentorId(int mentorID) {
        String sqlStatement = classStatement.selectGroupsNamesByMentorId();
        List<String> groupNames = new ArrayList<>();
        try {
            PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(mentorID), sqlStatement);
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                groupNames.add(
                        resultSet.getString("name"));
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            disconnect();
        }
        return groupNames;
    }

    @Override
    public Map<Integer, Integer> getMentorAssignedToGroups() {
        String sqlStatement = classStatement.selectMentorAssignedToGroups();
        Map<Integer, Integer> groupMentor = new HashMap<>();

        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            ResultSet resultSet = query(statement);
            while (resultSet.next()) {
                groupMentor.put(
                        resultSet.getInt("id_class"),
                        resultSet.getInt("id_user")
                );
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            disconnect();
        }
        return groupMentor;
    }
}
