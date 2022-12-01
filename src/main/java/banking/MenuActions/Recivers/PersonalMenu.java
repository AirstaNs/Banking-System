package banking.MenuActions.Recivers;


import banking.DAO.Context;
import banking.MenuActions.Controller;
import banking.MenuActions.BankSystem;
import banking.MenuActions.Page;
import banking.Printable;
import banking.client.User;

import java.util.Scanner;

/**
 * RECEIVER  in Command pattern
 * <br>
 * The personal account page in the system is available after logging in.
 * A specific menu item and page, each method describes a specific action on the page.
 */
public class PersonalMenu implements ShouldBeExit {
    /**
     * Needed to change the displayed page in the console
     */
    private final Controller controller;

    public PersonalMenu(Controller controller) {
        this.controller = controller;
    }


    /**
     * Action to display the balance on the console for a specific user
     */
    public void balance() {
        controller.getUser().Card().Balance().printToConsole();
    }

    public void addIncome() { //TODO
        Message.ADD_INCOME_INPUT.printToConsole();
        try {
            long amount = new Scanner(System.in).nextLong();
            User user = controller.getUser();

            user.Card().Balance().deposit(amount);
            controller.getContext().updateBalance(user);

            Message.ADD_INCOME_SUCCESSFULLY.printToConsole();
        } catch (RuntimeException e) {
            Message.ERROR_ADD_INCOME_FAILED.printToConsole();
        }
    }

    public void transfer(User user, Context context, String toNumberCard, long amount) { //TOD
        context.transfer(user, toNumberCard, amount);
    }

    public void closeAccount(boolean isRemove) { //TODO
        if (isRemove) {
            Message.CLOSE_ACCOUNT.printToConsole();
            controller.setUser(null);
            controller.setPage(Page.welcomePage(BankSystem.loginMenu));
        } else {
            throw new RuntimeException("Аккаунт не закрылся");
        }
    }

    /**
     * Exit from the Personal Account. <br>
     * Removing the user from the page, Redirecting to the login page.
     */
    public void LogOut() {
        Message.LOG_OUT.printToConsole();
        controller.setUser(null);
        controller.setPage(Page.welcomePage(BankSystem.loginMenu));
    }

    private enum Message implements Printable {
        LOG_OUT("You have successfully logged out!\n"),
        ADD_INCOME_INPUT("Enter income:"),
        ADD_INCOME_SUCCESSFULLY("Income was added!\n"),
        ERROR_ADD_INCOME_FAILED("Amount less than zero\n"),
        CLOSE_ACCOUNT("The account has been closed!\n");
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
