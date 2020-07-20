/*
package dao.SQL;

import dao.StudentItemDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SQLStudentItemDao extends Database_Connection implements StudentItemDAO {


    @Override
    public boolean add(int studentId, int itemId, boolean isUsed) {
        String sqlStatement = "INSERT INTO students_items (id_item,id_student_details,is_used) VALUES (?,?,?);";
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(studentId, itemId, isUsed), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean markItemAsUsed(int studentId, int itemId) {
        String sqlStatement = "UPDATE students_items SET is_used = true WHERE id_student_details = ? AND id_item = ?;";
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(studentId, itemId), sqlStatement);
        return update(statement);
    }

    @Override
    public List<Integer> getStudentItemsIdsBy(int studentID) {
        String sqlStatement = "SELECT id_item FROM students_items WHERE id_student_details=?;";
        List<Integer> studentsItems = new ArrayList<>();

        try {
            PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(studentID), sqlStatement);
            ResultSet resultSet = query(statement);

            while (resultSet.next()) {
                studentsItems.add(resultSet.getInt("id_item"));
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
        String sqlStatement = "DELETE FROM students_items WHERE students_items.id_item IN SELECT id FROM items " +
                "JOIN students_items ON items.id = students_items.id_item WHERE items.id_category = ?;";
        PreparedStatement statement = getPreparedStatementBy(Collections.emptyList(), sqlStatement);
        return update(statement);
    }
}*/
