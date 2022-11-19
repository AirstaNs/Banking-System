package banking.Card;

import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

import banking.Utils;

public class AlgorithmLuna {
    /**
     * If the number is greater than 9 then add up its digits. <br>
     * Example: 18 -> 9
     */
    private static final IntUnaryOperator sumDigitsNumber = n -> n / 10 + n % 10;
    /**
     * Set when initializing the card number
     */
    private Function<Integer, Boolean> alternate;
    private String card;

    public AlgorithmLuna(String card) {
        this.card = card;
        var isEvenLength = Utils.isEven(card.length());
        setIndexesExecuteAlgorithm(isEvenLength);
    }

    /**
     * Get checksum digit
     *
     * @return checksum digit
     */
    public int getNextNumberCardFormatLuna() {
        int[] charsCard = Utils.StringToNumberArray(card); // Expand the card number, start from the end.
        int sum = getSumFromCardNumber(charsCard);
        return getMissingValueMultipleTen(sum);

    }

    public boolean isFormattedLuna() {
        var lenth = card.matches("^\\d{" + DebitCardFormat.LENGTH_CARD_NUMBER + "}$");
        return lenth & getSumFromCardNumber(Utils.StringToNumberArray(card)) % 10 == 0;
    }

    /**
     * Get the sum using the Luna algorithm.
     *
     * @param charsCard Array of card digits
     * @return sum according to the Luna algorithm
     */
    public int getSumFromCardNumber(int[] charsCard) {
        return IntStream.range(0, charsCard.length)
                        .map(i -> alternate.apply(i) ? multiplyTwo(charsCard[i]) : charsCard[i])
                        .map(sumDigitsNumber)
                        .sum();
    }

    /**
     * Specify which indexes should be multiplied by two. <br>
     * Is set in the constructor. <br>
     *
     * @param isEvenLength Depends on card length. <br> If the length of the map is odd, then we go to odd indices and vice versa.
     */
    private void setIndexesExecuteAlgorithm(boolean isEvenLength) {
        if (isEvenLength) {
            alternate = Utils::isOdd;
        } else {
            alternate = Utils::isEven;
        }
    }

    /**
     * We multiply the numbers by two if they are in even / odd places. <br>
     * Depends on the parity of the length
     *
     * @param digit digit card
     * @return digit card  multiply Two
     */
    private static int multiplyTwo(int digit) {
        return digit << 1;
    }

    /**
     * Get the digit missing for parity from the sum. <br>
     * If the sum = 18 - the missing digit is 2. return 2 <br>
     * If the sum is a multiple of 10, then we extract 0. The sequence is correct.
     *
     * @param sum the amount obtained by applying the previous steps of the Luna algorithm.
     * @return number that is missing.
     */
    private int getMissingValueMultipleTen(int sum) {
        return (10 - sum % 10) % 10;
    }

}