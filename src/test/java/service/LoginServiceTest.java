package service;

import dao.UserDAO;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginServiceTest {

    LoginService loginService;
    UserDAO userDAO = mock(UserDAO.class);
    User user;

    @BeforeEach
    void setUp() {
        loginService = new LoginService();
        user = new User(
                69,
                "Mock",
                "Mock",
                "mock@gmail.com",
                "123456",
                "101-101-501",
                2
        );
        userDAO.delete(user);
    }

    @Test
    public void testGetUser() {
        userDAO.add(user);
        when(userDAO.getByLogin(user.getLogin())).thenReturn(user);
        assertEquals(user, loginService.getUser("Mock", "123456"));
    }
}