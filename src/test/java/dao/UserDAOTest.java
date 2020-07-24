package dao;

import dao.SQL.Database_Connection;
import dao.SQL.SQLUserDao;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserDAOTest {

    private UserDAO userDAO;
    private User user1;
    private User user2;
    private SQLUserDao sqlUserDao;

    @BeforeEach
    void setUp() {
        Database_Connection database_connection = new Database_Connection();
        database_connection.connect();
        database_connection.disconnect();

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

        userDAO.delete(user1);
        userDAO.delete(user2);

    }
//
//    @Test
//    void mockTests(){
//        UserDAO mockUserDAO = mock(UserDAO.class);
//        when(mockUserDAO.getByLogin("GetThis")).thenReturn(user1);
////        assertTrue(mockUserDAO.getByLogin("Mocker")==user3);
//        assertTrue((mockUserDAO.getByLogin("GetThis"))==user1);
//
//    }

    @Test
    void checkIfUserExistsInDatabase() {
        userDAO.add(user1);
        User result = userDAO.getByLogin(user1.getLogin());
        assertEquals(user1, result);
//        userDAO.delete(user1);
    }

    @Test
    void updateUserInDbTest() {
        userDAO.add(user1);
        User expected = userDAO.getByLogin(user1.getLogin());
        expected.setName("ChangeForTest");
        userDAO.update(expected);
        User result = userDAO.getByLogin(expected.getLogin());
        assertEquals(expected, result);
//        userDAO.delete(user1);
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
//        userDAO.delete(user1);
    }

    @Test
    void getByLoginAndPasswordTest() {
        userDAO.add(user1);
        User result = userDAO.getByLoginAndPassword(user1.getLogin(), user1.getPassword());
        assertEquals("Czarek", result.getLogin());
        assertEquals("123456", result.getPassword());
//        userDAO.delete(user1);
    }

    @Test
    void getByLoginAndRoleTest() {
        userDAO.add(user1);
        User result = userDAO.getByLoginAndRole(user1.getLogin(), user1.getRole());
        assertEquals("Czarek", result.getLogin());
        assertEquals(2, result.getRole());
//        userDAO.delete(user1);
    }

    @Test
    void getByLoginTest() {
        userDAO.add(user1);
        User result = userDAO.getByLogin(user1.getLogin());

        assertEquals("Czarek", result.getLogin());
        assertEquals("Marek", result.getName());
//        userDAO.delete(user1);
    }

    @Test
    void getByEmailTest() {
        userDAO.add(user1);
        User result = userDAO.getByEmail(user1.getEmail());

        assertEquals("czarek@gmail.com", result.getEmail());
        assertEquals("Marek", result.getName());
//        userDAO.delete(user1);
    }

    @Test
    void getByPhoneNumberTest() {
        userDAO.add(user1);
        User result = userDAO.getByPhoneNumber(user1.getPhoneNumber());

        assertEquals("401-501-501", result.getPhoneNumber());
        assertEquals("Czarek", result.getLogin());

//        userDAO.delete(user1);
    }

    @Test
    void getAllByRoleTest() {

        userDAO.add(user1);
        userDAO.add(user2);

        List<User> results = userDAO.getAllByRole(2);

        assertTrue(results.contains(user1));
        assertTrue(results.contains(user2));

//        userDAO.delete(user1);
//        userDAO.delete(user2);

    }

    @Test
    void getAllTest() {
        userDAO.add(user1);
        userDAO.add(user2);

        List<User> results = userDAO.getAll();

//        Collections.sort(results, Comparator.comparingLong(User::getId));

        assertTrue(results.contains(user1));
        assertTrue(results.contains(user2));

//        assertEquals(user1, results.get(2));
//        assertEquals(user2, results.get(3));

//        userDAO.delete(user1);
//        userDAO.delete(user2);
    }

}