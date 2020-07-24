package handler;

import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

class MentorControllerTest {

    MentorController mentorController;

    @BeforeEach
    void setUp() {
        this.mentorController = new MentorController();
    }


    //    metoda sprawdza wywolanie endpointu test/test , porownuje body payload
    @Test
    void handleTest1() throws IOException {

        //then
        HttpExchange httpExchangeMock = mock(HttpExchange.class);
        OutputStream outputStream = mock(OutputStream.class);

        //when
        Mockito.when(httpExchangeMock.getRequestURI()).thenReturn(URI.create("test/test"));
        Mockito.when(httpExchangeMock.getResponseBody()).thenReturn(outputStream);

        String expected = "[{\"id\":29,\"name\":\"Rysiu\",\"login\":\"Rysiu\",\"email\":\"ryszard@gmail.com\"" +
                ",\"password\":\"123456\",\"phoneNumber\":\"123-123-123\",\"role\":2},{\"id\":3,\"name\":\"Marek" +
                "\",\"login\":\"Czarek\",\"email\":\"czarek@gmail.com\",\"password\":\"123456\",\"phoneNumber\":\"" +
                "401-501-501\",\"role\":2},{\"id\":2,\"name\":\"Franek\",\"login\":\"Dolas\",\"email\":\"dolas.gma" +
                "il.com\",\"password\":\"123456\",\"phoneNumber\":\"604-604-604\",\"role\":2}]";

//        then
        mentorController.handle(httpExchangeMock);
        assertEquals(expected, mentorController.getResponse());
    }

    //    metoda sprawdza wywolanie endpointu 1521/345345/29 , porownuje body payload
    @Test
    void handleTest2() throws IOException {

        //then
        HttpExchange httpExchangeMock = mock(HttpExchange.class);
        OutputStream outputStream = mock(OutputStream.class);

        //when
        Mockito.when(httpExchangeMock.getRequestURI()).thenReturn(URI.create("1521/345345/29"));
        Mockito.when(httpExchangeMock.getResponseBody()).thenReturn(outputStream);

        String expected = "{\"id\":29,\"name\":\"Rysiu\",\"login\":\"Rysiu\",\"email\":\"r" +
                "yszard@gmail.com\",\"password\":\"123456\",\"phoneNumber\":\"123-123-123\",\"role\":2}";

//        then
        mentorController.handle(httpExchangeMock);
        assertEquals(expected, mentorController.getResponse());
    }


}