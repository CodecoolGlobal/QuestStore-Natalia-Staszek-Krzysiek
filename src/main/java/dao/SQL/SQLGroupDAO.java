package dao.SQL;

import dao.GroupDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SQLGroupDAO extends Database_Connection implements GroupDAO {


    @Override
    public List<model.Group> getAll() {
        String sqlStatement = "SELECT * FROM classes WHERE name != 'ANYTHING BUT THIS!';" ;
        List<model.Group> groups = new ArrayList<>();
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                groups.add(new model.Group(
                        resultSet.getInt("id"),
                        resultSet.getString("name")));
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            disconnect();
        }
        return groups;
    }

    @Override
    public model.Group getByName(String name) {
        String sqlStatement = "SELECT * FROM classes WHERE name = ?;" ;
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(name), sqlStatement);
        return getClass(statement);
    }

    @Override
    public model.Group getById(int id) {
        String sqlStatement = "SELECT * FROM classes WHERE id = ?;" ;
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(id), sqlStatement);
        return getClass(statement);
    }

    private model.Group getClass(PreparedStatement statement) {
        model.Group aGroup = null;
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                aGroup = new model.Group(
                        resultSet.getInt("id"),
                        resultSet.getString("name"));
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            disconnect();
        }
        return aGroup;
    }

    @Override
    public boolean add(model.Group group) {
        String sqlStatement = "INSERT INTO classes (name) VALUES (?);" ;
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(group.getTeamName()),
                sqlStatement);
        return update(statement);
    }

    @Override
    public boolean delete(model.Group group) {
        String sqlStatement = "DELETE FROM classes WHERE id=?;";
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(group.getId()), sqlStatement);
        return update(statement);
    }

    @Override
    public List<String> getGroupsNamesByMentorId(int mentorID) {
        String sqlStatement = "SELECT * FROM classes JOIN users_classes ON classes.id = users_classes.id_class " +
                "WHERE users_classes.id_user = ?;";
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
        String sqlStatement = "SELECT * FROM users_classes;";
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
