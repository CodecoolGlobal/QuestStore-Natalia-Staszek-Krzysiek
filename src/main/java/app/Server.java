package app;

import com.sun.net.httpserver.HttpServer;
import controller.LoginHandler;
import handler.MentorController;
import controller.Static;
import handler.RegistrationHandler;
import handler.StudentController;
import dao.SQL.SQLUserDao;
import handler.StudentController;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    public static void main(String[] args) throws IOException {
        int port = 8100;
        HttpServer server = HttpServer.create(new InetSocketAddress(8100), 0);
        server.createContext("/students", new StudentController());
        server.createContext("/mentors", new MentorController());
        server.createContext("/register", new RegistrationHandler());
        server.createContext("/static", new Static());
        server.createContext("/login", new LoginHandler());


        server.setExecutor(null);
        server.start();

        System.out.println("Server is running on port " + port);


    }
}
