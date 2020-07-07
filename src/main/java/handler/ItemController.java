package handler;

import dao.ItemDAO;
import dao.SQL.SQLItemDao;
import dao.StudentItemDAO;
import dao.UserDAO;
import model.Item;
import model.User;
import view.ItemView;

import java.util.ArrayList;
import java.util.List;

public class ItemController {

    private ItemDAO itemDAO;
    private UserDAO userDAO;
    private StudentItemDAO studentItemDAO;
    private ItemView itemView;

    public ItemController(ItemDAO itemDAO, UserDAO userDAO, StudentItemDAO studentItemDAO, ItemView itemView) {
        this.itemDAO = itemDAO;
        this.userDAO = userDAO;
        this.studentItemDAO = studentItemDAO;
        this.itemView = itemView;
    }

    void addNewItem(int id_creator) {
        SQLItemDao sqlItemDao = new SQLItemDao();

        itemView.displayCreatingItem();

        String name = itemView.displayGetName();
        int price = itemView.displayGetPrice();
        String category = itemView.askForItemCategory();
        String description = itemView.displayGetDescription();

        Item item = new Item(id_creator, name, price, description, category);

        if (sqlItemDao.add(item)) {
            itemView.displayItemHasBeenAdded();
        } else {
            itemView.operationFailed();
        }
    }

    void editItem() {
        itemView.clearConsole();
        List<Item> items = new ArrayList<>(itemDAO.getAllItems());
        itemView.displayEntriesNoInput(items);
        if (items.isEmpty()) {
            itemView.displayNoItems();
            return;
        }

        int itemId = itemView.getIdOfItem();

        if (itemDAO.getItemById(itemId) != null) {
            updateItem(itemDAO.getItemById(itemId));
        } else {
            itemView.operationFailed();
        }
    }

    private void updateItem(Item item) {
        final String UPDATE_NAME = "1";
        final String UPDATE_DESCRIPTION = "2";
        final String UPDATE_PRICE = "3";
        final String UPDATE_CATEGORY = "4";

        switch (itemView.askForPropertyToEdit(item)) {
            case UPDATE_NAME:
                item.setName(itemView.displayGetName());
                itemDAO.update(item);
                break;
            case UPDATE_DESCRIPTION:
                item.setDescription(itemView.displayGetDescription());
                itemDAO.update(item);
                break;
            case UPDATE_PRICE:
                item.setPrice(itemView.displayGetPrice());
                itemDAO.update(item);
                break;
            case UPDATE_CATEGORY:
                item.setCategory(itemView.askForItemCategory());
                itemDAO.update(item);
                break;
            default:
                itemView.displayWrongOptionMessage();
        }
    }

    void markStudentUsedItem() {
        List<User> students = new ArrayList<>(userDAO.getAllByRole(3));
        itemView.displayEntriesNoInput(students);
        if (students.isEmpty()) {
            itemView.pressAnyKeyToContinue();
            return;
        }
        String studentLogin = itemView.getStudentLoginToMarkArtifact();
        if (userDAO.getByLoginAndRole(studentLogin, 3) != null) {
            choseArtifactToMark(studentLogin);
        } else {
            itemView.displayThereIsNoStudentWithThisLogin();
        }
    }

    private void choseArtifactToMark(String studentLogin) {
        User student = userDAO.getByLogin(studentLogin);
        List<Item> items = new ArrayList<>(itemDAO.getItemsByStudentId(student.getId()));
        itemView.displayEntriesNoInput(items);
        if (items.isEmpty()) {
            itemView.pressAnyKeyToContinue();
            return;
        }
        String itemName = itemView.getItemNameInput();
        if (itemDAO.getItemByName(itemName) != null) {
            Item item = itemDAO.getItemByName(itemName);
            if (studentItemDAO.markItemAsUsed(student.getId(), item.getId())) {
                itemView.displayItemHasBeenMarked();
            } else {
                itemView.displayErrorMarkingItem();
            }
        }
    }
}
