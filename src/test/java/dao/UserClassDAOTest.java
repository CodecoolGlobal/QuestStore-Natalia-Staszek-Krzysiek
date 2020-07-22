package dao;

import dao.SQL.Database_Connection;
import dao.SQL.SQLGroupDAO;
import dao.SQL.SQLUserDao;
import dao.SQL.SQLUserGroupDao;
import model.Group;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserClassDAOTest {

    private GroupDAO groupDAO;
    private UserClassDAO userClassDAO;
    private UserDAO userDAO;

    @BeforeEach
    void setUp() {
        Database_Connection database_connection = new Database_Connection();
        database_connection.connect();
        database_connection.disconnect();
        userClassDAO = new SQLUserGroupDao();
        groupDAO = new SQLGroupDAO();
        userDAO = new SQLUserDao();
    }
    @Test
    void whenAddThenMentorGroupIsAddedToDb() {
        Group group1 = new Group(7,"Group1");
        Group group2 = new Group(8,"Group2");
        User mentor = new User(3, "Mentor", "Mentor", "mentor@email.com", "mentor",
                "666555666", 2);

        this.userDAO.add(mentor);
        this.groupDAO.add(group1);
        this.groupDAO.add(group2);

        this.userClassDAO.add(group1.getId(), mentor.getId());
        this.userClassDAO.add(group2.getId(), mentor.getId());

        List<String> groupNames = this.groupDAO.getGroupsNamesByMentorId(mentor.getId());
        Collections.sort(groupNames);

        assertEquals(group1.getTeamName(), groupNames.get(0));
        assertEquals(group2.getTeamName(), groupNames.get(1));

        this.userClassDAO.delete(group1.getId(), mentor.getId());
        this.userClassDAO.delete(group2.getId(), mentor.getId());

        this.groupDAO.delete(group1);
        this.groupDAO.delete(group2);

        this.userDAO.delete(mentor);

    }

    @Test
    void whenDeleteThenMentorGroupIsDeleted() {
        Group group1 = new Group(7,"Group1");
        User mentor = new User(3, "Mentor", "Mentor", "mentor@email.com", "mentor",
                "666555666", 2);
        this.userDAO.add(mentor);
        this.groupDAO.add(group1);
        this.userClassDAO.add(group1.getId(), mentor.getId());
        this.userClassDAO.delete(group1.getId(), mentor.getId());

        assertTrue(this.groupDAO.getGroupsNamesByMentorId(mentor.getId()).isEmpty());

        this.groupDAO.delete(group1);
        this.userDAO.delete(mentor);


    }
}