package controller;

import dao.StudentDetailsDAO;
import model.StudentDetails;
import model.User;
import view.StudentView;

import java.util.InputMismatchException;

public class StudentMenuController {

    private StudentView studentView;
    private StudentDetailsDAO studentDetailsDAO;
    private StudentController studentController;

    public StudentMenuController(StudentView studentView, StudentDetailsDAO studentDetailsDAO, StudentController studentController) {
        this.studentView = studentView;
        this.studentDetailsDAO = studentDetailsDAO;
        this.studentController = studentController;
    }

    void start(User user) {
        StudentDetails student = studentDetailsDAO.getStudentDataByStudentId(user);

        boolean isLoopEnd = false;
        int option = 0;

        while (!isLoopEnd) {
            studentView.displayInfoBar(student.getWallet(), student.getExperience());
            studentView.handleCodecoolerMenu();

            try {
                option = studentView.askForOption();
            }
            catch (InputMismatchException e) {
                System.err.println("Wrong option!");
            }
            switch (option) {
                case 1:
                    studentController.showStudentBackPack(user.getId());
                    break;
                case 2:
                    try {
                        studentController.buyArtifact(user);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    studentController.buyArtifactForTeam(user.getId());
                    break;
                case 4:
                    isLoopEnd = true;
            }
        }
    }
}
