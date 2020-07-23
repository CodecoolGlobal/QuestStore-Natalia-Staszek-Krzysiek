package handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.ItemDAO;
import dao.SQL.SQLUserDao;
import dao.StudentDetailsDAO;
import dao.StudentItemDAO;
import dao.UserDAO;
import model.User;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

public class StudentController implements Controller<User>, HttpHandler {

    private StudentDetailsDAO studentDetailsDAO;
    private StudentItemDAO studentItemDAO;
    //    private ExpLevelsDAO expLevelsDAO;
    private UserDAO userDAO;
    private ItemDAO itemDAO;

    private final SQLUserDao sqlUserDao = new SQLUserDao();

    public StudentController(){
    }

    @Override
    public boolean create(User user) {
        return sqlUserDao.add(user);
    }

    @Override
    public List<User> readAll() {
        return sqlUserDao.getAllByRole(3);
    }

    //mokuje sqluser dao getallbyrole(3) -> sprawdzam czy metoda read zwroci mi wlasciwego uzytkownika w innym wypddaku null
        //student controller tylko inne zaleznosci nulle w ramach funkcjonalnosci

    @Override
    public User read(int id) {
        List<User> users = readAll();

        for (User user: users) {
            if (user.getId()==id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean update(User user) {
        return sqlUserDao.update(user);
    }

    @Override
    public boolean delete(User user) {
        return sqlUserDao.delete(user);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String URL = exchange.getRequestURI().getRawPath();
        String[] methods = URL.split("/");
//        System.out.println(Arrays.toString(methods));

        String regex = "\\d+";

        if (methods.length == 2) {

            String response = "";
            try {
                List<User> students = readAll();
                ObjectMapper objectMapper = new ObjectMapper();
                response = objectMapper.writeValueAsString(students);
                exchange.getResponseHeaders().put("Content-Type", Collections.singletonList("application/json"));
                exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
                exchange.sendResponseHeaders(200, response.length());
            } catch (Exception e) {
                exchange.sendResponseHeaders(404, response.length());
            }
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.close();
        }

        else if (methods[2].matches(regex) ){
            String response = "";
            try {
                User student = read(Integer.parseInt(methods[2]));
                ObjectMapper objectMapper = new ObjectMapper();
                response = objectMapper.writeValueAsString(student);
                exchange.getResponseHeaders().put("Content-Type", Collections.singletonList("application/json"));
                exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
                exchange.sendResponseHeaders(200, response.length());
            } catch (Exception e) {
                exchange.sendResponseHeaders(404, response.length());
            }
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.close();
        }
    }
}