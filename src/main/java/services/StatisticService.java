package services;

import dao.SQL.SQLQuestDao;
import model.Mentor;
import model.StudentDetails;

import java.util.List;

public class StatisticService {

    public int getAllQuests() {
        //TODO: Quest sum all of available quest
        SQLQuestDao questDAO = new SQLQuestDao();
        return questDAO.getAll().size();
    }

    public int getAllQuestsByMentor(Mentor mentor) {
        //TODO: Quest created by mentor
        SQLQuestDao questDao = new SQLQuestDao();
        return mentor.getMentorQuests(questDao.getAll()).size();
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
