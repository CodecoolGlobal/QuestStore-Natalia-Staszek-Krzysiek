package view;

import utils.ConsoleInputGetter;
import model.Quest;

import java.util.Arrays;

public class QuestView extends View {

    public String getQuestNameInput() {
        return ConsoleInputGetter.getStringInputFromConsole("Enter quest name: ");
    }

    public void displayQuestAlreadyExists() {
        System.out.println("Quest with this name already exists!");
        pressAnyKeyToContinue();
    }

    public int getQuestPointsInput() {
        return ConsoleInputGetter.getIntegerFromConsole("Enter points prize: ");
    }

    public String getQuestDescriptionInput() {
        return ConsoleInputGetter.getStringInputFromConsole("Enter quest description: ");
    }

    public String getQuestCategory() {
        final String[] CORRECT_OPTIONS = {"b","e"};
        String userInput = "";
        boolean userInputInCorrectoptions = false;

        while (!userInputInCorrectoptions) {
            userInput = ConsoleInputGetter.getStringInputFromConsole("Enter 'b' to add basic quest or 'e' to add an extra one: ");
            userInputInCorrectoptions = Arrays.asList(CORRECT_OPTIONS).contains(userInput.toLowerCase());
            if(!userInputInCorrectoptions) {
                System.out.println("Wrong input!");
            }
        }
        return userInput;
    }

    public void displayQuestSuccessfullyAdded() {
        System.out.println("Quest successfully added!");
        pressAnyKeyToContinue();
    }

    public void displayErrorAddingQuest() {
        System.out.println("Error adding quest!");
        pressAnyKeyToContinue();
    }

    public void displayThereIsNoTaskWithThisName() {
        System.out.println("There is no task with this name!");
        pressAnyKeyToContinue();
    }

    public void displayWrongOptionMessage() {
        System.out.println("You have chosen wrong option!");
        pressAnyKeyToContinue();
    }

    public String getValueToUpdate(Quest quest) {
        System.out.println("\n" + quest);
        System.out.println("\nWhat would you like to change?: " +
                "\n1. Points" +
                "\n2. Description" +
                "\n3. Category");
        return ConsoleInputGetter.getStringInputFromConsole("Enter option");
    }

    public int askForPointsInput() {
        return ConsoleInputGetter.getIntegerFromConsole("Enter new points value: ");
    }

    public String askForDescriptionInput() {
        return ConsoleInputGetter.getStringInputFromConsole("Enter new description value: ");
    }

    public String getCodecoolerLoginToMarkQuest() {
        return ConsoleInputGetter.getStringInputFromConsole("Enter codecooler login to mark the quest: ");
    }

    public void displayQuestConnectionAdded() {
        System.out.println("Quest codecooler connection added!");
    }

    public void displayErrorAddingQuestConnection() {
        System.out.println("Error adding quest codecooler connection!");
        pressAnyKeyToContinue();
    }
}
