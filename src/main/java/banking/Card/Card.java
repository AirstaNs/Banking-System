package banking.Card;

import banking.Formatters.FormatterInt;
import banking.client.UID;


import java.util.Objects;

import static banking.Card.DebitCardFormat.BIN;

/**
 * A class that describes the Card of a specific client.
 */
public class Card {
    /**
     * The variable storing the card number must be 16 numbers.
     */
    private final String number;
    /**
     * The PIN code of the card, which stores the PIN code in the required format. <br>
     * Without a PIN code, you cannot enter the user's personal account.
     */
    private final Pin PIN;
    /**
     * The card has a certain balance, all deposits/withdrawals occur with the help of this variable. <br>
     * It has a strong connection with the Map.
     */
    private Balance Balance;

    /**
     * When creating the Card, you need to pass the PIN code format, as well as the unique ID of the card user.
     *
     * @param formatPin The number of digits in the pin code.
     * @param UserID    A unique ID is needed for a strong connection between the card and the user.
     */
    public Card(FormatterInt formatPin, UID UserID) {
        String stringFormatUID = UserID.getFormattedID();
        this.number = create(stringFormatUID);
        this.PIN = new Pin(formatPin);
        this.Balance = new Balance(this);
    }

    /**
     * Creating a map from text values. Needed to search for a user on the map.
     *
     * @param number Card number
     * @param PIN    Pin code of the card
     */
    public Card(String number, String PIN) {
        this.number = number;
        this.PIN = new Pin(PIN);
    }

    /**
     * A method that creates cards in a specific format for a specific user.
     *
     * @param ID user ID
     * @return card number in the required format
     */
    private String create(String ID) {
        StringBuilder builder = new StringBuilder();
        builder.append(BIN)
               .append(ID);
        final int ControlSum = new AlgorithmLuna(builder.toString()).getNextNumberCardFormatLuna();
        builder.append(ControlSum);
        return builder.toString();
    }

    public String getNumber() {
        return number;
    }

    public Pin PIN() {
        return PIN;
    }

    public Balance Balance() {
        return Balance;
    }

    public void setBalance(Balance balance) {
        Balance = balance;
    }

    @Override
    public String toString() {
        return number;
    }

    public void printConsole() {
        System.out.printf("""
                Your card number:
                %s
                """, number);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        if (this.hashCode() != o.hashCode()) return false;

        Card card = (Card) o;
        return number.equals(card.number) && PIN.equals(card.PIN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, PIN);

    }
}
