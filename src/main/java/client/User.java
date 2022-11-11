package client;

import banking.Card.Card;

import banking.Formatters.FormatterPin;
import banking.Card.Pin;

import java.util.HashMap;
import java.util.stream.Stream;

public class User {
    private final UID ID;
    private HashMap<Card, Pin> req;
    private final Card card ;
    public User() {
        ID= new UID();
         card = new Card(new FormatterPin(),ID);//.generated(id);
    }

    @Override
    public String toString() {
        return card.toString();
    }

    public static void main(String[] args) {
        Stream.generate(User::new).limit(5).forEach(System.out::println);
    }

    public Card getCard() {
        return card;
    }
}
