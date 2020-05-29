package view;

import controller.MentorController;

public class MentorMenu {
    private MentorController mentorController;


    public void MenuMentor() throws Exception {
        boolean isRunning = true;
        while (isRunning) {
//            View.clearScreen();
            System.out.printf("YOU ARE A MENTOR");
            String[] options = {"Create a Codecooler.", "Add a new quest.", "Add a quest to category.",
                    "Edit a quest", "Mark a quest", "Add an artifact to store", "Edit an artifact",
                    "Add an artifact to category", "Mark an artifact", "Show Codecooler Wallet", "LOGOUT"};
            View.displayMenu(options);
            int choice = View.getUserChoice(options.length);

            switch (choice) {
                case 1:
                    mentorController.createCodecooler();
                    break;
                case 2:
                    mentorController.addNewQuest();
                    break;
                case 3:
                    mentorController.addQuestToCategory();
                    break;
                case 4:
                    mentorController.editQuest();
                    break;
                case 5:
                    mentorController.markQuest();
                    break;
                case 6:
                    mentorController.addArtifactToStore();
                    break;
                case 7:
                    mentorController.editArtifact();
                    break;
                case 8:
                    mentorController.addArtifactToCategory();
                case 9:
                    mentorController.markArtifact();
                    break;
                case 10:
                    mentorController.showCodecollerWallet();
                    break;
                case 11:
                    isRunning = false;
                    break;
                default:
                    break;

            }
        }
    }
}
