package controller;

public class MentorController {

    public MentorController() {
    }

    public void MenuMentor() throws Exception {
        boolean isRunning = true;
        while (isRunning) {
            view.clearScreen();
            view.displayMenu();
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    createCodecooler();
                    break;
                case "2":
                    addNewQuest();
                    break;
                case "3":
                    addQuestToCategory();                    break;
                case "4":
                    editQuest();
                    break;
                case "5":
                    markQuest();
                    break;
                case "6":
                    addArtifactToStore();
                    break;
                case "7":
                    editArtifact();
                    break;
                    addArtifactToCategory();
                case "8":
                    markArtifact();
                    break;
                case "9":
                    showCodecollerWallet();
                    break;
                case "10":
                    isRunning = false;
                    break;
                default:
                    break;

            }
        }
    }
    public void createCodecooler(){

    }

    public void addNewQuest(){

    }

    public void addQuestToCategory(){

    }

    public void addArtifactToStore(){

    }

    public void editQuest(){

    }

    public void editArtifact(){

    }

    public void addArtifactToCategory(){

    }

    public void markQuest(){

    }

    public void markArtifact(){

    }

    public void showCodecollerWallet(){

    }



}
