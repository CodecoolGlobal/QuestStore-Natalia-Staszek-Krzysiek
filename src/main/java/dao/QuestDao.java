package dao;

import model.Quest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestDao extends Dao{

    private ArrayList<Quest> quests = new ArrayList<>();


    public List<Quest> extractor(ResultSet resultSet) throws SQLException {

        while (resultSet.next()) {
            Quest quest = new Quest();
            quest.setId(resultSet.getInt(1));
            quest.setName(resultSet.getString(2));
            quest.setPoints(resultSet.getInt(3));
            quest.setDescription(resultSet.getString(4));
            quest.setCategory(resultSet.getString(5));
            quests.add(quest);
        }
        disconnect();
        return quests;
    }
}
