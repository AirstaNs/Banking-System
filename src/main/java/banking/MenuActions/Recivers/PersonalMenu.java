package banking.MenuActions.Recivers;

import banking.MenuActions.Controller;
import banking.MenuActions.BankSystem;
import banking.MenuActions.Page;
import banking.Printable;
import banking.client.User;

/**
 * RECEIVER  in Command pattern
 * <br>
 * The personal account page in the system is available after logging in.
 * A specific menu item and page, each method describes a specific action on the page.
 */
public class PersonalMenu implements ShouldBeExit {
    /**
     * Personal account of users, detection action for it.
     */
    private User user;
    /**
     * Needed to change the displayed page in the console
     */
    private final Controller controller;

    public PersonalMenu(Controller controller) {
        this.controller = controller;
    }

    /**
     * Set the user for this page.
     *
     * @param user Personal account of users, detection action for it.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Action to display the balance on the console for a specific user
     */
    public void balance() {
        System.out.println(user.Card().Balance());
    }

    public void addIncome() { //TODO

    }

    public void transfer() { //TODO


    }

    public void closeAccount() { //TODO

    }

    /**
     * Exit from the Personal Account. <br>
     * Removing the user from the page, Redirecting to the login page.
     */
    public void LogOut() {
        Message.LOG_OUT.printToConsole();
        user = null;
        controller.setPage(Page.welcomePage(BankSystem.loginMenu, BankSystem.personalMenu));
    }

    private enum Message implements Printable {
        LOG_OUT("You have successfully logged out!\n"),
        addIncome("addIncome"),
        transfer("transfer"),
        closeAccount("closeAccount");
        private String message;

        Message(String message) {
            this.message = message;
        }

        @Override
        public void printToConsole() {
            System.out.println(message);
        }
    }
}
