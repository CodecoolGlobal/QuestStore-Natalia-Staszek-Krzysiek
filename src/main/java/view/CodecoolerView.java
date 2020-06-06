package view;


import controller.CodecoolerController;

public class CodecoolerView {
    private CodecoolerController codecoolerController;

    public void MenuMentor() throws Exception {
        boolean isRunning = true;
        while (isRunning) {
//            View.clearScreen();
            System.out.printf("YOU ARE A CODECOOLER");
            String[] options = {"See my wallet.", "Buy an artifact.",
                    "Buy an artifact together with your team.", "See my level of experience","LOGOUT"};
            View.displayMenu(options);
            int choice = View.getUserChoice(options.length);
            switch (choice) {
                case 1:
                    codecoolerController.showWallet();
                    break;
                case 2:
                    codecoolerController.buyArtifact();
                    break;
                case 3:
                    codecoolerController.buyArtifactAsTeam();
                    break;
                case 4:
                    codecoolerController.showLevelOfExperience();
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
                    break;
            }
        }
    }
}
