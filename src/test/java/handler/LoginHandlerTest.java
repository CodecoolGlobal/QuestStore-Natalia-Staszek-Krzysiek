package handler;

import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.LoginService;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class LoginHandlerTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void mockTestHandle() throws IOException {
//        InputStream inputStream = new ByteArrayInputStream(testData.getBytes(StandardCharsets.UTF_8));
        HttpExchange httpExchange = mock(HttpExchange.class);
        LoginService login = mock(LoginService.class);
        controller.Static mockStatic = mock(controller.Static.class);

        LoginHandler loginHandler = new LoginHandler(login,mockStatic);
        when(httpExchange.getRequestMethod()).thenReturn("POST");
//        when(httpExchange.getResponseBody()).thenReturn("");

        loginHandler.handle(httpExchange);

        Mockito.verify(httpExchange.getRequestMethod());
    }
}