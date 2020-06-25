package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.ItemDAO;
import dao.SQL.SQLUserDao;
import dao.StudentDetailsDAO;
import dao.StudentItemDAO;
import dao.UserDAO;
import model.Item;
import model.StudentDetails;
import model.User;
import view.StudentView;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentController implements Controller<User>, HttpHandler {

    private StudentView studentView;
    private StudentDetailsDAO studentDetailsDAO;
    private StudentItemDAO studentItemDAO;
    //    private ExpLevelsDAO expLevelsDAO;
    private UserDAO userDAO;
    private ItemDAO itemDAO;
    private final SQLUserDao sqlUserDao = new SQLUserDao();

    public StudentController() {
    }

    void updateStudentBalance(int studentId, int points) {
        StudentDetails user = studentDetailsDAO.getStudentDataByStudentId(studentId);
        user.setWallet(user.getWallet() + points);
    }

    void updateStudentExperience(int studentId, int points) {
        StudentDetails user = studentDetailsDAO.getStudentDataByStudentId(studentId);
        user.setExperience(user.getExperience() + points);
        studentDetailsDAO.updateStudentData(user);
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
        } else {
            studentView.displayNoStudents();
        }
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
                    StudentDetails user = studentDetailsDAO.getStudentDataByStudentId(studentId);
                    updateStudentBalance(price, user);

                } else {
                    studentView.displayNoMoney();
                }
            } else {
                studentView.displayItemAlreadyContaining();
            }
        }
    }

    private Item chooseItemToBuy(String category) {
        List<Item> items = itemDAO.getItemsByCategory(category);

        if (items != null) {
            int itemId = studentView.chooseItemFrom(items);

            if (checkIfIdItemInStore(itemId, items)) {
                return itemDAO.getItemById(itemId);
            } else {
                studentView.displayWrongId();
                return null;
            }
        } else {
            studentView.operationFailed();
            return null;
        }
    }

    private boolean checkIfIdItemInStore(int itemId, List<Item> items) {
        for (Item item : items) {
            if (itemId == item.getId()) {
                return true;
            }
        }
        return false;
    }

    private boolean isStudentAffordToBuy(int studentId, int price) {
        StudentDetails user = studentDetailsDAO.getStudentDataByStudentId(studentId);
        int studentWallet = user.getWallet();
        return studentWallet > price;
    }

    void buyArtifactForTeam(int studentId) {
        StudentDetails user = studentDetailsDAO.getStudentDataByStudentId(studentId);
        if (user.getTeamName().isEmpty()) {
            studentView.displayStudentHaveNoTeamAssignedMessage();
            return;
        }
        List<StudentDetails> team = studentDetailsDAO.getStudentsDataByTeamName(user.getTeamName());
        Item item = chooseItemToBuy("MULTIPLE");

        if (item != null && team != null) {

            if (!isTeamMemberContainItem(team, item.getId())) {
                int priceForEachStudent = item.getPrice() / team.size();

                if (isTeamAffordToBuy(priceForEachStudent, team)) {
                    for (StudentDetails member : team) {
                        updateStudentBackpack(member.getId(), item);
                        updateStudentBalance(priceForEachStudent, member);
                    }

                } else {
                    studentView.displayNoMoney();
                }
            } else {
                studentView.displayItemAlreadyContaining();
            }
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
        if (studentItemDAO.add(item.getId(), studentId, true)) {
            studentView.operationSuccess();
        } else {
            studentView.operationFailed();
        }
    }

    private void updateStudentBalance(int price, StudentDetails user) {
        int transactionBalance = user.getWallet() - price;
        user.setWallet(transactionBalance);
        studentDetailsDAO.updateStudentData(user);
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

    @Override
    public boolean create(User user) {
        return sqlUserDao.add(user);
    }

    @Override
    public List<User> readAll() {
        return sqlUserDao.getAllByRole(3);
    }

    @Override
    public User read(int id) {
        List<User> users = readAll();

        for (User user: users) {
            if (user.getId()==id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean update(User user) {
        return sqlUserDao.update(user);
    }

    @Override
    public boolean delete(User user) {
        return sqlUserDao.delete(user);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String URL = exchange.getRequestURI().getRawPath();
        String[] methods = URL.split("/");
//        System.out.println(Arrays.toString(methods));

        String regex = "\\d+";

        if (methods.length == 2) {

            String response = "";
            try {
                List<User> students = readAll();
                ObjectMapper objectMapper = new ObjectMapper();
                response = objectMapper.writeValueAsString(students);
                exchange.getResponseHeaders().put("Content-Type", Collections.singletonList("application/json"));
                exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
                exchange.sendResponseHeaders(200, response.length());
            } catch (Exception e) {
                exchange.sendResponseHeaders(404, response.length());
            }
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.close();
        }

        else if (methods[2].matches(regex) ){
            String response = "";
            try {
                User student = read(Integer.parseInt(methods[2]));
                ObjectMapper objectMapper = new ObjectMapper();
                response = objectMapper.writeValueAsString(student);
                exchange.getResponseHeaders().put("Content-Type", Collections.singletonList("application/json"));
                exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
                exchange.sendResponseHeaders(200, response.length());
            } catch (Exception e) {
                exchange.sendResponseHeaders(404, response.length());
            }
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.close();
        }
    }
}