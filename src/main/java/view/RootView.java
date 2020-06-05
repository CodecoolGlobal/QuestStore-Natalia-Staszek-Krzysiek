package view;

import java.util.Scanner;

public class RootView {

    public void displayMenu() {
        System.out.println("QUEST STORE" +
                "\nAVAILABLE OPTIONS:" +
                "1. LOGIN IN\n" +
                "2. SIGN UP\n" +
                "0. EXIT QUEST STORE");
    }

    public String getUserInput() {
        System.out.print("Choose option: ");
        return getStringInput();
    }

    private String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showWrongInputMessage() {
        System.out.println("WROND INPUT!");
    }

    public String getUserLogin() {
        System.out.print("Enter login: ");
        return getStringInput();
    }

    public String getUserPassword() {
        System.out.print("Enter password: ");
        return getStringInput();
    }

    public void userNotAssignedMessage() {
        System.out.printf("\nUser has not been assigned yet!");
        showPressAnyKeyToContinueMessage();
    }

    public void showPressAnyKeyToContinueMessage() {
        System.out.print("\nPress any key to continue.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public String createUserLogin() {
        System.out.printf("Enter password (6-15 characters: ");
        return getStringInput();
    }

    public String createUserPassword() {
        System.out.print("Enter password (6-15 characters: ");
        return getStringInput();
    }
}