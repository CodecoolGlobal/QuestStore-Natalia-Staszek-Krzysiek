package view;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserView extends View {

    static Scanner scan = new Scanner(System.in);

    public int askForOption() {
        int option = 0;

        try {
            System.out.print("\nEnter option: ");
            option = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("You typed ");
        }
        return option;
    }

    public Boolean getTypeOfUser() {
        String userChoice = "";
        String[] typesOfUser = {"m", "c"};

        System.out.println("Type 'm' if you want to create a mentor or 'c' if you want to create a codecooler from a user");

        while (!Arrays.asList(typesOfUser).contains(userChoice.toLowerCase())) {
            userChoice = scan.nextLine();

            if (userChoice.equals("m")) {
                return true;
            } else if (userChoice.equals("c")) {
                return false;
            }
        }
        return null;
    }

    public String askForLogin() {
        System.out.printf("\nEnter login of profile to change: ");
        return scan.nextLine();
    }

    String getStringInput() {
        return scan.nextLine();
    }

    public void userDoesNotExistMessage() {
        System.out.println("Such User does not exist!");
        pressAnyKeyToContinue();
    }

    public void emptyListMessage() {
        System.out.println("List is empty!");
        pressAnyKeyToContinue();
    }

    public void operationSuccessMessage() {
        System.out.println("Operation successful!");
    }
}
