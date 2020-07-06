package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.SQL.SQLUserDao;
import dao.UserDAO;
import model.User;
import view.MentorView;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MentorController implements Controller<User>, HttpHandler {

    private MentorView mentorView;
    private UserDAO userDAO;
    private GroupController groupController;
    private final SQLUserDao sqlUserDao = new SQLUserDao();

    public MentorController(){

    }

    public MentorController(UserDAO userDAO, GroupController groupController, MentorView mentorView) {
        this.userDAO = userDAO;
        this.groupController = groupController;
        this.mentorView = mentorView;
    }

    void deleteMentor() {
        List<User> mentors = new ArrayList<>(userDAO.getAllByRole(2));
        mentorView.displayEntriesNoInput(mentors);
        if (mentors.isEmpty()) {
            mentorView.pressAnyKeyToContinue();
            return;
        }
        String login = mentorView.getMentorLoginToDelete();
        User mentor = userDAO.getByLoginAndRole(login, 2);
        if (mentor != null) {
            userDAO.delete(mentor);
            mentorView.displayMentorDeletedMessage();
        } else {
            mentorView.displayNoMentorMessage();
        }
    }

    void showMentorProfileAndHisGroups() {
        List<User> mentors = new ArrayList<>(userDAO.getAllByRole(2));
        mentorView.displayEntriesNoInput(mentors);
        if (mentors.isEmpty()) {
            mentorView.pressAnyKeyToContinue();
            return;
        }
        String login = mentorView.getMentorLoginToShowProfile();
        User mentor = userDAO.getByLoginAndRole(login, 2);

        if (mentor != null) {
            mentorView.displayMentorProfile(mentor);
            groupController.showMentorGroups(mentor.getId());
        } else {
            mentorView.displayNoMentorMessage();
        }
    }

    void editMentorData() {

        final String QUIT_OPTION = "q";

        List<User> mentors = new ArrayList<>(userDAO.getAllByRole(2));
        mentorView.displayEntriesNoInput(mentors);
        if (userDAO.getAllByRole(2).isEmpty()) {
            mentorView.pressAnyKeyToContinue();
            return;
        }
        String login = mentorView.getMentorLoginToEdit();
        if (login.equals(QUIT_OPTION)) return;
        User mentorToEdit = userDAO.getByLoginAndRole(login, 2);
        if (mentorToEdit != null) {
            updateProfileAttribute(mentorToEdit);
        } else {
            mentorView.displayThereIsNoMentorWithThisLogin();
        }
    }

    private void updateProfileAttribute(User user) {
        final int UPDATE_NAME = 1;
        final int UPDATE_LOGIN = 2;
        final int UPDATE_EMAIL = 3;
        final int UPDATE_PHONE = 4;

        int valueToChange = mentorView.askForChangeInProfile(user);
        switch (valueToChange) {
            case UPDATE_NAME:
                String name = mentorView.askForNewValue();
                user.setName(name);
                showEditResultMessage(userDAO.update(user));
                break;
            case UPDATE_LOGIN:
                String login = mentorView.askForNewValue();
                user.setLogin(login);
                showEditResultMessage(userDAO.update(user));
                break;
            case UPDATE_EMAIL:
                String email = mentorView.askForNewValue();
                user.setEmail(email);
                showEditResultMessage(userDAO.update(user));
                break;
            case UPDATE_PHONE:
                String phoneNumber = mentorView.askForNewValue();
                user.setPhoneNumber(phoneNumber);
                showEditResultMessage(userDAO.update(user));
                break;
            default:
                mentorView.errorWrongSign();
                break;
        }
    }

    private void showEditResultMessage(boolean isEdit) {
        if (isEdit) {
            mentorView.valueChanged();
        } else {
            mentorView.errorChangingValueMessage();
        }
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
                List<User> mentors = readAll();
                ObjectMapper objectMapper = new ObjectMapper();
                response = objectMapper.writeValueAsString(mentors);
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
        return sqlUserDao.getAllByRole(2);
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