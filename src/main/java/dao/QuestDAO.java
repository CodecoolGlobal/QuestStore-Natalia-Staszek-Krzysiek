package dao;

import model.Quest;

import java.util.List;

public interface QuestDAO {

    List<Quest> getAll();
    List<Quest> getTasksByStudentId(int id);
    Quest getByName(String name);
    Quest getById(int id);
    boolean add(Quest task);
    boolean update(Quest task);
    boolean delete(Quest task);
    void setDatabasePath(String path);
}
