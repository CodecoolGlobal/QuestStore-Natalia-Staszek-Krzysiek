package app;

import com.sun.net.httpserver.HttpServer;
import controller.StudentController;
import dao.SQL.SQLUserDao;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    public static void main(String[] args) throws IOException {
        int port = 9000;
        HttpServer server = HttpServer.create(new InetSocketAddress(9000), 0);

        server.createContext("/students", new StudentController());

        server.setExecutor(null);
        server.start();

        System.out.println("Server is running on port " + port);


    }
}
