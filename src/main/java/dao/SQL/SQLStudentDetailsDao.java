package dao.SQL;

import dao.StudentDetailsDAO;
import data.Database_Connection;
import data.statements.StudentDetailsStatement;
import model.StudentDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SQLStudentDetailsDao extends Database_Connection implements StudentDetailsDAO {

    private StudentDetailsStatement studentDetailsStatement = new StudentDetailsStatement();

    @Override
    public StudentDetails getStudentDataByStudentId(int student_id) {
        String sqlStatement = studentDetailsStatement.getStudentDataById();
        StudentDetails studentDetails = null;
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            statement.setInt(1, student_id);
            ResultSet resultSet = query(statement);
            while (resultSet.next()) {
                studentDetails = new StudentDetails(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_class"),
                        resultSet.getString("team_name"),
                        resultSet.getInt("level"),
                        resultSet.getInt("wallet"),
                        resultSet.getInt("experience")
                );
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            disconnect();
        }
        return studentDetails;
    }

    @Override
    public List<StudentDetails> getAllStudentsData() {

        String sqlStatement = studentDetailsStatement.getAllStudents();
        List<StudentDetails> students = new ArrayList<>();
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            ResultSet resultSet = query(statement);
            while (resultSet.next()) {
                students.add(new StudentDetails(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_class"),
                        resultSet.getString("team_name"),
                        resultSet.getInt("level"),
                        resultSet.getInt("wallet"),
                        resultSet.getInt("experience"))
                );
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            disconnect();
        }
        return students;
    }

    @Override
    public List<StudentDetails> getStudentsDataByTeamName(String teamName) {
        String sqlStatement = studentDetailsStatement.getStudentsInSameTeam();
        List<StudentDetails> studentsTeam = new ArrayList<>();
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            statement.setString(1, teamName);
            ResultSet resultSet = query(statement);
            while (resultSet.next()) {
                studentsTeam.add(new StudentDetails(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_class"),
                        resultSet.getString("team_name"),
                        resultSet.getInt("level"),
                        resultSet.getInt("wallet"),
                        resultSet.getInt("experience"))
                );
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            disconnect();
        }
        return studentsTeam;
    }

    @Override
    public boolean add(StudentDetails student) {
        String sqlStatement = studentDetailsStatement.createStudentData();
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(student.getId(), student.getClass(),
                student.getTeamName(),student.getLevel(), student.getWallet(), student.getExperience()), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean updateStudentData(StudentDetails student) {
        if (student != null) {
            String sqlStatement = studentDetailsStatement.updateStudentData();
            PreparedStatement statement = getPreparedStatementBy(Arrays.asList(student.getGroupId(), student.getTeamName(),
                    student.getLevel(), student.getWallet(), student.getExperience(), student.getId()), sqlStatement);
            return update(statement);

        } else { return false; }
    }

    @Override
    public void setDatabasePath(String path) {

    }
}
