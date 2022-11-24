package banking.DAO;

import banking.Card.Card;
import banking.client.User;

import java.util.Optional;

/**
 * An interface to provide multiple repository implementations for storing data about a system user.
 */
public interface Context {

    int addUser(User user);

    boolean removeUser(User user);

    boolean containsUser(User user);

    boolean containsUser(Card cardUser);

    boolean containsUser(String number, String pin);


    Optional<User> getUser(String number, String pin);

    Optional<User> getUser(Card card);

    int getCountUser();

    int updateBalance(User user);
}
