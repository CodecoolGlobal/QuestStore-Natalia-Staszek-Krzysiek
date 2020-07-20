package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface GroupDAO {

    List<model.Group> getAll();
    model.Group getByName(String name);
    model.Group getById(int id);
    boolean add(model.Group group) throws SQLException;
    boolean delete(model.Group group);
    List<String> getGroupsNamesByMentorId(int mentorID);
    Map<Integer, Integer> getMentorAssignedToGroups();

}
