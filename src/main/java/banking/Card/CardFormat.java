package banking.Card;

 interface CardFormat {
     String BIN = "400000";
     int LENGTH_CARD_NUMBER = 16;

     String generated(int UserId);
 }
//ShouldBeGenerateCardNumber