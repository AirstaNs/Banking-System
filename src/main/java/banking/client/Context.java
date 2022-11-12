package banking.client;

import banking.Card.Card;

import java.util.ArrayList;
import java.util.Optional;

public class Context {
    private static final Context Instance = new Context();

    private Context() {

    }

    private static final ArrayList<User> Users = new ArrayList<>();

    public void addUser(User user) {
        var userOptional = Optional.ofNullable(user);
        userOptional.ifPresent(Users::add);
    }

    public boolean containsUser(User user) {
        return Users.contains(user);
    }

    public boolean containsUser(Card cardUser) {
        return Users.stream()
                    .map(User::getCard)
                    .anyMatch(cardUser::equals);
    }

    public boolean containsUser(String number, String pin) {
        Card checkCard = new Card(number, pin);
        return Users.stream()
                    .map(User::getCard)
                    .anyMatch(checkCard::equals);
        //   return getUser(checkCard).isPresent();
    }


    public Optional<User> getUser(String number, String pin) {
        Card checkCard = new Card(number, pin);
        return getUser(checkCard);
    }

    public Optional<User> getUser(Card card) {
        return Users.stream()
                    .filter(user -> user.getCard()
                                        .equals(card))
                    .findFirst();
    }

    public boolean removeUser(User user) {
        return Users.remove(user);
    }


    public static Context getInstance() {
        return Instance;
    }

}
