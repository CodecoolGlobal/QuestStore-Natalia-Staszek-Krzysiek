package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.SQL.SQLUserDao;
import helpers.Parser;
import model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

public class RegistrationHandler implements HttpHandler {

    private final SQLUserDao sqlUserDao;

    public RegistrationHandler() {
        sqlUserDao = new SQLUserDao();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";
        String method = exchange.getRequestMethod();
        exchange.getResponseHeaders().put("Content-Type", Collections.singletonList("application/json"));
        exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));

        if (method.equals("POST")) {
            InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String from = bufferedReader.readLine();
            System.out.println(from);

            Map<String, String> data = Parser.parseFormData(from);

            User user = new User();
            user.setName(data.get("name"));
            user.setLogin(data.get("login"));
            user.setEmail(data.get("email"));
            user.setPassword(data.get("password"));
            user.setPhoneNumber(data.get("phone_number"));
            user.setRole(4);

            sqlUserDao.add(user);

        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }
}
