package handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import dao.SQL.SQLUserDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import static org.junit.Assert.assertEquals;

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
        Mockito.when(httpExchangeMock.getResponseHeaders()).thenReturn(new Headers());
        Mockito.when(httpExchangeMock.getResponseBody()).thenReturn(new ByteArrayOutputStream(100));
        String expected = "[{\"id\":29,\"name\":\"Rysiu\",\"login\":\"Rysiu\",\"email\":\"ryszard@gmail.com\"," +
                "\"password\":\"123456\",\"phoneNumber\":\"123-123-123\",\"role\":2},{\"id\":2,\"name\":\"Franek\"," +
                "\"login\":\"Dolas\",\"email\":\"dolas.gmail.com\",\"password\":\"123456\"," +
                "\"phoneNumber\":\"604-604-604\",\"role\":2}]";
        mentorController.handle(httpExchangeMock);
        assertEquals(expected,mentorController.getResponse());

    }

}