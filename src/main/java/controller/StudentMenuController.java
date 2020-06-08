package controller;

import dao.StudentDetailsDAO;
import model.StudentDetails;
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

    void start(int studentId) {
        StudentDetails student = getStudentDataBy(studentId);
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
                    studentController.showStudentBackPack(studentId);
                    break;
                case 2:
                    studentController.buyArtifact(studentId);
                    break;
                case 3:
                    studentController.buyArtifactForTeam(studentId);
                    break;
                case 4:
                    isLoopEnd = true;
            }
        }
    }

    private StudentDetails getStudentDataBy(int student_id) {
        return studentDetailsDAO.getStudentDataByStudentId(student_id);
    }
}
