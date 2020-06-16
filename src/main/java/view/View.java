package view;

import utils.Iterator;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


abstract class View {

    static Scanner scan = new Scanner(System.in);

    String getStringInput() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public void pressAnyKeyToContinue() {
        System.out.print("\nPress any key to continue: \n");
        scan.nextLine();
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public <T> void displayEntries(List<T> entries) {
        showAllEntries(entries);
        pressAnyKeyToContinue();
    }

    public <T> void displayEntriesNoInput(List<T> entries) {
        showAllEntries(entries);
    }

    private <T> void showAllEntries(List<T> entries) {
        Iterator<T> iterator = new Iterator<>(entries);
        System.out.println();

        if (!entries.isEmpty()) {
            int index = 1;

            while (iterator.hasNext()) {
                System.out.println(index + ". " + iterator.next());
                index++;
            }
        } else {
            System.out.println("List is empty!");
        }
    }

    public int askForOption() {
        int option = 0;
        try {
            System.out.print("\nEnter option: ");
            option = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("You typed the wrong sign!");
        }
        return option;
    }

    public void errorChangingValue() {
        System.out.println("Error while changing the value!");
        pressAnyKeyToContinue();
    }

    public String askForNewValue() {
        System.out.print("Enter a new value: ");
        return getStringInput();
    }

    public void errorWrongSign() {
        System.out.printf("Invalid input!");
        pressAnyKeyToContinue();
    }

    public void hasBeenPromotedMessage() {
        System.out.println("User has been promoted!");
        pressAnyKeyToContinue();
    }

    public void valueChanged() {
        System.out.printf("The value has been changed");
        pressAnyKeyToContinue();
    }
    public void userNotExist() {
        System.out.printf("User does not exist!");
        pressAnyKeyToContinue();
    }
    public void studentLoginNotExist() {
        System.out.printf("There is no student with such login!");
        pressAnyKeyToContinue();
    }

    public void displayThereIsNoStudentWithThisLogin() {
        System.out.println("There is no student with this login!");
        pressAnyKeyToContinue();
    }

    public void operationSuccess(){
        System.out.printf("Operation success!");
    }
    public void operationFailed(){
        System.out.printf("Operation failed!");
    }


}