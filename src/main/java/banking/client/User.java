package banking.client;

import banking.Card.Balance;
import banking.Card.Card;

import banking.Formatters.PinFormat;

import java.util.Objects;

public class User {
    private final UID ID;
    private final Card card;


    public User() {
        ID = new UID();
        card = new Card(new PinFormat(), ID);
    }

    public User(int id, String card_number, String pin, int balance) {
        ID = new UID(id);
        card = new Card(card_number, pin);
        card.setBalance(new Balance(card));
        card.getBalance()
            .deposit((long) balance);
    }

    public void printToConsole() {
        card.printConsole();
        card.PIN()
            .printConsole();
    }

    @Override
    public String toString() {
        return card.toString();
    }

    public Card Card() {
        return card;
    }

    public UID ID() {
        return ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (this.hashCode() != o.hashCode()) return false;
        User user = (User) o;
        return ID.equals(user.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
