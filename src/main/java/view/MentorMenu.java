package view;

import controller.MentorController;
import dao.MentorDao;

public class MentorMenu {
<<<<<<< HEAD

    public static void print() {
        System.out.printf("YOU ARE A MENTOR");
        String[] options = {"Create a Codecooler.", "Add a new quest.", "Add a quest to category.",
                "Add an artifact to store", "Edit a quest", "Edit an artifact", "Add an artifact to category",
                "Mark a quest", "Mark an artifact", "Show Codecooler Wallet"};
        View.displayMenu(options);
    }
}
=======
    private MentorController mentorController;


    public void MenuMentor() throws Exception {
        boolean isRunning = true;
        while (isRunning) {
            view.clearScreen();
            view.displayMenu();
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    mentorController.createCodecooler();
                    break;
                case "2":
                    mentorController.addNewQuest();
                    break;
                case "3":
                    mentorController.addQuestToCategory();
                    break;
                case "4":
                    mentorController.editQuest();
                    break;
                case "5":
                    mentorController.markQuest();
                    break;
                case "6":
                    mentorController.addArtifactToStore();
                    break;
                case "7":
                    mentorController.editArtifact();
                    break;
                    mentorController.addArtifactToCategory();
                case "8":
                    mentorController.markArtifact();
                    break;
                case "9":
                    mentorController.showCodecollerWallet();
                    break;
                case "10":
                    isRunning = false;
                    break;
                default:
                    break;

            }
        }
    }
}
>>>>>>> bee9623c7cf2de9bf855949e0523286946fc71a9
