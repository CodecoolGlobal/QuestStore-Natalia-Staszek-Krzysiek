package dao;

import java.util.List;
import java.util.Map;

public interface GroupDAO {

    List<model.Group> getAll();
    model.Group getByName(String name);
    model.Group getById(int id);
    boolean add(model.Group group);
    boolean delete(model.Group group);
    List<String> getGroupsNamesByMentorId(int mentorID);
    Map<Integer, Integer> getMentorAssignedToGroups();

}
