package banking.DAO;

import banking.Card.Card;
import banking.client.User;

import java.util.ArrayList;
import java.util.Optional;

public class ContextList implements Context {
    private static final ContextList Instance = new ContextList();

    private ContextList() {

    }

    private static final ArrayList<User> Users = new ArrayList<>();

    public int addUser(User user) {
        var userOptional = Optional.ofNullable(user);
        userOptional.ifPresent(Users::add);
        return 0;
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

    @Override
    public int getCountUser() {
        return Users.size();
    }


    public static ContextList getInstance() {
        return Instance;
    }

}
