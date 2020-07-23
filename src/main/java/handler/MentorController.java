package handler;

import Exceptions.DatabaseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.SQL.SQLUserDao;
import model.User;


import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

public class MentorController implements Controller<User>, HttpHandler {

    private String response = "";

    private final SQLUserDao sqlUserDao = new SQLUserDao();

    @Override
    public boolean create(User mentor) {
        return sqlUserDao.add(mentor);
    }

    @Override
    public List<User> readAll() throws DatabaseException {
        return sqlUserDao.getAllByRole(2);
    }

    @Override
    public User read(int id) throws DatabaseException {
        List<User> mentors = readAll();

        for (User mentor: mentors)
            if (mentor.getId()==id) {
                return mentor;
            }

        throw new DatabaseException("Mentor with : " + id + " not found");
    }

    @Override
    public boolean update(User mentor) {
        return sqlUserDao.update(mentor);
    }

    @Override
    public boolean delete(User mentor) {
        return sqlUserDao.delete(mentor);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String URL = exchange.getRequestURI().getRawPath();
        String[] methods = URL.split("/");
//        System.out.println(Arrays.toString(methods));

        String regex = "\\d+";
        if (methods.length == 2) {


            try {
                List<User> mentors = readAll();
                ObjectMapper objectMapper = new ObjectMapper();
                response = objectMapper.writeValueAsString(mentors);
                exchange.getResponseHeaders().put("Content-Type", Collections.singletonList("application/json"));
                exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
                exchange.sendResponseHeaders(200, response.length());
            } catch (Exception e) {
                exchange.sendResponseHeaders(404, response.length());
            } catch (DatabaseException e) {
                response = e.getMessage();
                exchange.sendResponseHeaders(404, response.length());
            }

        }

        else if (methods[2].matches(regex) ){

            try {
                User student = read(Integer.parseInt(methods[2]));
                ObjectMapper objectMapper = new ObjectMapper();
                response = objectMapper.writeValueAsString(student);
                exchange.getResponseHeaders().put("Content-Type", Collections.singletonList("application/json"));
                exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
                exchange.sendResponseHeaders(200, response.length());
            } catch (Exception e) {
                exchange.sendResponseHeaders(404, response.length());
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        }
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    public String getResponse() {
        return response;
    }
}