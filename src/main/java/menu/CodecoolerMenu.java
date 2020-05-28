package menu;


import controller.CodecoolerController;

public class CodecoolerMenu {
    private CodecoolerController codecoolerController;

    public void MenuMentor() throws Exception {
        boolean isRunning = true;
        while (isRunning) {
            view.clearScreen();
            view.displayMenu();
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    codecoolerController.showWallet();
                    break;
                case "2":
                    codecoolerController.buyArtifact();
                    break;
                case "3":
                    codecoolerController.buyArtifactAsTeam();
                    break;
                case "4":
                    codecoolerController.showLevelOfExperience();
                    break;
                case "5":
                    isRunning = false;
                    break;
                default:
                    break;

            }
        }
    }
}
