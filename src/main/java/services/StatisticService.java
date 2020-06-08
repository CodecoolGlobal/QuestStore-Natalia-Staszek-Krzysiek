package services;

import dao.SQL.SQLQuestDao;
import model.Mentor;
import model.Quest;
import model.StudentDetails;

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

    public int getQuestsByStudents(List<StudentDetails> students) {
        //TODO: Quest done by student
        return students.size();
    }

    public int getAllMentors(List<Mentor> mentors) {
        //TODO: Get count of mentors
        return mentors.size();
    }

    public int getAllStudents(List<StudentDetails> students) {
        //TODO: Get count of students
        return 0;
    }
}
