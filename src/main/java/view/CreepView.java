package view;

import controller.MentorController;

import java.util.HashMap;
import java.util.Map;

public class CreepView extends View{
    private MentorController mentorController;

    private Map<Integer,String> menu = new HashMap<>();

    public void prepareCreepMenu() {
        menu.put(1, "Create a mentor account");
        menu.put(2, "Create a group");
        menu.put(3, "Assign mentor to group");
        menu.put(4, "Revoke mentor from group");
        menu.put(5, "Delete group and all its associations");
        menu.put(6, "Delete mentor and all his associations");
        menu.put(7, "Edit Mentor's profile");
        menu.put(8, "View mentor's profile and his class");
//        menu.put(9, "Create a level of experience based on amount of coolcoins");
//        menu.put(10, "Remove level of experience");
//        menu.put(11, "Show all levels of experience");
        menu.put(9, "Log out");
    }
    private void displayCreepMenu() {
        System.out.println("You are logged as Admin");
        for (Integer option : menu.keySet()) {
            System.out.println(option + ". " + menu.get(option));
        }
    }
    public void handleCreepMenu() {
        prepareCreepMenu();
        displayCreepMenu();
    }

    public void displayValueHasBeenChanged() {
        System.out.println("Value has been changed!");
        pressAnyKeyToContinue();
    }

}