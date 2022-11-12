package banking.Card;

import banking.Formatters.FormatterInt;
import banking.client.UID;


import java.util.Objects;

import static banking.Card.ShouldBeGenerateCardNumber.BIN;

public class Card {
    private final String number;

    private final Pin PIN;

    private Balance balance;

    public Card(FormatterInt formatPin, UID UserID) {
        String stringFormatUID = UserID.getFormattedID();
        this.number = create(stringFormatUID);
        this.PIN = new Pin(formatPin);
        this.balance = new Balance(this);
    }
    public Card(String number, String PIN) {
        this.number = number;
        this.PIN = new Pin(PIN);
    }

    private String create(String ID) {
        final int ControlSum = 9; //TODO БИЛДЕР

        return String.format("%s%s%d", BIN, ID, ControlSum);
    }

    public String getNumber() {
        return number;
    }


    public Balance getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("""
                Your card number:
                %s
                %s
                """, number, PIN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        if (this.hashCode()!=o.hashCode()) return false;

        Card card = (Card) o;
        return number.equals(card.number) && PIN.equals(card.PIN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, PIN);

    }
}
