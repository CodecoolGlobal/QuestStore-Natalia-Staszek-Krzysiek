//package handler;
//
//import dao.StudentDetailsDAO;
//import dao.UserDAO;
//import model.StudentDetails;
//import model.User;
//import view.UserView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserController {
//
//    private UserDAO userDAO;
//    private UserView userView;
//    private StudentDetailsDAO studentDetailsDAO;
//
//    UserController(UserDAO userDAO, UserView userView, StudentDetailsDAO StudentDetailsDAO) {
//        this.userDAO = userDAO;
//        this.userView = userView;
//        this.studentDetailsDAO = StudentDetailsDAO;
//    }
//
//    void promoteBlankUser() {
//
//        if (userDAO.getAllByRole(4).size() > 0) {
//            List<User> users = new ArrayList<>(userDAO.getAllByRole(4));
//            userView.displayEntriesNoInput(users);
//            String login = userView.askForLogin();
//            User user = userDAO.getByLoginAndRole(login, 4);
//
//            if (user != null) {
//                promote(user);
//            } else {
//                userView.userDoesNotExistMessage();
//            }
//        } else {
//            userView.emptyListMessage();
//        }
//    }
//
//    void promote(User user) {
//
//        boolean isPromoteToMentor = userView.getTypeOfPromotion();
//        boolean isPromoted;
//
//        if (isPromoteToMentor) {
//            user.setRole(2);
//            isPromoted = userDAO.update(user);
//        } else {
//            user.setRole(3);
//            isPromoted = userDAO.update(user);
//
//            StudentDetails student = createStudent(user);
//            studentDetailsDAO.add(student);
//        }
//        if (isPromoted) {
//            userView.hasBeenPromotedMessage();
//        } else {
//            userView.userDoesNotExistMessage();
//        }
//    }
//
//    StudentDetails createStudent(User user) {
//        try {
//            StudentDetails student = new StudentDetails();
//            student.setId(user.getId());
//            return student;
//        }
//        catch (IllegalArgumentException e) {
//            return null;
//        }
//    }
//
//    public UserDAO getUserDAO() {
//        return userDAO;
//    }
//
//    StudentDetailsDAO getStudentDetailsDAO() {
//        return studentDetailsDAO;
//    }
//}