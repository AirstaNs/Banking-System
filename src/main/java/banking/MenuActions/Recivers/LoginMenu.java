package banking.MenuActions.Recivers;

import banking.MenuActions.Controller;
import banking.MenuActions.BankSystem;
import banking.MenuActions.Page;
import banking.Printable;
import banking.client.User;

import java.util.Optional;
import java.util.Scanner;

import static banking.MenuActions.Recivers.LoginMenu.Message.*;
/**
 * RECEIVER  in Command pattern
 * <br>
 * The initialization page is available without logging in.
 * A specific menu item and page, each method describes a specific action on the page.
 */

public class LoginMenu implements ShouldBeExit {
    /**
     * Needed to change the displayed page in the console
     */
    private Controller controller;

    public LoginMenu(Controller controller) {
        this.controller = controller;
    }

    /**
     * Account creation action. <br>
     * Creates a new user instance. <br>
     * Adds to Context - "database". <br>
     * Writes the user's card data to the console.
     */
    public void createAccount() {
        var user = new User();
        controller.getContext().addUser(user);
        user.printToConsole();
    }

    /**
     * The action of signing in to an account. <br>
     * Reads the login data from the console: Card Number and Pin. <br>
     * Takes from the context of the User. <br>
     * If the data is incorrect - DOES NOT redirect to the page of the Personal Account. <br>
     * if correct, redirects to the page of the Personal Account
     *
     * @param personalMenu
     */
    public void LogInAccount(PersonalMenu personalMenu) {
        var optionalUser = initialUserFromConsole();

        optionalUser.ifPresentOrElse((person) -> {
            personalMenu.setUser(person);
            SUCCESSFUL_LOGIN.printToConsole();
            controller.setPage(Page.personalPage(BankSystem.personalMenu));
        }, FAILED_LOGIN::printToConsole);
    }

    private Optional<User> initialUserFromConsole() {
        INPUT_CARD.printToConsole();
        Scanner scanner = new Scanner(System.in);
        String number = scanner.next();
        INPUT_PIN.printToConsole();
        String pin = scanner.next();
        return controller.getContext().getUser(number, pin);
    }

    public enum Message implements Printable {
        INPUT_CARD("Enter your card number:"),
        INPUT_PIN("Enter your PIN:"),
        SUCCESSFUL_LOGIN("You have successfully logged in!"),
        FAILED_LOGIN("Wrong card number or PIN!");
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