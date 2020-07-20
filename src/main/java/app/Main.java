package app;

import dao.QuestDAO;
import dao.SQL.SQLQuestDao;
import model.Quest;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        QuestDAO questDAO = new SQLQuestDao();
        List<Quest> results = questDAO.getAllQuests();
        System.out.println(results);


    }

}
