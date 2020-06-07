package dao;

import model.Codecooler;

import java.util.List;

public interface StudentDetailsDAO {

    Codecooler getStudentDataByStudentId(int student_id);
    List<Codecooler> getAllStudentsData();
    List<Codecooler> getStudentsDataByTeamName(String teamName);
    boolean add(Codecooler student);
    boolean updateStudentData(Codecooler student);
    void setDatabasePath(String path);
}