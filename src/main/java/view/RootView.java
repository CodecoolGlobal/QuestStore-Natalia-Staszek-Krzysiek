package view;

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
        return
    }
}