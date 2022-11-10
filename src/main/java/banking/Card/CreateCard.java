package banking.Card;

import banking.Formatted.Formatted;

public class CreateCard implements ShouldBeGenerateCardNumber {
    private Formatted format;

    public CreateCard(Formatted format) {
        this.format = format;
    }

    @Override
    public String generated(int UserId) {
        return null;
    }
}
