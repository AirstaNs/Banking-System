package banking.Card;

public interface ShouldBeGenerateCardNumber {
    String BIN = "400000";
    int lengthCARD = 16 - BIN.length();

    String generated(int UserId);
}
