package dao;

import model.Item;

import java.util.List;

public interface ItemDAO {

    List<Item> getItemsByStudentId(int student_id);
    List<Item> getItemsByCategory(String category);
    List<Item> getAllItems();
    Item getItemByName(String itemName);
    Item getItemById(int id);
    boolean add(Item item);
    boolean update(Item item);
    boolean delete(Item item);
}