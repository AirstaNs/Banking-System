import banking.Card.AlgorithmLuna;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;


public class mustImplementLuhnAlgorithmTest {
    public static Map<String, Integer> cards = new TreeMap<>();

    /**
     * Fill the collection with cards and correct last digits.
     */
    @BeforeClass
    public static void init() {
        cards.put("1230002", 6);
        cards.put("135138954993914435", 1);
        cards.put("13124", 3);
        cards.put("1311124", 0);
        cards.put("1354311124", 2);
        cards.put("10340132103", 1);
        cards.put("103405132103", 0);
    }

    /**
     * A test that checks that the moon algorithm generates the check digit correctly.<br>
     * According to the given values in the collection.
     */
    @Test
    public void MustValidCardNumber() {
        for (var key : cards.keySet()) {
            var val = cards.get(key);
            var numberGen = new AlgorithmLuna(key).getNextNumberCardFormatLuna();
            Assert.assertEquals(key, (Integer.valueOf(numberGen)), val);
        }
    }
}
