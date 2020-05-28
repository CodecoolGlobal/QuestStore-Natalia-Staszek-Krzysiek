package dao;

import model.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDao extends Dao{

    private ArrayList<Item> items = new ArrayList<>();

    protected ItemDao(String login, String password, String database) {
        super(login, password, database);
    }

    public List<Item> extractor(ResultSet resultSet) throws SQLException {

        while (resultSet.next()) {
            Item item = new Item();
            item.setId(resultSet.getInt(1));
            item.setName(resultSet.getString(2));
            item.setPrice(resultSet.getInt(3));
            item.setDescription(resultSet.getString(4));
            item.setCategory(resultSet.getString(5));
            items.add(item);
        }
        disconnect();
        return items;
    }
}
