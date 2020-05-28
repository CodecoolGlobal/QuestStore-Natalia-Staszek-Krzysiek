package menu;

import controller.MentorController;
import dao.MentorDao;

public class MentorMenu {
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
                case "8":
                    mentorController.addArtifactToCategory();
                    break;
                case "9":
                    mentorController.markArtifact();
                    break;
                case "10":
                    mentorController.showCodecollerWallet();
                    break;
                case "11":
                    isRunning = false;
                    break;
                default:
                    break;

            }
        }
    }
}
