package controller;

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

    void addNewItem() {
        SQLItemDao sqlItemDao = new SQLItemDao();

        itemView.displayCreatingItem();

        String name = itemView.displayGetName();
        int price = itemView.displayGetPrice();
        String category = itemView.askForItemCategory();
        String description = itemView.displayGetDescription();

        Item item = new Item(name, price, description, category);

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

        int id = itemView.getIdOfItem();
        Item item = itemDAO.getItemById(id);

        if (item != null) {
            int updateOption = itemView.askForPropertyToEdit(item);
            handleUpdateBonus(updateOption, item);
        }
        itemView.pressAnyKeyToContinue();
    }

    private void handleUpdateBonus(int updateOption, Item item) {
        int UPDATE_NAME = 1;
        int UPDATE_PRICE = 2;
        int UPDATE_CATEGORY = 3;
        int UPDATE_DESCRIPTION = 4;

        if (updateOption == UPDATE_NAME) {
            item.setName(itemView.displayGetName());

        } else if (updateOption == UPDATE_PRICE) {
            item.setPrice(itemView.displayGetPrice());

        } else if (updateOption == UPDATE_CATEGORY) {
            item.setCategory(itemView.askForItemCategory());

        } else if (updateOption == UPDATE_DESCRIPTION) {
            item.setDescription(itemView.displayGetDescription());

        } else {
            itemView.operationFailed();
            return;
        }

        boolean isUpdate = itemDAO.update(item);
        if (isUpdate) {
            itemView.displayItemHasBeenAdded();

        } else {
            itemView.operationFailed();
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
