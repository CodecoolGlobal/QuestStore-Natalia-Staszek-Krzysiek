package dao;

import dao.SQL.Database_Connection;
import dao.SQL.SQLGroupDAO;
import model.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GroupDAOTest {

    private GroupDAO groupDAO;

    @BeforeEach
    void setUp() {

        Database_Connection database_connection = new Database_Connection();
        database_connection.connect();

        this.groupDAO = new SQLGroupDAO();
    }

    @Test
    void whenAddThenGroupIsAddedToDb() {
        Group expected = new Group(7,"Group1");
        this.groupDAO.add(expected);
        Group result = this.groupDAO.getByName(expected.getTeamName());
        assertEquals(expected, result);
        this.groupDAO.delete(expected);
    }

    @Test
    void whenDeleteThenGroupDeleted() {
        Group group = new Group(7,"Group1");
        this.groupDAO.add(group);
        this.groupDAO.delete(group);
        assertNull(this.groupDAO.getByName(group.getTeamName()));
    }

    @Test
    void whenGetAllThenReturnAllElements() {
        Group group1 = new Group(7,"Group1");
        Group group2 = new Group(8,"Group2");
        this.groupDAO.add(group1);
        this.groupDAO.add(group2);
        List<Group> results = this.groupDAO.getAll();

        Collections.sort(results, Comparator.comparingLong(Group::getId));

        assertEquals(group1, results.get(5));
        assertEquals(group2, results.get(6));
        this.groupDAO.delete(group1);
        this.groupDAO.delete(group2);
    }
}