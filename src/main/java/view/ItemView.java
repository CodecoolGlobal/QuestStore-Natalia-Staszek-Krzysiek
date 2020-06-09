package view;

import utils.ConsoleInputGetter;
import model.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class ItemView extends View {

    public void displayCreatingItem() {
        clearConsole();
        System.out.println("Create new bonus menu");
    }

    public String displayGetName() {
        return ConsoleInputGetter.getStringInputFromConsole("Enter new name: ");
    }

    public int displayGetPrice() {
        return ConsoleInputGetter.getIntegerFromConsole("Enter new price");
    }

    public String displayGetDescription() {
        return ConsoleInputGetter.getStringInputFromConsole("Enter new description: ");
    }

    public void displayItemHasBeenAdded() {
        System.out.println("Artifact has been added successfully!");
    }

    public String askForItemCategory() throws InputMismatchException {
        List<String> itemCategories = new ArrayList<>(Arrays.asList("Basic","Extra"));

        String userCategoryChoose = "";

        while (!itemCategories.contains(userCategoryChoose)) {
            System.out.println("Choose category of bonus, type \'Basic\' or \'Extra\': ");
            userCategoryChoose = scan.next();
        }

        return userCategoryChoose;
    }

    public int getIdOfItem() {
        return ConsoleInputGetter.getIntegerFromConsole("\nEnter id of item to edit: ");
    }

    public int askForPropertyToEdit(Item item) {
        clearConsole();
        return ConsoleInputGetter.getIntegerFromConsole(item.toString() +
                "\n\nWhat would you like to update?:" +
                "\n1. Name" +
                "\n2. Price" +
                "\n3. Category" +
                "\n4. Description");
    }

    public void displayNoItems() {
        System.out.println("There is no items!");
        pressAnyKeyToContinue();
    }

    public String getStudentLoginToMarkArtifact() {
        return ConsoleInputGetter.getStringInputFromConsole("Enter student login to mark the artifact: ");
    }

    public String getItemNameInput() {
        return ConsoleInputGetter.getStringInputFromConsole("Enter item name: ");
    }

    public void displayItemHasBeenMarked() {
        System.out.println("Item has been marked!");
        pressAnyKeyToContinue();
    }

    public void displayErrorMarkingItem() {
        System.out.println("Error marking item!");
        pressAnyKeyToContinue();
    }
}
