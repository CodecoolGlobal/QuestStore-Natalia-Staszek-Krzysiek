package dao.SQL;

import dao.StudentDetailsDAO;
import data.Database_Connection;
import data.statements.StudentDetailsStatement;
import model.StudentDetails;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SQLStudentDetailsDao extends Database_Connection implements StudentDetailsDAO {

    private StudentDetailsStatement studentDetailsStatement = new StudentDetailsStatement();

    @Override
    public StudentDetails getStudentDataByStudentId(User user) {
        String sqlStatement = "SELECT * FROM students_details WHERE students_details.id_user=?";
        StudentDetails studentDetails = null;
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            statement.setInt(1, user.getId());
            ResultSet resultSet = query(statement);
            while (resultSet.next()) {
                studentDetails = new StudentDetails(
                        user,
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

        String sqlStatement = "SELECT * FROM students_details sd JOIN user u ON u.id = sd.id_user;";
        List<StudentDetails> students = new ArrayList<>();
        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            ResultSet resultSet = query(statement);
            while (resultSet.next()) {
                students.add(generateStudentsDetails(resultSet));
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
        String sqlStatement = "SELECT * FROM students_details sd JOIN user u ON u.id = sd.id_user WHERE sd.id_team=?;";
        List<StudentDetails> studentsTeam = new ArrayList<>();

        try {
            PreparedStatement statement = getPreparedStatement(sqlStatement);
            statement.setString(1, teamName);
            ResultSet resultSet = query(statement);
            while (resultSet.next()) {
                studentsTeam.add(generateStudentsDetails(resultSet));
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
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(student.getUser().getId(), student.getGroupId(),
                student.getWallet(), student.getExperience(), student.getTeamName()), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean updateStudentData(StudentDetails student) {
        if (student != null) {
            String sqlStatement = studentDetailsStatement.updateStudentData();
            PreparedStatement statement = getPreparedStatementBy(Arrays.asList(student.getUser().getId(), student.getWallet(), student.getExperience(), student.getGroupId(), student.getTeamName()), sqlStatement);
            return update(statement);
        }
        return false;
    }

    @Override
    public void setDatabasePath(String path) {

    }

    private User generateUser(ResultSet userResultSet) throws SQLException {
        return new User(
                userResultSet.getInt("id"),
                userResultSet.getString("name"),
                userResultSet.getString("login"),
                userResultSet.getString("email"),
                userResultSet.getString("password"),
                userResultSet.getString("phone_number"),
                userResultSet.getInt("id_role")
        );
    }

    private StudentDetails generateStudentsDetails(ResultSet resultSet) throws SQLException {
        return new StudentDetails(
                generateUser(resultSet),
                resultSet.getInt("wallet"),
                resultSet.getInt("experience"),
                resultSet.getInt("id_class"),
                resultSet.getString("team_name"));
    }
}
