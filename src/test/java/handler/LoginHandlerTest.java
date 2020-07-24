package handler;

import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.LoginService;

import java.io.IOException;
import java.io.OutputStream;

import static org.mockito.Mockito.*;


class LoginHandlerTest {

    //    ten test sprawdza czy zostala wywolana metoda na refernecji klasy mockStatic -> sendFileAsResponse
//
    @Test
    void mockTestHandleGet() throws IOException {
//        given
        HttpExchange httpExchange = mock(HttpExchange.class);
        LoginService login = mock(LoginService.class);
        controller.Static mockStatic = mock(controller.Static.class);
        OutputStream mockOutputStream = mock(OutputStream.class);
        LoginHandler loginHandler = new LoginHandler(login, mockStatic);

//        when
        when(httpExchange.getRequestMethod()).thenReturn("GET");
        when(httpExchange.getResponseBody()).thenReturn(mockOutputStream);
        loginHandler.handle(httpExchange);

//        then
        Mockito.verify(mockStatic, times(1)).sendFileAsResponse(anyObject(), anyString());
    }

    @Test
    void mockTestHandleLet() throws IOException {
//        given
        HttpExchange httpExchange = mock(HttpExchange.class);
        LoginService login = mock(LoginService.class);
        controller.Static mockStatic = mock(controller.Static.class);
        OutputStream mockOutputStream = mock(OutputStream.class);
        LoginHandler loginHandler = new LoginHandler(login, mockStatic);

//        when
        when(httpExchange.getRequestMethod()).thenReturn("POST");
        when(httpExchange.getResponseBody()).thenReturn(mockOutputStream);
        loginHandler.handle(httpExchange);

//        then
        Mockito.verify(mockStatic, times(1)).sendFileAsResponse(anyObject(), anyString());
    }
}