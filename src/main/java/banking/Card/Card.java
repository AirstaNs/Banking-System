package banking.Card;

import banking.Formatters.FormatterInt;
import banking.client.UID;


import java.util.Objects;

import static banking.Card.ShouldBeGenerateCardNumber.BIN;

public class Card {
    private final String number;

    private final Pin pin;

    public Card(FormatterInt formatPin, UID UserID) {
        String stringFormatUID = UserID.getFormattedID();
        this.number = create(stringFormatUID);
        this.pin = new Pin(formatPin);
    }

    private String create(String ID) {
        final int ControlSum = 9; //TODO БИЛДЕР

        return String.format("%s%s%d", BIN, ID, ControlSum);
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.format("""
                Your card number:
                %s
                %s
                """, number, pin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return number.equals(card.number) && pin.equals(card.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, pin);
    }
}
