package banking.Card;

import java.util.Objects;

public class Balance {
    private  Long value;
    private final Card card;
    public Balance(Card card){
        value = 0L;
        this.card = card;
    }

    public void increase(Long value){
        this.value+=value;
    }

    public void decrease(Long value){
        this.value-=value;
    }
    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Balance)) return false;
        if (this.hashCode()!=o.hashCode()) return false;
        Balance balance = (Balance) o;

        return card.equals(balance.card) && value.equals(balance.value);
    }

    @Override
    public int hashCode() {
        return card != null ?  Objects.hash(card,value) : 0;
    }
}
