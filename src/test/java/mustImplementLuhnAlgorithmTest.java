import banking.Card.AlgorithmLuna;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class mustImplementLuhnAlgorithmTest {
    public static Map<String, Integer> cardsNextNumber = new TreeMap<>();
    public static Map<String, Boolean> cardIsValid = new TreeMap<>();
    public static List<String> cardsNumber = List.of(
            "1230002", "135138954993914435", "13124", "1311124", "1354311124", "10340132103",
            "103405132103", "112", "112534", "9999", "99994", "10003",
            "100103", "1001031", "1894614372", "18946143721", "18946143729999", "318946143729999",
            "100000001", "1000000016", "1000000017", "19577671656415", "195776716564158", "43243243243",
            "4000000000000010", "4000000000010050", "990000001", "9999999999", "1", "15",
            "1001010010", "1001010011", "1001010071", "1000000", "10000040", "1234567890",
            "4082692541225725", "4046994910854761", "4615254312912266", "5287674281501465", "374725881125934", "6011733778337063",
            "6011240987513328", "3588726434764381", "358872634764381","4000000000005001");
    public static List<Integer> nextNumber = List.of(6, 1, 3, 0, 2, 1,
            0, 3, 3, 4, 6, 2,
            1, 2, 6, 3, 0, 4,
            6, 5, 3, 8, 5, 8,
            5, 9, 0, 0, 8, 8,
            4, 2, 6, 8, 5, 3,
            1, 1, 7, 8, 6, 8,
            4, 5, 3,9

    );
    public static List<Boolean> isValiid = List.of(
            true, false, false, false, false, false,
            true, false, false, false, true, false,
            false, true, false, false, false, false,
            false, true, false, false, true, true,
            true, true, false, true, false, false,
            false, false, true, false, true, false,
            true, true, true, true, true, true,
            true, true, false,true

    );

    /**
     * Fill the collection with cards and correct last digits.
     */
    @BeforeClass
    public static void init() {
        for (int i = 0; i < cardsNumber.size(); i++) {
            String card = cardsNumber.get(i);
            Integer nextN = nextNumber.get(i);
            cardsNextNumber.put(card, nextN);
            Boolean isValid = isValiid.get(i);
            cardIsValid.put(card, isValid);
        }
    }

    /**
     * A test that checks that the moon algorithm generates the check digit correctly.<br>
     * According to the given values in the collection. <br>
     * {@link AlgorithmLuna#getNextNumberCardFormatLuna()}
     */
    @Test
    public void MustValidCardNumber() {
        for (var key : cardsNextNumber.keySet()) {
            var val = cardsNextNumber.get(key);
            var numberGen = new AlgorithmLuna(key).getNextNumberCardFormatLuna();
            Assert.assertEquals(key, (Integer.valueOf(numberGen)), val);
        }
    }

    /**
     * The test checks the correctness of the {@link AlgorithmLuna#isFormattedLuna()}  method check
     */
    @Test
    public void MastIsFormattedCardLuna() {
        for (var key : cardsNextNumber.keySet()) {
            var val = cardIsValid.get(key);
            boolean numberGen = new AlgorithmLuna(key).isFormattedLuna();
            Assert.assertEquals(key, numberGen, val);
        }
    }
}
