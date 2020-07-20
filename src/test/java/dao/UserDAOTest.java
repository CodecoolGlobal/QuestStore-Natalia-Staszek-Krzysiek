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
                3,
                "Marek",
                "Czarek",
                "czarek@gmail.com",
                "123456",
                "401-501-501",
                2
        );

        user2 = new User(
                4,
                "Dorota",
                "Psota",
                "psota@gmail.com",
                "123456",
                "604-204-604",
                2
        );
    }

    @Test
    void checkIfUserExistsInDatabase() {
        userDAO.add(user1);
        User result = userDAO.getByLogin(user1.getLogin());
        assertEquals(user1, result);
        userDAO.delete(user1);
    }

    @Test
    void updateUserInDbTest() {
        userDAO.add(user1);
        User expected = userDAO.getByLogin(user1.getLogin());
        expected.setName("ChangeForTest");
        userDAO.update(expected);
        User result = userDAO.getByLogin(expected.getLogin());
        assertEquals(expected, result);
        userDAO.delete(user1);
    }

    @Test
    void deleteUserFromDbTest() {
        userDAO.add(user1);
        User userFromDB = userDAO.getByLogin(user1.getLogin());
        userDAO.delete(userFromDB);
        User result = userDAO.getByLogin(user1.getLogin());
        assertEquals(null, result);
    }

    @Test
    void getByIdTest() {
        userDAO.add(user1);
        User result = userDAO.getById(3);
        assertEquals("Marek", result.getName());
        userDAO.delete(user1);
    }

    @Test
    void getByLoginAndPasswordTest() {

        User result = userDAO.getByLoginAndPassword(user1.getLogin(), user1.getPassword());

        assertEquals("Dulda", result.getLogin());
        assertEquals("123456", result.getPassword());
    }

    @Test
    void getByLoginAndRoleTest() {
        userDAO.add(user1);
        User result = userDAO.getByLoginAndRole(user1.getLogin(), user1.getRole());
        assertEquals("Czarek", result.getLogin());
        assertEquals(2, result.getRole());
        userDAO.delete(user1);
    }

    @Test
    void getByLoginTest() {
        User result = userDAO.getByLogin(user1.getLogin());

        assertEquals("Dulda", result.getLogin());
        assertEquals("Andrzej", result.getName());
    }

    @Test
    void getByEmailTest() {
        userDAO.add(user1);
        User result = userDAO.getByEmail(user1.getEmail());

        assertEquals("czarek@gmail.com", result.getEmail());
        assertEquals("Marek", result.getName());
        userDAO.delete(user1);
    }

    @Test
    void getByPhoneNumberTest() {
        userDAO.add(user1);
        User result = userDAO.getByPhoneNumber(user1.getPhoneNumber());

        assertEquals("401-501-501", result.getPhoneNumber());
        assertEquals("Czarek", result.getLogin());

        userDAO.delete(user1);
    }

    @Test
    void getAllByRoleTest() {

        userDAO.add(user1);
        userDAO.add(user2);

        List<User> result = userDAO.getAllByRole(2);

        assertEquals(user1, result.get(2));
        assertEquals(user2, result.get(3));

        userDAO.delete(user1);
        userDAO.delete(user2);

    }

    @Test
    void getAllTest() {
        userDAO.add(user1);
        userDAO.add(user2);

        List<User> result = userDAO.getAll();

        assertEquals(user1, result.get(19));
        assertEquals(user2, result.get(20));

        userDAO.delete(user1);
        userDAO.delete(user2);
    }
}