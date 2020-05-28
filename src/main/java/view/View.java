package view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class View {

    static Scanner scan = new Scanner(System.in);

    public static String getInput(){
        return scan.nextLine();
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void displayMenu(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println(String.format("(%d) %s",i+1,options[i]));
        }
    }

    public static int getUserChoice(int optionsLength) {
        boolean isCorrect;
        String userChoice = "";
        do {
            Scanner scan = new Scanner(System.in);
            userChoice = scan.nextLine();
            isCorrect = validateUserChoice(userChoice, optionsLength);
        }while(!isCorrect);
        return Integer.parseInt(userChoice);
    }

    public static boolean validateUserChoice(String userChoice, int optionsLength) {
        try {
            int parsedUserChoice = Integer.parseInt(userChoice);
            return !(parsedUserChoice > optionsLength  || parsedUserChoice < 1);
        }catch(Exception e){
            printErrorMessage("Invalid input!");
            return false;
        }
    }

    public static void showPersonList(List<String> people) {
        for (int i  = 0; i < people.size(); i++) {
            System.out.println((i + 1) + ". " + people.get(i));
        }
    }

    public static void displayGrades(Map<String,Integer> grades){
        for (String assigment: grades.keySet()){
            System.out.println(assigment+": "+grades.get(assigment));
        }
    }

}
