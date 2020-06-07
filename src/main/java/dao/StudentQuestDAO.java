package dao;

public interface StudentQuestDAO {

    boolean add(int studentID, int taskID);
    void setDatabasePath(String path);
}