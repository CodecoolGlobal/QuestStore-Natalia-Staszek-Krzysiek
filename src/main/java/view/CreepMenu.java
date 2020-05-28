package view;

import controller.CreepController;

public class CreepMenu {
    private CreepController creepController;

    public void Menu() throws Exception {
        boolean isRunning = true;
        while (isRunning) {
            view.clearScreen();
            view.displayMenu();
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    creepController.showAllMentors();
                    break;
                case "2":
                    creepController.createTeam();
                    break;
                case "3":
                    creepController.editMentor();
                    break;
                case "4":
                    creepController.createMentor();
                    break;
                case "5":
                    creepController.showMentor();
                    break;
                case "6":
                    creepController.createLevelOfExperience();
                case "7":
                    isRunning = false;
                    break;
                default:
                    break;

            }
        }
    }
}
