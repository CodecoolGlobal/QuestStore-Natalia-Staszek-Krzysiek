package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controller.Static;
import helpers.Parser;
import model.User;
import service.LoginService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class LoginHandler implements HttpHandler {

    private final Static aStatic;
    private LoginService login;
    private static final int STUDENT = 1;
    private static final int MENTOR = 2;
    private static final int CREEPUGUY = 3;

    public LoginHandler(Static aStatic) {
        this.aStatic = aStatic;
        login = new LoginService();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "";
        String method = httpExchange.getRequestMethod();
        System.out.println(method);

        if (method.equals("POST")) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            System.out.println(formData);
            Map<String, String> inputs = Parser.parseFormData(formData);

            System.out.println(inputs.get("login"));
            System.out.println(inputs.get("password"));

            String nextUrl = "";

            User user = login.getUser(inputs.get("login"), inputs.get("password"));
            int userType = user.getRole();

            if (userType == 3) {
                nextUrl = "http://localhost:8100/static/creep.html";
            }
            if ((userType == 2)) {
                nextUrl = "http://localhost:8100/static/creep.html";
            }
            if (userType == 1) {
                nextUrl = "http://localhost:8100/static/creep.html";
            }

            httpExchange.getResponseHeaders().add("Location", nextUrl);
            httpExchange.sendResponseHeaders(302, response.getBytes().length);
        } else if (method.equals("GET")) {
            aStatic.sendFileAsResponse(httpExchange, "./static/login.html");
        }

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
