package dao.SQL;

import dao.StudentQuestDAO;

import java.sql.PreparedStatement;
import java.util.Arrays;

public class SQLStudentQuestDao extends Database_Connection implements StudentQuestDAO {


    @Override
    public boolean add(int studentID, int taskID) {
        String sqlStatement = "INSERT INTO students_quests (id_user,id_quest) VALUES (?,?);";
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(studentID, taskID), sqlStatement);
        return update(statement);
    }
}
