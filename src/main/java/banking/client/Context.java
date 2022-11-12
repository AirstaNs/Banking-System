package banking.client;

import banking.Card.Card;

import java.util.ArrayList;
import java.util.Optional;

public class Context {
    private final ArrayList<User> Users = new ArrayList<>();

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

    public boolean removeUser(User user) {
        return Users.remove(user);
    }

    public static void main(String[] args) {
        var cont = new Context();

        var USER = new User();
        cont.addUser(USER);
    }
}
