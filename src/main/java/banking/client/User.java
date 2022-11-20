package banking.client;

import banking.Card.Card;

import banking.Formatters.PinFormat;

import java.util.Objects;

public class User {
    private final UID ID;
    private final Card card;

    public User() {
        ID = new UID();
        card = new Card(new PinFormat(), ID);//.generated(id);
    }

    public void printToConsole() {
        card.printConsole();
    }

    @Override
    public String toString() {
        return card.toString();
    }

    public Card getCard() {
        return card;
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
