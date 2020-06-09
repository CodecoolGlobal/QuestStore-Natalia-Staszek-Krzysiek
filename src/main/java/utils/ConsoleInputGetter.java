package utils;

import java.util.Scanner;

import static utils.IntegerChecker.isInteger;

public class ConsoleInputGetter {

    static Scanner scan = new Scanner(System.in);

    public static String getStringInputFromConsole(String data) {

        String input = null;
        boolean isCorrectInput = false;

        while(!isCorrectInput) {
            System.out.print(data);
            input = getStringInput();
            if (input.trim().length() > 0) {
                isCorrectInput = true;
            }
        }
        return input;
    }

    public static int getIntegerFromConsole(String data) {

        String input = null;
        boolean isCorrectInput = false;

        while(!isCorrectInput) {
            System.out.print(data);
            input = getStringInput();
            if (input.trim().length() > 0 && isInteger(data)) {
                isCorrectInput = true;
            }
        }
        return Integer.parseInt(input);
    }

    private static String getStringInput() {
        return scan.nextLine();
    }
}
