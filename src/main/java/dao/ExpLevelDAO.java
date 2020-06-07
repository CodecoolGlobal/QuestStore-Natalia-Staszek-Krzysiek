package dao;

import model.LevelOfExp;

import java.util.List;

public interface ExpLevelDAO {

    List<LevelOfExp> getAll();
    LevelOfExp getByName(String levelName);
    boolean add(LevelOfExp expLevel);
    boolean delete(String levelName);
    void setDatabasePath(String path);
}
