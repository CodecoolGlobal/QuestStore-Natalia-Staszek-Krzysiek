package dao;

import model.StudentDetails;
import model.User;

import java.util.List;

public interface StudentDetailsDAO {

    StudentDetails getStudentDataByStudentId(User user);
    List<StudentDetails> getAllStudentsData();
    List<StudentDetails> getStudentsDataByTeamName(String teamName);
    boolean add(StudentDetails student);
    boolean updateStudentData(StudentDetails student);
    void setDatabasePath(String path);
}