package utils;

/**
 * @author: zilla0148
 * @date: 2018/12/26 10:32
 */
public class FormatChecker {
    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;
        boolean isLetter = false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit( str.charAt( i ) )) {
                isDigit = true;
            } else if (Character.isLetter( str.charAt( i ) )) {
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]{6,18}$";
        return isDigit && isLetter && str.matches( regex );
    }
}
