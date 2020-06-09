package controller;

import dao.StudentDetailsDAO;
import dao.UserDAO;
import model.StudentDetails;
import model.User;
import view.MentorView;
import view.UserView;

public class MentorMenuController extends UserController{

    private MentorView mentorView;
    private TeamController teamController;
    private ClassController classController;
    private QuestController questController;
    private StudentController studentController;
    private ItemController itemController;

    public MentorMenuController(UserDAO userDAO, UserView view, StudentDetailsDAO studentDetailsDAO, MentorView mentorView,
                                TeamController teamController, ClassController classController, QuestController questController,
                                StudentController studentController, ItemController itemController) {
        super(userDAO, view, studentDetailsDAO);
        this.mentorView = mentorView;
        this.teamController = teamController;
        this.classController = classController;
        this.questController = questController;
        this.studentController = studentController;
        this.itemController = itemController;
    }

    void start() {
        int option;
        boolean isAppRunning = true;

        while (isAppRunning) {
            mentorView.clearConsole();
            mentorView.handleMentorMenu();
            option = mentorView.askForOption();

            switch (option) {
                case 1:
                    promoteBlankUser();
                    break;
                case 2:
                    classController.addStudentToGroup();
                    break;
                case 3:
                    questController.addNewQuest();
                    break;
                case 4:
                    itemController.addNewItem();
                    break;
                case 5:
                    questController.editQuest();
                    break;
                case 6:
                    itemController.editItem();
                    break;
                case 7:
                    questController.markStudentAchievedQuest();
                    break;
                case 8:
                    itemController.markStudentUsedItem();
                    break;
                case 9:
                    studentController.showStudentSummary();
                    break;
                case 10:
                    teamController.handleReshuffleStudentsTeams();
                    break;
                case 11:
                    isAppRunning = false;
            }
        }
    }

    @Override
    void promote(User user) {
        user.setRole(3);
        boolean isPromoted = getUserDAO().update(user);
        StudentDetails student = createStudent(user);
        getStudentDetailsDAO().add(student);
        if (isPromoted) {
            mentorView.hasBeenPromotedMessage();
        } else {
            mentorView.userNotExist();
        }
    }
}
