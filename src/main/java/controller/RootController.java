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

}