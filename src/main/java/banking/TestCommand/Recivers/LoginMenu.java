package banking.TestCommand.Recivers;

import banking.TestCommand.Controller;
import banking.TestCommand.HomeAutomationDemo;
import banking.TestCommand.Page;
import banking.client.Context;
import banking.client.User;

import java.util.Scanner;

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
        Context.getInstance().addUser(user);
        System.out.println(user);
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
        System.out.println("Enter your card number:");
        Scanner scanner = new Scanner(System.in);
        var number = scanner.next();
        System.out.println("Enter your PIN:");
        var pin = scanner.next();
        var user = Context.getInstance().getUser(number, pin);

        // TODO возможно связано с Context, вставлять брать из Context
        user.ifPresentOrElse((person) -> {
            personalMenu.setUser(person);
            System.out.println("You have successfully logged in!");
            controller.setPage(Page.personalPage(HomeAutomationDemo.personalMenu));
        }, () -> System.out.println("Wrong card number or PIN!"));
    }
}