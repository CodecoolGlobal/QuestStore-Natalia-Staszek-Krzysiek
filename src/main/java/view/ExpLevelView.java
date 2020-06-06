package view;

import utilities.ConsoleInputGetter;

public class ExpLevelView extends View {

    public String getLevelNameInput() {
        return ConsoleInputGetter.getStringInputFromConsole("Enter level of experience name:");
    }

    public int getLevelValueInput() {
        return ConsoleInputGetter.getIntegerFromConsole("Enter value of experience to set: ");
    }

    public void displayLevelSetMessage() {
        System.out.println("Level has been set!");
        pressAnyKeyToContinue();
    }

    public void displayLevelDeletedMessage() {
        System.out.println("Experience level has been deleted!");
        pressAnyKeyToContinue();
    }

    public void displayDeleteErrorMessage() {
        System.out.println("Error deleting experience level!");
        pressAnyKeyToContinue();
    }

    public void displayThereIsNoLevelWithThisNameMessage() {
        System.out.println("There is no experience level with this name!");
        pressAnyKeyToContinue();
    }
}
