package controller;

import dao.ItemDAO;
import dao.StudentDetailsDAO;
import dao.StudentItemDAO;
import dao.UserDAO;
import model.Item;
import model.StudentDetails;
import model.User;
import view.StudentView;

import java.util.ArrayList;
import java.util.List;

public class StudentController {

    private StudentView studentView;
    private StudentDetailsDAO studentDetailsDAO;
    private StudentItemDAO studentItemDAO;
//    private ExpLevelsDAO expLevelsDAO;
    private UserDAO userDAO;
    private ItemDAO itemDAO;

    public StudentController(StudentDetailsDAO studentDetailsDAO, StudentItemDAO studentItemDAO,
                             UserDAO userDAO, ItemDAO itemDAO, StudentView studentView) {
        this.studentDetailsDAO = studentDetailsDAO;
        this.studentItemDAO = studentItemDAO;
        this.userDAO = userDAO;
        this.itemDAO = itemDAO;
        this.studentView = studentView;
    }

    void updateStudentBalance(int studentId, int points) {
        StudentDetails studentDetails = studentDetailsDAO.getStudentDataByStudentId(studentId);
        studentDetails.setWallet(studentDetails.getWallet() + points);
    }

    void updateStudentExperienceAndLevel(int studentId, int points) {
        StudentDetails studentDetails = studentDetailsDAO.getStudentDataByStudentId(studentId);
        studentDetails.setExperience(studentDetails.getExperience() + points);
        studentDetailsDAO.updateStudentData(studentDetails);
    }

    void showStudentSummary() {
        studentView.clearConsole();

        List<User> students = userDAO.getAllByRole(3);
        if (students != null) {

            for (User user : students) {
                List<String> studentInfo = new ArrayList<>();

                StudentDetails student = studentDetailsDAO.getStudentDataByStudentId(user.getId());
                studentInfo.add(user.getName());
                studentInfo.add(student.getWallet().toString());

                List<Item> studentItems = itemDAO.getItemsByStudentId(student.getId());
                if (studentItems != null) {
                    studentView.displayStudentInfo(studentInfo, studentItems);

                }
            }
            studentView.pressAnyKeyToContinue();
        } else { studentView.displayNoStudents(); }
    }

    void showStudentBackPack(int studentId) {
        List<Item> backpack = itemDAO.getItemsByStudentId(studentId);
        studentView.displayCodecoolerBackpack(backpack);
    }

    void buyArtifact(int studentId) {
        Item item = chooseItemToBuy("SINGLE");

        if (item != null) {

            if (!isStudentContainItem(studentId, item.getId())) {
                int price = item.getPrice();

                if (isStudentAffordToBuy(studentId, price)) {
                    updateStudentBackpack(studentId, item);
                    StudentDetails studentDetails = studentDetailsDAO.getStudentDataByStudentId(studentId);
                    updateStudentBalance(price, studentDetails);

                } else { studentView.displayNoMoney(); }
            } else { studentView.displayItemAlreadyContaining(); }
        }
    }

    private Item chooseItemToBuy(String category) {
        List<Item> items = itemDAO.getItemsByCategory(category);

        if (items != null) {
            int itemId = studentView.chooseItemFrom(items);

            if (checkIfIdItemInStore(itemId, items)) {
                return itemDAO.getItemById(itemId);

            } else { studentView.displayWrongId(); return null; }
        } else {
            studentView.operationFailed();
            return null;
        }
    }

    private boolean checkIfIdItemInStore(int itemId, List<Item> items) {
        for (Item item : items) {
            if (itemId == item.getId()) { return true; }
        }
        return false;
    }

    private boolean isStudentAffordToBuy(int studentId, int price) {
        StudentDetails studentDetails = studentDetailsDAO.getStudentDataByStudentId(studentId);
        int studentWallet = studentDetails.getWallet();
        return studentWallet > price;
    }

    void buyArtifactForTeam(int studentId) {
        StudentDetails studentDetails = studentDetailsDAO.getStudentDataByStudentId(studentId);
        if (studentDetails.getTeamName().isEmpty()) {
            studentView.displayStudentHaveNoTeamAssignedMessage();
            return;
        }
        List<StudentDetails> team = studentDetailsDAO.getStudentsDataByTeamName(studentDetails.getTeamName());
        Item item = chooseItemToBuy("MULTIPLE");

        if (item != null && team != null) {

            if (!isTeamMemberContainItem(team, item.getId())) {
                int priceForEachStudent = item.getPrice() / team.size();

                if (isTeamAffordToBuy(priceForEachStudent, team)) {
                    for (StudentDetails member : team) {
                        updateStudentBackpack(member.getId(), item);
                        updateStudentBalance(priceForEachStudent, member);
                    }

                } else { studentView.displayNoMoney(); }
            } else { studentView.displayItemAlreadyContaining(); }
        }
    }

    private boolean isTeamAffordToBuy(int itemPriceForEachStudent, List<StudentDetails> team) {

        for (StudentDetails student : team) {
            if (student.getWallet() < itemPriceForEachStudent) {
                return false;
            }
        }
        return true;
    }

    private void updateStudentBackpack(int studentId, Item item) {
        if (studentItemDAO.add(studentId, item.getId(), 0)) {
            studentView.operationSuccess();
        } else {
            studentView.operationFailed();
        }
    }

    private void updateStudentBalance(int price, StudentDetails studentDetails) {
        int transactionBalance = studentDetails.getWallet() - price;
        studentDetails.setWallet(transactionBalance);
        studentDetailsDAO.updateStudentData(studentDetails);
    }

    private boolean isStudentContainItem(int studentId, int itemID) {
        List<Integer> studentsItems = studentItemDAO.getStudentItemsIdsBy(studentId);
        for (int studentItemID : studentsItems) {
            if (itemID == studentItemID) {
                return true;
            }
        }
        return false;
    }

    private boolean isTeamMemberContainItem(List<StudentDetails> team, int itemID) {
        for (StudentDetails member : team) {
            if (isStudentContainItem(member.getId(), itemID)) {
                return true;
            }
        }
        return false;
    }
}