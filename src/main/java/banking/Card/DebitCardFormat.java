package banking.Card;

import banking.Formatters.FormatterString;

interface DebitCardFormat extends FormatterString {
    String BIN = "400000";
    int LENGTH_CARD_NUMBER = 16;

    //    @Override
    //    default String toFormat(String string) {
    //        return null;
    //    }
}
//ShouldBeGenerateCardNumber