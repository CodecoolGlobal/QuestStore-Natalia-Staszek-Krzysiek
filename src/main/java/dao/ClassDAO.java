package dao;

import model.Class;

import java.util.List;
import java.util.Map;

public interface ClassDAO {

    List<Class> getAll();
    Class getByName(String name);
    Class getById(int id);
    boolean add(Class group);
    boolean delete(Class group);
    List<String> getGroupsNamesByMentorId(int mentorID);
    Map<Integer, Integer> getMentorAssignedToGroups();

}
