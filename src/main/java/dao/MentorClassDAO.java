package dao;

public interface MentorClassDAO {

    boolean add(int groupID, int mentorID);
    boolean delete(int groupID, int mentorID);
    boolean deleteBy(int mentorID);
    void setDatabasePath(String path);
}
