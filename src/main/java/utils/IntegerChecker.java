package utils;

public class IntegerChecker {

    static boolean isInteger(String data) {
        try {
            Integer.parseInt(data);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

}
