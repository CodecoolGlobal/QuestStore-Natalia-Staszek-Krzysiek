package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.SQL.SQLUserDao;
import dao.StudentDetailsDAO;
import dao.UserDAO;
import model.StudentDetails;
import model.User;
import view.UserView;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserController implements Controller<User>, HttpHandler {

    private UserDAO userDAO;
    private UserView userView;
    private StudentDetailsDAO studentDetailsDAO;
    private final SQLUserDao sqlUserDao = new SQLUserDao();

    UserController(UserDAO userDAO, UserView userView, StudentDetailsDAO StudentDetailsDAO) {
        this.userDAO = userDAO;
        this.userView = userView;
        this.studentDetailsDAO = StudentDetailsDAO;
    }

    public UserController(){
    }

    void promoteBlankUser() {

        if (userDAO.getAllByRole(4).size() > 0) {
            List<User> users = new ArrayList<>(userDAO.getAllByRole(4));
            userView.displayEntriesNoInput(users);
            String login = userView.askForLogin();
            User user = userDAO.getByLoginAndRole(login, 4);

            if (user != null) {
                promote(user);
            } else {
                userView.userDoesNotExistMessage();
            }
        } else {
            userView.emptyListMessage();
        }
    }

    void promote(User user) {

        boolean isPromoteToMentor = userView.getTypeOfPromotion();
        boolean isPromoted;

        if (isPromoteToMentor) {
            user.setRole(2);
            isPromoted = userDAO.update(user);
        } else {
            user.setRole(3);
            isPromoted = userDAO.update(user);

            StudentDetails student = createStudent(user);
            studentDetailsDAO.add(student);
        }
        if (isPromoted) {
            userView.hasBeenPromotedMessage();
        } else {
            userView.userDoesNotExistMessage();
        }
    }

    StudentDetails createStudent(User user) {
        try {
            StudentDetails student = new StudentDetails();
            student.setUser(user);
            return student;
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    StudentDetailsDAO getStudentDetailsDAO() {
        return studentDetailsDAO;
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
                List<User> allUsers = readAll();
                ObjectMapper objectMapper = new ObjectMapper();
                response = objectMapper.writeValueAsString(allUsers);
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

    @Override
    public boolean create(User user) {
        return false;
    }

    @Override
    public List<User> readAll() {
        return sqlUserDao.getAll();
    }

    @Override
    public User read(int id) {
        List<User> users =  readAll();

        for (User user: users) {
            if (user.getId()==id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }
}