package banking.Card;

import java.util.Objects;

/**
 * The class describing the specific balance of the transferred {@link Card}. <br>
 * When created, the balance is set by default = 0L. <br>
 * In the {@link Card} class, the balance for the strong link is also passed.
 */
public class Balance {
    /**
     * A variable that stores funds on the client's {@link Card}.
     */
    private Long balance;
    /**
     * The {@link Card} that stores this balance.
     */
    private final Card card;

    public Balance(Card card) {
        balance = 0L;
        this.card = card;
    }

    /**
     * Method to increase the balance on the card
     *
     * @param amount the value by which to increase the balance.
     */
    protected void deposit(Long amount) {
        this.balance += amount;
    }

    /**
     * Method to reduce the balance on the card.
     *
     * @param amount the value by which to reduce the balance.
     */
    protected void withdraw(Long amount) {
        this.balance -= amount;
    }

    protected long getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Balance)) return false;
        if (this.hashCode() != o.hashCode()) return false;
        Balance balance = (Balance) o;

        return card.equals(balance.card) && this.balance.equals(balance.balance);
    }

    @Override
    public int hashCode() {
        return card != null ? Objects.hash(card, balance) : 0;
    }

    @Override
    public String toString() {
        return String.format("Balance: %d%n", balance);
    }
}
