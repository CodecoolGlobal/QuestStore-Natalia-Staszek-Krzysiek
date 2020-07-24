package dao;

import dao.SQL.Database_Connection;
import dao.SQL.SQLUserDao;
import model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import Exceptions.DatabaseException;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import dao.SQL.SQLUserDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class UserDAOTest {
    private UserDAO userDAO;
    private User user1;
    private User user2;

@Mock
    Database_Connection mockDatabaseConnection;


    @BeforeAll
    void setUp() {
        Database_Connection database_connection = Mockito.mock(Database_Connection.class);

        Mockito.when(database_connection.getPreparedStatementBy("SELECT * FROM users WHERE id_role = ?;";);



                /*this.login = "fbznochzdwosyl";
        this.password = "95a8e2f8c07cf64b80500a788f87ec46f1d2eaf6b2b542034f25f4e0311b0e2f";
        this.database = "d3nuc8s3988iho";
        this.databaseURL = "jdbc:postgresql://ec2-54-75-246-118.eu-west-1.compute.amazonaws.com/";
        userDao = new UserDaoDb(connectionFactory);
        userDAO = new SQLUserDao();
   User user2 = new User(2, "Jarek", "kacz", "kacza@a.pl", "123456", "123456789", 2);
   userDAO.add(user1);*/

   }

 @Test
    void should_return_true_when_user_added() {
     User user1 = new User(1, "Adam", "ad",
             "a@a.pl", "123456", "123456789", 2);

     userDAO.add(user2);

     assertEquals(user2, this.userDAO.getById(2));


 }

    @Test
    void getAllByRole() {
    }

    @Test
    void getStudentsByGroupId() {
    }

    @Test
    void getById() {
    }

    @Test
    void getByLoginAndPassword() {
    }

    @Test
    void getByLoginAndRole() {
    }

    @Test
    void should_find_user_by_login() {
       /* when(mockDatabaseConnection.getPreparedStatementBy());
        User result = userDAO.getByLogin(user1.getLogin());
        assertEquals("ad", result);*/
   }


    @Test
    void getByEmail() {

    }

    @Test
    void getByPhoneNumber() {
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}