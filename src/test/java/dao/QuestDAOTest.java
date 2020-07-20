package dao;

import dao.SQL.Database_Connection;
import dao.SQL.SQLQuestDao;
import model.Quest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestDAOTest {

    private QuestDAO questDAO;
    private Quest expected;

    @BeforeEach
    void setUp() {
        Database_Connection database_connection = new Database_Connection();
        database_connection.connect();

        this.questDAO = new SQLQuestDao();

//        expected = new Quest(8, 2, "Archery", 12505, "Shooting moving targets","BASIC");

    }

    @Test
    void whenAddThenTaskIsAddedToDb() {
        Quest expected = new Quest(9,2, "Task", 300, "Simple task", "BASIC");
        this.questDAO.add(expected);
        Quest result = this.questDAO.getQuestByName(expected.getName());
        assertEquals(expected, result);
        this.questDAO.delete(expected);
    }

    @Test
    void whenUpdateThenTaskIsUpdated() {
        Quest expected = new Quest(9,2, "Task", 300, "Simple task", "BASIC");
        this.questDAO.add(expected);
        expected.setPoints(500);
        expected.setDescription("New description");
//        expected.setName("New name");
        expected.setCategory("New category");
        this.questDAO.update(expected);
        Quest result = this.questDAO.getQuestByName("Task");
        assertEquals(expected,result);
        this.questDAO.delete(expected);
    }

    @Test
    void whenGetAllThenReturnAllElements() {
        Quest quest1 = new Quest(9,1, "Task1", 300, "Simple task", "BASIC");
        Quest quest2 = new Quest(10,2, "Task2", 350, "Simple2 task", "ADVANCED");
        this.questDAO.add(quest1);
        this.questDAO.add(quest2);
        List<Quest> results = this.questDAO.getAllQuests();
        assertEquals(quest1, results.get(6));
        assertEquals(quest2, results.get(7));
        this.questDAO.delete(quest1);
        this.questDAO.delete(quest2);
    }
}