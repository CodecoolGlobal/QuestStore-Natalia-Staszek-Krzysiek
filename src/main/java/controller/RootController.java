package controller;

import dao.Dao;
import model.Codecooler;
import model.Creep;
import model.Mentor;
import model.User;
import view.RootView;

public class RootController {

    private Dao dao;
    private RootView rootView;

    RootController() {
        this.dao = new Dao();
        this.rootView = new RootView();
    }

    public void start() {
        boolean exitApp = false;

        while (!exitApp) {
            rootView.displayMenu();
            String userInput = rootView.getUserInput();
            switch (userInput) {
                case "1":
                    logIn();
                    break;
                case "2":
                    signUp();
                case "0":
                    exitApp = true;
                    break;
                default:
                    rootView.displayWrongInput();
            }
        }
    }

    private void logIn() {
        boolean isLoggedIn = false;
        String login;
        String password;
        User user;

        while (!isLoggedIn) {

            login = rootView.getUserLogin();
            password = rootView.getUserPassword();
            user = Dao.getUserLoginPassword(login,password);

            if (user != null) {
                isLoggedIn = true;
                if (user instanceof User) {
                    rootView.userNotAssignedMessage();
                } else if (user instanceof Codecooler) {
                    studentController.start();
                } else if (user instanceof Mentor) {
                    mentorController.start();
                } else if (user instanceof Creep) {
                    creepController.start();
                }
            } else {
                rootView.displayUserNotExist();
            }

        }
    }

    private void signUp() {

        boolean wasUserCreated = false;
        String login;
        String password;

        while(!wasUserCreated) {
            login = createUserLogin();
            password = createUserPassword();

}





















    }

}