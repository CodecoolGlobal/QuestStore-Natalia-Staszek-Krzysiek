package controller;

import dao.UserDAO;
import model.User;
import utilities.EmailValidator;
import utilities.PhoneValidator;
import view.RootView;

public class RootController {

    private UserDAO userDAO;
    private RootView rootView;
    private CreepMenuController creepMenuController;
    private StudentMenuController studentMenuController;
    private MentorMenuController mentorMenuController;

    private final int MIN_LENGTH = 6;
    private final int MAX_LENGTH = 15;

    public RootController(UserDAO userDAO, RootView rootView, CreepMenuController creepMenuController,
                          StudentMenuController studentMenuController, MentorMenuController mentorMenuController) {
        this.userDAO = userDAO;
        this.rootView = rootView;
        this.creepMenuController = creepMenuController;
        this.studentMenuController = studentMenuController;
        this.mentorMenuController = mentorMenuController;
    }

    public void start() {
        boolean isAppRunning = true;

        while (isAppRunning) {
            rootView.displayMenu();
            String userInput = rootView.getUserInput();
            switch (userInput) {
                case "1":
                    signIn();
                    break;
                case "2":
                    signUp();
                    break;
                case "0":
                    isAppRunning = false;
                    break;
                default:
                    rootView.wrongInputMessage();
            }
        }
    }


    private void signIn() {

        final String QUIT_OPTION = "q";
        boolean isLoggedIn = false;
        String login;
        String password;
        User user;

        while(!isLoggedIn) {

            login = rootView.getUserLogin();
            if (login.equals(QUIT_OPTION)) return;
            password = rootView.getUserPassword();
            if (password.equals(QUIT_OPTION)) return;
            user = userDAO.getByLoginAndPassword(login, password);

            if(user != null) {
                isLoggedIn = true;
                switch (user.getRole()) {
                    case 4:
                        rootView.wrongInputMessage();
                        break;
                    case 3:
                        studentMenuController.start(user.getId());
                        break;
                    case 2:
                        mentorMenuController.start();
                        break;
                    case 1:
                        creepMenuController.start();
                        break;
                }
            } else {
                rootView.userNotExistMessage();
            }
        }
    }

    private void signUp() {

        boolean isUserCreated = false;
        String name;
        String login;
        String email;
        String password;
        String phoneNumber;

        while(!isUserCreated) {
            login = createUserLogin();
            if (userDAO.getByLogin(login) != null) {
                rootView.userAlreadyExistMessage();
                return;
            }
            email = createUserEmail();
            if (userDAO.getByEmail(email) != null) {
                rootView.userWithEmailAlreadyExist();
                return;
            }
            phoneNumber = createUserPhoneNumber();
            if (userDAO.getByPhoneNumber(phoneNumber) != null) {
                rootView.userWithPhoneAlreadyExist();
                return;
            }
            password = createUserPassword();
            name = createUserName();
            this.userDAO.add(new User(name, login, email, password, phoneNumber, 4));
            rootView.displayCreatedUser(login, name, email, phoneNumber);
            isUserCreated = true;
        }
    }

    private String createUserLogin() {

        String login = null;
        boolean isCorrectInput = false;

        while(!isCorrectInput) {
            login = rootView.getNewUserLogin();
            if (login.length() >= MIN_LENGTH && login.length() <= MAX_LENGTH) {
                isCorrectInput = true;
            }
        }
        return login;
    }

    private String createUserPassword() {

        String password = null;
        boolean isCorrectInput = false;

        while(!isCorrectInput) {
            password = rootView.getNewUserPassword();
            if (password.length() >= MIN_LENGTH && password.length() <= MAX_LENGTH) {
                isCorrectInput = true;
            }
        }
        return password;
    }

    private String createUserName() {
        return rootView.getNewUserName();
    }

    private String createUserEmail() {

        String email = null;
        boolean isCorrectInput = false;

        while(!isCorrectInput) {
            email = rootView.getNewUserEmail();
            if (new EmailValidator().validate(email)) {
                isCorrectInput = true;
            }
        }
        return email;
    }

    private String createUserPhoneNumber() {

        String phoneNumber = null;
        boolean isCorrectInput = false;

        while(!isCorrectInput) {
            phoneNumber = rootView.getNewUserPhoneNumber();
            if (new PhoneValidator().validate(phoneNumber)) {
                isCorrectInput = true;
            }
        }
        return phoneNumber;
    }
}