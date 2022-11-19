package banking;


public class Utils {

    /**
     * Method that checks if the passed value is even
     *
     * @param value Value to check for parity
     * @return even or NOT
     */
    public static boolean isEven(int value) {
        return value % 2 == 0;
    }

    /**
     * Method that checks if the passed value is even
     *
     * @param value Value to check for parity
     * @return odd or NOT
     */
    public static boolean isOdd(int value) {
        return (value & 1) != 0;
    }

    /**
     * Returns an array of numbers from a string
     *
     * @param str Digit string
     * @return Array of digits from the passed string
     */
    public static int[] StringToNumberArray(String str) {

        return str.chars()
                  .map(Character::getNumericValue)
                  .toArray();
    }

    /**
     * Checks if a string contains only digits
     *
     * @param str The string to check
     * @return Does the string contain only numbers
     */
    public static boolean isOnlyDigitsString(String str) {
        return isOnlyDigitsLength(str, str.length());
    }

    /**
     * The method checks the string for only digits and length
     *
     * @param str    The string to check
     * @param length String length
     * @return Returns true - if the length of the string matches length and is only digit
     */
    public static boolean isOnlyDigitsLength(String str, long length) {
        return str.matches("\\d{" + length + "}");
    }


}
