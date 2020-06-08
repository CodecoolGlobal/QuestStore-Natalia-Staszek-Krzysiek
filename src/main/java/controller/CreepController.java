//package controller;
//
//import dao.UserDAO;
//import view.CreepView;
//import view.UserView;
//
//public class CreepController extends UserController{
//
//    private CreepView adminView;
//    private ClassController groupController;
//    private ExpLevelsController expLevelsController;
//    private MentorController mentorController;
//
//    public CreepMenuController(UserDAO userDAO, UserView userView, StudentDetailsDAO StudentDetailsDAO, AdminView adminView,
//                               GroupController groupController, ExpLevelsController expLevelsController, MentorController mentorController) {
//        super(userDAO, userView, StudentDetailsDAO);
//        this.adminView = adminView;
//        this.groupController = groupController;
//        this.expLevelsController = expLevelsController;
//        this.mentorController = mentorController;
//    }
//
//    void start() {
//
//        int option;
//        boolean isAppRunning = true;
//
//        while (isAppRunning) {
//            adminView.clearConsole();
//            adminView.handleAdminMenu();
//            option = adminView.askForOption();
//
//            switch (option) {
//                case 1:
//                    promoteBlankUser();
//                    break;
//                case 2:
//                    groupController.createGroup();
//                    break;
//                case 3:
//                    groupController.assignMentorToGroup();
//                    break;
//                case 4:
//                    groupController.revokeMentorFromGroup();
//                    break;
//                case 5:
//                    groupController.deleteGroup();
//                    break;
//                case 6:
//                    mentorController.deleteMentor();
//                    break;
//                case 7:
//                    mentorController.editMentorData();
//                    break;
//                case 8:
//                    mentorController.showMentorProfileAndHisGroups();
//                    break;
//                case 9:
//                    expLevelsController.addLevelOfExperience();
//                    break;
//                case 10:
//                    expLevelsController.removeLevelOfExperience();
//                    break;
//                case 11:
//                    expLevelsController.showAllLevelsOfExperience();
//                    break;
//                case 12:
//                    isAppRunning = false;
//            }
//        }
//    }
//}