package dao.SQL;

import dao.StudentItemDAO;
import data.Database_Connection;
import data.statements.StudentItemStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SQLStudentItemDao extends Database_Connection implements StudentItemDAO {

    private StudentItemStatement studentItemStatement = new StudentItemStatement();

    @Override
    public boolean add(int studentId, int itemId, int isUsed) {
        String sqlStatement = studentItemStatement.addStudentItemConnection();
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(studentId, itemId, isUsed), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean markItemAsUsed(int studentId, int itemId) {
        String sqlStatement = studentItemStatement.markItemAsUsed();
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(studentId, itemId), sqlStatement);
        return update(statement);
    }

    @Override
    public List<Integer> getStudentItemsIdsBy(int studentID) {
        String sqlStatement = studentItemStatement.getStudentsItems();
        List<Integer> studentsItems = new ArrayList<>();

        try {
            PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(studentID), sqlStatement);
            ResultSet resultSet = query(statement);

            while (resultSet.next()) {
                studentsItems.add(resultSet.getInt("id"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            disconnect();
        }
        return studentsItems;
    }

    @Override
    public boolean removeTeamItems() {
        String sqlStatement = studentItemStatement.deleteTeamItemsStatement();
        PreparedStatement statement = getPreparedStatementBy(Collections.emptyList(), sqlStatement);
        return update(statement);
    }
}