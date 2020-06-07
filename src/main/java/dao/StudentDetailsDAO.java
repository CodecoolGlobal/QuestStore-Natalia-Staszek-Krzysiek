package dao;

import model.StudentDetails;

import java.util.List;

public interface StudentDetailsDAO {

    StudentDetails getStudentDataByStudentId(int student_id);
    List<StudentDetails> getAllStudentsData();
    List<StudentDetails> getStudentsDataByTeamName(String teamName);
    boolean add(StudentDetails student);
    boolean updateStudentData(StudentDetails student);
    void setDatabasePath(String path);
}