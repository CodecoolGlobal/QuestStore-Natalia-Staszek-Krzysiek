package services;

import dao.QuestDAO;
import dao.SQL.SQLQuestDao;
import model.Mentor;
import model.StudentDetails;

import java.util.List;

public class StatisticService {

    public int getAllQuests() {
        //TODO: Quest sum
        SQLQuestDao questDAO = new SQLQuestDao();
        return questDAO.getAll().size();
    }

    public int getAllQuestsByMentor(List<Mentor> mentors) {
        //TODO: Quest created by mentor
        return 0;
    }

    public int getQuestsByStudents(List<StudentDetails> students) {
        //TODO: Quest done by student
        return 0;
    }

    public int getAllMentors(List<Mentor> mentors) {
        //TODO: Get count of mentors
        return 0;
    }

    public int getAllStudents(List<StudentDetails> students) {
        //TODO: Get count of students
        return 0;
    }
}
