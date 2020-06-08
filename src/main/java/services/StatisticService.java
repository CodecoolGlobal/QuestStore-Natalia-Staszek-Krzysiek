package services;

import dao.SQL.SQLQuestDao;
import dao.SQL.SQLStudentDetailsDao;
import dao.SQL.SQLUserDao;
import model.Quest;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class StatisticService {

    public int getAllQuests() {
        //TODO: Quest sum all of available quest
        SQLQuestDao questDAO = new SQLQuestDao();
        return questDAO.getAll().size();
    }

    public int getAllQuestsByMentor(int mentorId) {
        //TODO: Quest created by mentor
        SQLQuestDao questDao = new SQLQuestDao();
        List<Quest> quests = questDao.getAll();
        List<Quest> mentorQuest = new ArrayList<>();
        for (Quest quest : quests) {
            if (quest.getIdMentor() == mentorId) {
                mentorQuest.add(quest);
            }
        }
        return mentorQuest.size();
    }

    public int getQuestsByStudents(int studentId) {
        //TODO: Quest done by student
        SQLStudentDetailsDao sqlStudentDetailsDao = new SQLStudentDetailsDao();
        List<Quest> quests = sqlStudentDetailsDao.getStudentDataByStudentId(studentId).getCompletedQuests();
        List<Quest> studentQuest = new ArrayList<>();
        for (Quest quest : quests) {
            if (quest.getIdMentor() == studentId) {
                studentQuest.add(quest);
            }
        }
        return studentQuest.size();
    }

    public int getAllUsersByRole(int role) {
        //TODO: Get count of mentors
        return 0;
    }

}
