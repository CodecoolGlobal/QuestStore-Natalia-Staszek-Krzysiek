package handler;

import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;


class CookieHandlerTest {

    CookieHandler cookieHandler = new CookieHandler();

    @Test
    void handle() throws IOException {

        // Arrange
        HttpExchange mock = Mockito.mock(HttpExchange.class);
        Mockito.when(mock.getRequestHeaders().getFirst("Cookie")).thenReturn(null);

        //Act
        cookieHandler.handle(mock);

        // Assert
        String cookie = Mockito.verify(mock.getRequestHeaders()
                .getFirst("Cookie"), Mockito.times(1));
    }
}