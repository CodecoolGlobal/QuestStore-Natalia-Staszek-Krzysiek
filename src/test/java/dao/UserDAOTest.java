package dao;

import dao.SQL.Database_Connection;
import dao.SQL.SQLUserDao;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDAOTest {

    private UserDAO userDAO;
    private User user1;
    private User user2;
    private User user3;
    private static final String DATABASE_PATH = "testDb.db";

    @BeforeEach
    void setUp() {
        Database_Connection database_connection = new Database_Connection();
        database_connection.connect();

        userDAO = new SQLUserDao();

        user1 = new User(
                1,
                "Andrzej",
                "Dulda",
                "andrzej.dulda@pis.pl",
                "123456",
                "501-501-501",
                1
        );

        user2 = new User(
                2,
                "Franek",
                "Dolas",
                "dolas.gmail.com",
                "123456",
                "604-604-604",
                2
        );
        user3 = new User(
                29,
                "Rysiu",
                "Rysiu",
                "ryszard@gmail.com",
                "123456",
                "123-123-123",
                2
        );
    }

    @Test
    void checkIfUserExistsInDatabase() {
        User result = userDAO.getByLogin("Dulda");
        assertEquals(user1, result);
    }

    @Test
    void getByIdTest() {
        User result = userDAO.getById(1);
        assertEquals("Andrzej", result.getName());
    }

    @Test
    void getByLoginAndPasswordTest() {

        User result = userDAO.getByLoginAndPassword(user1.getLogin(), user1.getPassword());

        assertEquals("Dulda", result.getLogin());
        assertEquals("123456", result.getPassword());
    }

    @Test
    void getByLoginAndRoleTest() {
        User result = userDAO.getByLoginAndRole(user1.getLogin(), user1.getRole());

        assertEquals("Dulda", result.getLogin());
        assertEquals(1, result.getRole());
    }

    @Test
    void getByLoginTest() {
        User result = userDAO.getByLogin(user1.getLogin());

        assertEquals("Dulda", result.getLogin());
        assertEquals("Andrzej", result.getName());
    }

    @Test
    void getByEmailTest() {
        User result = userDAO.getByEmail(user1.getEmail());

        assertEquals("andrzej.dulda@pis.pl", result.getEmail());
        assertEquals("Andrzej", result.getName());
    }

    @Test
    void getByPhoneNumberTest() {
        User result = userDAO.getByPhoneNumber(user1.getPhoneNumber());

        assertEquals("501-501-501", result.getPhoneNumber());
        assertEquals("Dulda", result.getLogin());
    }

    @Test
    void getAllByRoleTest() {
        List<User> result = userDAO.getAll();

        assertEquals(user3, result.get(0));
        assertEquals(user2, result.get(1));

    }

    @Test
    void getAllTest() {

        List<User> result = userDAO.getAll();

        assertEquals(user3, result.get(1));
    }
}