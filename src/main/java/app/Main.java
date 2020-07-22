package app;

import dao.QuestDAO;
import dao.SQL.SQLQuestDao;
import dao.SQL.SQLUserDao;
import dao.UserDAO;
import model.Quest;
import model.User;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        QuestDAO questDAO = new SQLQuestDao();
        List<Quest> results = questDAO.getAllQuests();
        System.out.println(results);

        UserDAO userDAO = new SQLUserDao();
        List<User> resultsTwo = userDAO.getAllByRole(2);
        Collections.sort(resultsTwo, Comparator.comparingLong(User::getId));
        System.out.println(resultsTwo);

        List<User> resultsThree = userDAO.getAll();
        Collections.sort(resultsThree, Comparator.comparingLong(User::getId));
        System.out.println(resultsThree);


    }

}
