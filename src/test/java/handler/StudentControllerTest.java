package handler;

import dao.SQL.SQLUserDao;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentControllerTest {

    StudentController studentController;
    User user1 = new User();
    User user2 = new User();
    User user3 = new User();
    List<User> users = new ArrayList<>();

    @BeforeEach
    void setUp() {

        users.add(user1);
        users.add(user2);
        users.add(user3);
        studentController = new StudentController();

    }

    @Test
    void testReadUsers() {

        SQLUserDao sqlUserDao = mock(SQLUserDao.class);
        when(sqlUserDao.getAllByRole(3)).thenReturn(users);
        assertEquals(studentController.readAll(),users);
    }

    @Test
    void testReadUsersNull() {

        SQLUserDao sqlUserDao = mock(SQLUserDao.class);
        when(sqlUserDao.getAllByRole(3)).thenReturn(null);
        assertNull(studentController.read(40));
    }
}