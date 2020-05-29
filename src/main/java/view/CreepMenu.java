package view;

import controller.CreepController;

public class CreepMenu {
    private CreepController creepController;

    public void Menu() throws Exception {
        boolean isRunning = true;
        while (isRunning) {
//            view.clearScreen();
            System.out.printf("YOU ARE A CREEP");
            String[] options = {"Show all Mentors.", "Create a Team.", "Edit a Mentor.",
                    "Create a Mentor","Show a Mentor","Create a Level Of Experience",
                    "LOGOUT"};
            View.displayMenu(options);
            int choice = View.getUserChoice(options.length);
            switch (choice) {
                case 1:
                    creepController.showAllMentors();
                    break;
                case 2:
                    creepController.createTeam();
                    break;
                case 3:
                    creepController.editMentor();
                    break;
                case 4:
                    creepController.createMentor();
                    break;
                case 5:
                    creepController.showMentor();
                    break;
                case 6:
                    creepController.createLevelOfExperience();
                case 7:
                    isRunning = false;
                    break;
                default:
                    break;

            }
        }
    }
}
