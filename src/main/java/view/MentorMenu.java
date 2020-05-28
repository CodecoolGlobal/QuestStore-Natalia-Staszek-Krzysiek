package view;

public class MentorMenu {

    public static void print() {
        System.out.printf("YOU ARE A MENTOR");
        String[] options = {"Create a Codecooler.", "Add a new quest.", "Add a quest to category.",
                "Add an artifact to store", "Edit a quest", "Edit an artifact", "Add an artifact to category",
                "Mark a quest", "Mark an artifact", "Show Codecooler Wallet"};
        View.displayMenu(options);
    }
}