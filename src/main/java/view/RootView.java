package view;

import utils.ConsoleInputGetter;

public class RootView extends View{

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

    public void wrongInputMessage() {
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

    public String getNewUserLogin() {
        System.out.print("Enter login (min. 6 - max. 15)");
        return getStringInput();
    }
    public String getNewUserPassword() {
        System.out.print("Enter password (min. 6 - max. 15");
        return getStringInput();
    }
    public  String getNewUserName() {
        return ConsoleInputGetter.getStringInputFromConsole("Enter name: ");
    }
    public String getNewUserEmail() {
        System.out.print("Enter email: ");
        return getStringInput();
    }
    public String getNewUserPhoneNumber() {
        System.out.print("Enter phone number is format (000-000-000): ");
        return getStringInput();
    }
    public void userNotAssignedMessage() {
        System.out.println("\nUser is not assigned yet, wait for Creep to approve.");
        pressAnyKeyToContinue();
    }
    public void userAlreadyExistMessage() {
        System.out.println("\nUser with this login already exists!");
        pressAnyKeyToContinue();
    }
    public void displayCreatedUser(String login, String name, String email, String phoneNumber) {
        System.out.println("\nUser created: " +
                "\nLogin: " + login +
                "\nName: " + name +
                "\nEmail: " + email +
                "\nPhone number: " + phoneNumber +
                "\nWARNING! WAIT FOR ADMIN TO ACTIVATE IT;)!");
        pressAnyKeyToContinue();
    }
    public void userNotExistMessage() {
        System.out.println("\nSuch User does not exist!");
    }
    public void userWithEmailAlreadyExist() {
        System.out.println("\nA User with this email already exists!");
        pressAnyKeyToContinue();
    }
    public void userWithPhoneAlreadyExist() {
        System.out.println("\nA User with this phone number already exists!");
        pressAnyKeyToContinue();
    }
}