package controller;

import dao.Dao;
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
                case "0":
                    exitApp = true;
                    break;
                default:
                    rootView.displayWrongInput();
            }
        }
    }

}