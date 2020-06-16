package controller;

import dao.StudentDetailsDAO;
import dao.UserDAO;
import view.CreepView;
import view.UserView;

public class CreepMenuController extends UserController {

    private CreepView creepView;
    private GroupController groupController;
    private MentorController mentorController;

    public CreepMenuController(UserDAO userDAO, UserView userView, StudentDetailsDAO studentDetailsDAO, CreepView creepView,
                               GroupController groupController, MentorController mentorController) {
        super(userDAO, userView, studentDetailsDAO);
        this.creepView = creepView;
        this.groupController = groupController;
        this.mentorController = mentorController;
    }

    void start() {

        int option;
        boolean isAppRunning = true;

        while (isAppRunning) {
            creepView.clearConsole();
            creepView.handleCreepMenu();
            option = creepView.askForOption();

            switch (option) {
                case 1:
                    promoteBlankUser();
                    break;
                case 2:
                    groupController.createGroup();
                    break;
                case 3:
                    groupController.assignMentorToGroup();
                    break;
                case 4:
                    groupController.revokeMentorFromGroup();
                    break;
                case 5:
                    groupController.deleteGroup();
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
                case 9:
                    isAppRunning = false;
            }
        }
    }
}