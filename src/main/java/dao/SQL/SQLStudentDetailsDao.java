package dao.SQL;

import dao.StudentDetailsDAO;
import model.StudentDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SQLStudentDetailsDao extends Database_Connection implements StudentDetailsDAO {


    @Override
    public StudentDetails getStudentDataByStudentId(int student_id) {
        String sqlStatement = "SELECT * FROM students_details WHERE students_details.id_user=?";
        StudentDetails studentDetails = null;
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            statement.setInt(1, student_id);
            ResultSet resultSet = query(statement);
            while (resultSet.next()) {
                studentDetails = new StudentDetails(
                        resultSet.getInt("id_user"),
                        resultSet.getInt("wallet"),
                        resultSet.getInt("experience"),
                        resultSet.getInt("id_class"),
                        resultSet.getString("team_name")
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

        String sqlStatement = "SELECT * FROM students_details;";
        List<StudentDetails> students = new ArrayList<>();
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            ResultSet resultSet = query(statement);
            while (resultSet.next()) {
                students.add(new StudentDetails(
                        resultSet.getInt("id_user"),
                        resultSet.getInt("wallet"),
                        resultSet.getInt("experience"),
                        resultSet.getInt("id_class"),
                        resultSet.getString("team_name"))
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
        String sqlStatement = "SELECT * FROM students_details WHERE students_details.id_team=?;";
        List<StudentDetails> studentsTeam = new ArrayList<>();
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            statement.setString(1, teamName);
            ResultSet resultSet = query(statement);
            while (resultSet.next()) {
                studentsTeam.add(new StudentDetails(
                        resultSet.getInt("id_user"),
                        resultSet.getInt("wallet"),
                        resultSet.getInt("experience"),
                        resultSet.getInt("id_class"),
                        resultSet.getString("team_name"))
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
        String sqlStatement = "INSERT INTO students_details (id_user,wallet,experience,id_class,team_name) VALUES (?,?,?,?,?);";
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(student.getId(), student.getGroupId(),
                student.getWallet(), student.getExperience(),student.getTeamName()), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean updateStudentData(StudentDetails student) {
        if (student != null) {
            String sqlStatement = "UPDATE students_details SET id_user=?,wallet=?,experience=?,id_class=?,team_name=?;";
            PreparedStatement statement = getPreparedStatementBy(Arrays.asList(student.getId(), student.getWallet(), student.getExperience(), student.getGroupId(), student.getTeamName()), sqlStatement);
            return update(statement);

        } else { return false; }
    }

    @Override
    public void setDatabasePath(String path) {

    }
}
