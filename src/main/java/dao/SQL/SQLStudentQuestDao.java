package dao.SQL;

import dao.StudentQuestDAO;
import data.Database_Connection;
import data.statements.StudentQuestStatement;

import java.sql.PreparedStatement;
import java.util.Arrays;

public class SQLStudentQuestDao extends Database_Connection implements StudentQuestDAO {

    private StudentQuestStatement studentQuestStatement = new StudentQuestStatement();

    @Override
    public boolean add(int studentID, int taskID) {
        String sqlStatement = studentQuestStatement.insertConnectionStatement();
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(studentID, taskID), sqlStatement);
        return update(statement);
    }
}
