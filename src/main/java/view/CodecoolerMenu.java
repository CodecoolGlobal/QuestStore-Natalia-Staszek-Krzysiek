package view;

public class CodecoolerMenu {

    public static void print() {
        System.out.printf("YOU ARE A CODECOOLER");
        String[] options = {"See my wallet.", "Buy an artifact.",
                "Buy an artifact together with your team.", "See my level of experience"};
        View.displayMenu(options);
    }
}


