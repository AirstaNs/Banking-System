package banking.client;

import banking.Card.Card;

import java.util.Optional;

public interface Context {

    public int addUser(User user);

    public boolean containsUser(User user);

    public boolean containsUser(Card cardUser);

    public boolean containsUser(String number, String pin);


    public Optional<User> getUser(String number, String pin);

    public Optional<User> getUser(Card card);

    public boolean removeUser(User user);

}
