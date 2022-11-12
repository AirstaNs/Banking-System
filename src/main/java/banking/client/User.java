package banking.client;

import banking.Card.Card;

import banking.Formatters.PinFormat;
import banking.Card.Pin;

import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Stream;

public class User {
    private final UID ID;
    private HashMap<Card, Pin> req;
    private final Card card;

    public User() {
        ID = new UID();
        card = new Card(new PinFormat(), ID);//.generated(id);
    }

    @Override
    public String toString() {
        return card.toString();
    }

    public static void main(String[] args) {
        Stream.generate(User::new)
              .limit(5)
              .forEach(System.out::println);
    }

    public Card getCard() {
        return card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return ID.equals(user.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
