package dao.SQL;

import dao.QuestDAO;
import data.Database_Connection;
import data.statements.QuestStatement;
import model.Quest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SQLQuestDao extends Database_Connection implements QuestDAO {

    private QuestStatement questStatement = new QuestStatement();

    @Override
    public List<Quest> getTasksByStudentId(int id) {
        String sqlStatement = questStatement.selectTasksByStudentId();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(id), sqlStatement);
        return getTasks(statement);
    }

    @Override
    public List<Quest> getAll() {
        String sqlStatement = questStatement.selectAllTasks();
        PreparedStatement statement = getPreparedStatementBy(Collections.emptyList(), sqlStatement);
        return getTasks(statement);
    }

    private List<Quest> getTasks(PreparedStatement statement) {
        List<Quest> tasks = new ArrayList<>();
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                tasks.add(new Quest(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_mentor"),
                        resultSet.getString("name"),
                        resultSet.getInt("points"),
                        resultSet.getString("description"),
                        resultSet.getString("id_category")));
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            disconnect();
        }
        return tasks;
    }

    @Override
    public Quest getById(int id) {
        String sqlStatement = questStatement.selectTaskById();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(id), sqlStatement);
        return getTask(statement);
    }

    @Override
    public Quest getByName(String name) {
        String sqlStatement = questStatement.selectTaskByName();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(name), sqlStatement);
        return getTask(statement);
    }

    private Quest getTask(PreparedStatement statement) {
        Quest task = null;
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                task = new Quest(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("points"),
                        resultSet.getString("description"),
                        resultSet.getString("category"));
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            disconnect();
        }
        return task;
    }

    @Override
    public boolean add(Quest task) {
        String sqlStatement = questStatement.insertTaskStatement();
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(task.getName(), task.getPoints(),
                task.getDescription(), task.getCategory()), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean update(Quest task) {
        String sqlStatement = questStatement.updateTaskStatement();
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(task.getId(), task.getName(),
                task.getPoints(), task.getDescription(), task.getCategory(), task.getId()), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean delete(Quest task) {
        String sqlStatement = questStatement.deleteTaskStatement();
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(task.getId()), sqlStatement);
        return update(statement);
    }
}
