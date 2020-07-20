/*
package dao.SQL;

import dao.QuestDAO;
import model.Quest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SQLQuestDao extends Database_Connection implements QuestDAO {


    @Override
    public List<Quest> getTasksByStudentId(int id) {
        String sqlStatement = "SELECT name,points,description,category FROM quests JOIN quests " +
                "ON quests.id=students_quests.id_quest WHERE students_quests.id_user;";
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(id), sqlStatement);
        return getTasks(statement);
    }

    @Override
    public List<Quest> getAllQuests() {
        String sqlStatement = "SELECT * FROM quests;";
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
                        resultSet.getString("category")));
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
        String sqlStatement = "SELECT * FROM quests WHERE id = ?;";
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(id), sqlStatement);
        return getTask(statement);
    }

    @Override
    public Quest getQuestByName(String name) {
        String sqlStatement = "SELECT * FROM quests WHERE name = ?;";
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
                        resultSet.getInt("id_mentor"),
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

    */
/*
    * PRZYGTOWANE DANE - NIEKONIECZNIE WYSLANE, KWERENDA NIEUZUPELNIONA (CZESTY BLAD)
    * WKLEJANIE KOMENTARZU KODU WSKAZANE
    *
    * *//*


    @Override
    public boolean add(Quest task) {
        String sqlStatement = "INSERT INTO quests (id_mentor,name,points,description,category) VALUES (?,?,?,?,?);";
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(task.getId_mentor(), task.getName(), task.getPoints(),
                task.getDescription(), task.getCategory()), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean update(Quest task) {
        String sqlStatement = "UPDATE quests SET points=?,description=?,category=? WHERE name=?;";
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(task.getName(),task.getPoints(), task.getDescription(), task.getCategory()), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean delete(Quest task) {
        String sqlStatement = "DELETE FROM quests WHERE quests.id=?;";
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(task.getId()), sqlStatement);
        return update(statement);
    }
}
*/
