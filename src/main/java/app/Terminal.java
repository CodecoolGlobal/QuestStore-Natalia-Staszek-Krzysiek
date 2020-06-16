package app;

import controller.*;
import dao.*;
import dao.SQL.*;
import view.*;

public class Terminal {

    public static void main(String... args) {

        //OGRANICZENIE ILOSCI INSTANCJI DO NAJPOTRZEBNIEJSZYCH

        CreepView creepView = new CreepView();
        GroupView groupView = new GroupView();
        ItemView itemView = new ItemView();
        MentorView mentorView = new MentorView();
        RootView rootView = new RootView();
        StudentView studentView = new StudentView();
        QuestView questView = new QuestView();
        UserView userView = new UserView();

        GroupDAO groupDAO = new SQLGroupDAO();
        ItemDAO itemDAO = new SQLItemDao();
        UserClassDAO mentorGroupDAO = new SQLUserGroupDao();
        StudentDetailsDAO studentDetailsDAO = new SQLStudentDetailsDao();
        StudentItemDAO studentItemDAO = new SQLStudentItemDao();
        StudentQuestDAO studentQuestDAO = new SQLStudentQuestDao();
        QuestDAO questDAO = new SQLQuestDao();
        UserDAO userDAO = new SQLUserDao();

        GroupController groupController = new GroupController(groupView, mentorView, groupDAO, userDAO,
                mentorGroupDAO, studentDetailsDAO);
        ItemController itemController = new ItemController(itemDAO, userDAO, studentItemDAO, itemView);
        MentorController mentorController = new MentorController(userDAO, groupController, mentorView);
        StudentController studentController = new StudentController(studentDetailsDAO, studentItemDAO, userDAO,
                itemDAO, studentView);
        QuestController questController = new QuestController(questDAO, userDAO, studentQuestDAO, studentController, questView);
        TeamController teamController = new TeamController(studentDetailsDAO, studentItemDAO, userView);

        CreepMenuController adminMenuController = new CreepMenuController(userDAO, userView, studentDetailsDAO, creepView,
                groupController, mentorController);
        MentorMenuController mentorMenuController = new MentorMenuController(userDAO, userView, studentDetailsDAO, mentorView,
                teamController, groupController, questController, studentController, itemController);
        StudentMenuController studentMenuController = new StudentMenuController(studentView, studentDetailsDAO, studentController);
        RootController rootController = new RootController(userDAO, rootView, adminMenuController, studentMenuController,
                mentorMenuController);
        rootController.start();
    }
}
