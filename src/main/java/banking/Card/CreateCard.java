package banking.Card;

import banking.Formatted.Formatted;

public class CreateCard implements ShouldBeGenerateCardNumber {
    private Formatted format;

    public CreateCard(Formatted format) {
        this.format = format;
    }

    @Override
    public String generated(int UserId) {
        final int ControlSum = 9; //TODO БИЛДЕР
       String accountIdentifier = format.castToFormat(UserId);
      return String.format("%s%s%d",BIN,accountIdentifier,ControlSum);
    }
}
