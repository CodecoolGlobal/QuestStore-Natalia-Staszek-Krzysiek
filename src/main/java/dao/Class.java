package dao;

import java.util.List;
import java.util.Map;

public interface Class {

    List<Class> getAll();
    Class getByName(String name);
    Class getById(int id);
    boolean add(Class group);
    boolean delete(Class group);
    List<String> getGroupsNamesByMentorId(int mentorID);
    Map<Integer, Integer> getMentorAssignedToGroups();
    void setDatabasePath(String path);

}
