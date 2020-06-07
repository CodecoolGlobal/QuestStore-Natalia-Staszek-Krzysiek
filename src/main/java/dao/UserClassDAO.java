package dao;

public interface UserClassDAO {

    boolean add(int groupID, int mentorID);
    boolean delete(int groupID, int mentorID);
    boolean deleteBy(int mentorID);
}
