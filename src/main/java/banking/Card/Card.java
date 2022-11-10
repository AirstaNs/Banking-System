package banking.Card;

import banking.Formatters.Formatter;

import static banking.Card.ShouldBeGenerateCardNumber.BIN;

public class Card   {
    private final String number;
    private final Formatter format;

    private final String Pin;

    public Card(Formatter formatCard, Formatter formatPin, int UserId) {
        this.format = formatCard;
        this.number = create(UserId);
        this.Pin = new Pin(formatPin).getPin();
    }

   private   String create(int UserId) {
        final int ControlSum = 9; //TODO БИЛДЕР
       String accountIdentifier = format.castToFormat(UserId);

       return String.format("%s%s%d",BIN,accountIdentifier,ControlSum);
    }

    public String getNumber() {
        return number;
    }

}
