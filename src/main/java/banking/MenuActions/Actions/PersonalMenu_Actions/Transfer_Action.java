package banking.MenuActions.Actions.PersonalMenu_Actions;

import banking.Card.AlgorithmLuna;
import banking.Card.Card;
import banking.DAO.Context;
import banking.MenuActions.Actions.Action;
import banking.MenuActions.Controller;
import banking.MenuActions.Recivers.PersonalMenu;
import banking.Printable;

import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Transfer_Action extends Action {
    private static final String title = "Do transfer";

    private final PersonalMenu personalMenu;

    public Transfer_Action(int numberItem, PersonalMenu personalMenu) {
        super(title, numberItem);
        this.personalMenu = personalMenu;
    }

    @Override
    public void execute(Controller controller) {
        Scanner scanner = new Scanner(System.in);
        var user = controller.getUser();
        var userCard = user.Card();
        var context = controller.getContext();


        String toNumberCard = getCardForTransfer(scanner);
        var validate = new ValidateTransfer();
        var isValidateCard = validate.checkValidateUser(userCard, toNumberCard, context);
        long amount;
        if (isValidateCard) {
            try {
                amount = getAmountTransfer(scanner);
            } catch (RuntimeException e) {
                Message.TRANSFER_ENTER_AMOUNT.printToConsole();
                return;
            }
            boolean isNotOverAmount = validate.isBalanceNotOverAmount(userCard, amount);

            if (isNotOverAmount) personalMenu.transfer(user, context, toNumberCard, amount);
        }

    }

    private String getCardForTransfer(Scanner scanner) {
        Message.TRANSFER_ENTER_CARD.printToConsole();
        String toNumberCard = scanner.next();
        return toNumberCard;
    }

    private long getAmountTransfer(Scanner scanner) {
        Message.TRANSFER_ENTER_AMOUNT.printToConsole();
        long amount = scanner.nextLong();
        return amount;
    }

    private static class ValidateTransfer {
        boolean isValid = true;

        public boolean checkValidateUser(Card userCard, String toNumberCard, Context context) {
            try {
                boolean isNotSame = this.isNotSameAccount(userCard, toNumberCard);
                boolean isFormattedLuna = this.isFormattedLuna(toNumberCard);
                boolean isExistUser = this.isUserExists(context, toNumberCard);

            } catch (NoSuchElementException noSuch) {
                Message.ERROR_TRANSFER_CARD_NOT_EXIST.printToConsole();
                return !isValid;
            } catch (FormatterClosedException format) {
                Message.ERROR_TRANSFER_CARD_NUMBER_NOT_LUNA.printToConsole();
                return !isValid;
            } catch (NumberFormatException number) {
                Message.ERROR_TRANSFER_TO_YOURSELF.printToConsole();
                return !isValid;
            }
            return isValid;
        }

        public boolean isNotSameAccount(Card fromCard, String toNumberCard) {
            boolean isNotSameAccount = true;
            if (fromCard.getNumber().equals(toNumberCard)) {
                throw new NumberFormatException();
            }
            return isNotSameAccount;
        }

        public boolean isFormattedLuna(String toNumberCard) {
            boolean isLuna = true;
            if (new AlgorithmLuna(toNumberCard).isFormattedLuna()) {
                return isLuna;
            }
            throw new FormatterClosedException();
        }

        public boolean isUserExists(Context context, String toNumberCard) {
            boolean isExists = true;
            if (context.containsUser(toNumberCard)) {
                return isExists;
            } else {
                throw new NoSuchElementException();
            }
        }

        public boolean isBalanceNotOverAmount(Card card, long amount) {
            boolean notOver = true;
            if (card.Balance().getBalance() > amount) {
                return notOver;
            } else {
                Message.ERROR_TRANSFER_NOT_MONEY.printToConsole();
                return !notOver;
            }
        }

    }

    private enum Message implements Printable {
        TRANSFER_ENTER_CARD("Transfer\n" + "Enter card number:"),
        TRANSFER_ENTER_AMOUNT("Enter how much money you want to transfer:"),
        ERROR_TRANSFER_NOT_MONEY("Not enough money!\n"),
        ERROR_TRANSFER_TO_YOURSELF("You can't transfer money to the same account!\n"),
        ERROR_TRANSFER_CARD_NUMBER_NOT_LUNA("Probably you made a mistake in the card number. Please try again!\n"),
        ERROR_TRANSFER_CARD_NOT_EXIST("Such a card does not exist."),
        ERROR_TRANSFER_AMOUNT("transfer amount must be at least zero!");
        private final String message;

        Message(String message) {
            this.message = message;
        }

        @Override
        public void printToConsole() {
            System.out.println(message);
        }
    }


}
