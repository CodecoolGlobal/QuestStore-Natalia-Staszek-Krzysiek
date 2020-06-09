package controller;

import dao.StudentDetailsDAO;
import dao.UserDAO;
import view.CreepView;
import view.UserView;

public class CreepMenuController extends UserController {

    private CreepView adminView;
    private ClassController classController;
    private MentorController mentorController;

    public CreepMenuController(UserDAO userDAO, UserView userView, StudentDetailsDAO studentDetailsDAO, CreepView adminView,
                               ClassController classController, MentorController mentorController) {
        super(userDAO, userView, studentDetailsDAO);
        this.adminView = adminView;
        this.classController = classController;
        this.mentorController = mentorController;
    }

    void start() {

        int option;
        boolean isAppRunning = true;

        while (isAppRunning) {
            adminView.clearConsole();
            adminView.handleCreepMenu();
            option = adminView.askForOption();

            switch (option) {
                case 1:
                    promoteBlankUser();
                    break;
                case 2:
                    classController.createGroup();
                    break;
                case 3:
                    classController.assignMentorToGroup();
                    break;
                case 4:
                    classController.revokeMentorFromGroup();
                    break;
                case 5:
                    classController.deleteGroup();
                    break;
                case 6:
                    mentorController.deleteMentor();
                    break;
                case 7:
                    mentorController.editMentorData();
                    break;
                case 8:
                    mentorController.showMentorProfileAndHisGroups();
                    break;
                case 12:
                    isAppRunning = false;
            }
        }
    }
}