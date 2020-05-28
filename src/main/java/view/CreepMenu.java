package view;

public class CreepMenu {

    public static void print() {
        System.out.printf("YOU ARE A CREEP");
        String[] options = {"Show all Mentors.", "Create a Team.", "Edit a Mentor.",
                "Create a Mentor","Show a Mentor","Create a Level Of Experience"};
        View.displayMenu(options);
    }
}