package view;

import utilities.ConsoleInputGetter;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class MentorView extends View{

    private Map<Integer,String> menu = new HashMap<>();

    public void prepareMentorMenu() {
        menu.put(1, "Create a codecooler");
        menu.put(2, "Add a codecooler to a class");
        menu.put(3, "Create a new quest");
        menu.put(4, "Create a new artifact");
        menu.put(5, "Edit quest");
        menu.put(6, "Edit artifact");
        menu.put(7, "Mark codecooler achieved quest");
        menu.put(8, "Mark codecooler bought artifact.");
        menu.put(9, "Show codecooler wallet and all his artifacts.");
        menu.put(10, "Reroll students teams");
        menu.put(11, "Log out");
    }

    private void displayMentorMenu() {
        for (Integer option : menu.keySet()) {
            System.out.println(option + ". " + menu.get(option));
        }
    }

    public void handleMentorMenu() {
        prepareMentorMenu();
        clearConsole();
        displayMentorMenu();
    }

    public String getStudentLoginToAssignToGroup() {
        return ConsoleInputGetter.getStringInputFromConsole("Enter student name to assign him to a group");
    }

    public String getGroupNameInput() {
        return ConsoleInputGetter.getStringInputFromConsole("Enter group name:");
    }

    public void displayGroupUpdated() {
        System.out.println("Group has been updated!");
        pressAnyKeyToContinue();
    }

    public void errorChangingValueMessage() {
        System.out.println("Error changing the value!");
        pressAnyKeyToContinue();
    }

    public String getMentorLoginToDelete() {
        System.out.println("Enter mentor login to delete all his data: ");
        return getStringInput();
    }

    public void displayMentorDeletedMessage() {
        System.out.println("Mentor has been deleted!");
        pressAnyKeyToContinue();
    }

    public void displayNoMentorMessage() {
        System.out.println("There is no mentor with this login!");
        pressAnyKeyToContinue();
    }

    public String getMentorLoginToShowProfile() {
        System.out.print("Enter login of the mentor: ");
        return getStringInput();
    }

    public void displayMentorProfile(User mentor) {
        System.out.println("");
        System.out.println(mentor);
    }

    public String getMentorLoginToEdit() {
        System.out.println("\nEnter mentor login to edit his data or q to go back:");
        return getStringInput();
    }

    public void displayThereIsNoMentorWithThisLogin() {
        System.out.println("There is no mentor with this login!");
        pressAnyKeyToContinue();
    }

    public int askForChangeInProfile(User profile) {
        System.out.println("\n" + profile.toString());
        System.out.println("\nWhat would you like to change in profile:" +
                "\n1. Name" +
                "\n2. Login" +
                "\n3. Email" +
                "\n4. Phone Number");

        return askForOption();
    }

    public String getMentorLoginToAssignGroup() {
        System.out.println("Enter mentor login: ");
        return getStringInput();
    }

    public String getMentorLoginToRevokeFromGroup() {
        System.out.println("Enter mentor login to revoke him from a group: ");
        return getStringInput();
    }

    public void displayMentorHasNoGroupAssigned() {
        System.out.println("Mentor has no groups assigned!");
        pressAnyKeyToContinue();
    }
}