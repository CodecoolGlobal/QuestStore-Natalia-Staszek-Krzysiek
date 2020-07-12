package dao.SQL;

import dao.ItemDAO;
import model.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SQLItemDao extends Database_Connection implements ItemDAO {


    @Override
    public List<Item> getItemsByStudentId(int studentId) {
        String sqlStatement = "SELECT name,description,price,category FROM items JOIN students_items " +
                "ON items.id = students_items.id_item WHERE students_items.id_student_details=?;";
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(studentId), sqlStatement);
        return getItemsBy(statement);
    }

    @Override
    public List<Item> getItemsByCategory(String category) {
        String sqlStatement = "SELECT * FROM items WHERE category=?;";
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(category), sqlStatement);
        return getItemsBy(statement);
    }

    @Override
    public List<Item> getAllItems() {
        String sqlStatement = "SELECT * FROM items;";
        PreparedStatement statement = getPreparedStatementBy(Collections.emptyList(), sqlStatement);
        return getItemsBy(statement);
    }


    private List<Item> getItemsBy(PreparedStatement statement) {

        List<Item> items = new ArrayList<>();
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next()) {
                items.add(new Item (
                        resultSet.getInt("id"),
                        resultSet.getInt("id_creator"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("description"),
                        resultSet.getString("category")));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            disconnect();
        }
        return items;
    }

    @Override
    public Item getItemById(int id) {
        String sqlStatement = "SELECT * FROM items WHERE id = ?;";
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(id), sqlStatement);
        return getItemFromStore(statement);
    }

    @Override
    public Item getItemByName(String itemName) {
        String sqlStatement = "SELECT * FROM items WHERE name=?;";
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(itemName), sqlStatement);
        return getItemFromStore(statement);
    }

    private Item getItemFromStore(PreparedStatement statement) {

        Item item = null;
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next()) {
                item = new Item (
                        resultSet.getInt("id"),
                        resultSet.getInt("id_creator"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("description"),
                        resultSet.getString("category"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            disconnect();
        }
        return item;
    }

    public boolean add(Item item) {
        String sqlStatement = "INSERT INTO items (id_creator,name,description,price,category) VALUES (?,?,?,?,?);";
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(item.getId_creator(), item.getName(), item.getDescription(),
                item.getPrice(), item.getCategory()), sqlStatement);
        return update(statement);
    }

    public boolean update(Item item) {
        String sqlStatement = "UPDATE items SET id=?,name=?,description=?,price=?,category=? WHERE id=?;";
        PreparedStatement statement = getPreparedStatementBy(Arrays.asList(item.getId(),item.getName(), item.getDescription(),
                item.getPrice(), item.getCategory(), item.getId()), sqlStatement);
        return update(statement);
    }

    @Override
    public boolean delete(Item item) {
        String sqlStatement = "DELETE FROM items WHERE id=?;";
        PreparedStatement statement = getPreparedStatementBy(Collections.singletonList(item.getId()), sqlStatement);
        return update(statement);
    }
}
