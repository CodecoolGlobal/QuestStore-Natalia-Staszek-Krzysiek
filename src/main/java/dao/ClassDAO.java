package dao;

import java.util.List;
import java.util.Map;

public interface ClassDAO {

    List<ClassDAO> getAll();
    ClassDAO getByName(String name);
    ClassDAO getById(int id);
    boolean add(ClassDAO group);
    boolean delete(ClassDAO group);
    List<String> getGroupsNamesByMentorId(int mentorID);
    Map<Integer, Integer> getMentorAssignedToGroups();
    void setDatabasePath(String path);

}
