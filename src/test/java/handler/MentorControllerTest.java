package handler;

import Exceptions.DatabaseException;
import com.sun.net.httpserver.HttpExchange;
import dao.SQL.SQLUserDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class MentorControllerTest {

MentorController mentorController = new MentorController();
    @Test
    void handle() throws IOException, DatabaseException {

        //Arrange

        HttpExchange httpExchangeMock = Mockito.mock(HttpExchange.class);
        SQLUserDao userDaoMock = Mockito.mock(SQLUserDao.class);
        OutputStream osMock = Mockito.mock(OutputStream.class);


        Mockito.when(httpExchangeMock.getRequestURI()).thenReturn(URI.create("/test"));
        Mockito.when(userDaoMock.getAllByRole(2)).thenThrow(new DatabaseException("Test exception"));
        Mockito.when(osMock.write("test".getBytes())).thenReturn("Test ".getBytes());
        mentorController.handle(httpExchangeMock);
       String testVar =  mentorController.response;
        System.out.println(testVar);

    }
}