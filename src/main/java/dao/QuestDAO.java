package dao;

import model.Quest;

import java.util.List;

public interface QuestDAO {

    List<Quest> getAllQuests();
    List<Quest> getTasksByStudentId(int id);
    Quest getQuestByName(String name);
    Quest getById(int id);
    boolean add(Quest task);
    boolean update(Quest task);
    boolean delete(Quest task);
}
