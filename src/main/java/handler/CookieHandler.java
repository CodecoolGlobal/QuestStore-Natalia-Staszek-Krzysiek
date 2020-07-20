package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import helpers.CookieHelper;
import model.User;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.util.*;

public class CookieHandler implements HttpHandler {
    private static final String SESSION_COOKIE_NAME = "sessionId";

    CookieHelper cookieHelper = new CookieHelper();
    Map<String, User> sessionStorage = new HashMap<>();

//    public boolean isLoggedIn(String cookie) {
//        return false;
//    }
//
//    public UserType getUserType(String cookie) {
//
//    }
//
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        Optional<HttpCookie> cookie = getSessionIdCookie(httpExchange);

        boolean isNewSession;
        if (cookie.isPresent()) {  // Cookie already exists
            isNewSession = false;
        } else { // Create a new cookie
            isNewSession = true;
            String sessionId = String.valueOf(UUID.randomUUID()); // This isn't a good way to create sessionId. Find a better one!
            cookie = Optional.of(new HttpCookie(SESSION_COOKIE_NAME, sessionId));
            httpExchange.getResponseHeaders().add("Set-Cookie", cookie.get().toString());
//            sessionStorage.put(sessionId, user);
        }

        String response = "\n isNewSession: " + isNewSession;
        response += "\n session id: " + cookie.get().getValue();
        sendResponse(httpExchange, response);
    }

    private Optional<HttpCookie> getSessionIdCookie(HttpExchange httpExchange){
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        List<HttpCookie> cookies = cookieHelper.parseCookies(cookieStr);
        return cookieHelper.findCookieByName(SESSION_COOKIE_NAME, cookies);
    }

    private void sendResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }
}