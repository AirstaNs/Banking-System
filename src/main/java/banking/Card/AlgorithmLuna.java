package banking.Card;


import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class AlgorithmLuna {


    public static void main(String[] args) {
        String card = "135138954993914435";
    }


    public static String getLastNumberCardFormatLuna(String card) {
        int[] charsCard = StringToIntArray(card);
        var isEvenLength = charsCard.length % 2 == 0;
        Function<Integer, Boolean> br1 = index -> (isEvenLength ? (index & 1) != 0 : (index % 2 == 0));

        /*
               Если length четное то false , если нечетное то true
                */

        IntUnaryOperator sumDigits = n -> n / 10 + n % 10;
        IntUnaryOperator multiplyTwo = digit -> digit * 2;


        int sum = IntStream.range(0, card.length())
                           .map(i -> br1.apply(i) ? multiplyTwo.applyAsInt(charsCard[i]) : charsCard[i])
                           .map(sumDigits)
                           .sum();
        return (10 - sum % 10) % 10 + "";
    }


    //    public static int calculateLuhnsCheckDigit(String number) {
    //        int     sum       = 0;
    //        boolean alternate = false;
    //
    //        for (int i = number.length() - 1; i >= 0; --i) {
    //            int digit = Character.getNumericValue(number.charAt(i)); // get the digit at the given index
    //            System.out.println(i);
    //            System.out.println(i+" "+alternate);
    //            alternate = !alternate;
    //            digit = (alternate) ? (digit * 2) : digit;  // double every other digit
    //            digit = (digit >= 10) ? (digit / 10 + digit % 10) : digit;  // subtract 9 if the value is greater than 9
    //            sum += digit;                                            // add the digit to the sum
    //        }
    //
    //        return (10 - sum % 10) % 10;
    //        //return (10 - sum % 10) % 10;
    //    }

    public static boolean isFormattedLuna(String numberCard) {
        //   if(numberCard.length()!=CardFormat.LENGTH_CARD_NUMBER) return false;
        return numberCard.matches("^\\d{" + CardFormat.LENGTH_CARD_NUMBER + "}$");
    }

    private static int[] StringToIntArray(String str) {
        return str.chars()
                  .map(Character::getNumericValue)
                  .toArray();
    }
}
