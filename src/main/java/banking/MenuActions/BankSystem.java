package banking.MenuActions;


import banking.MenuActions.Recivers.LoginMenu;
import banking.MenuActions.Recivers.PersonalMenu;


import java.util.Scanner;

/**
 * CLIENT in Command pattern
 * A class that simulates the operation of the banking system. <br>
 * Where pages with menu items inherited from Action are displayed. <br>
 * Works until the user selects the "Exit" menu item. <br>
 */
public class BankSystem {
    /**
     * The flag denoting the operation of the system, if false - the program is terminated.
     */
    public static boolean isWork = true;

    /**
     * Displays all items on the page.
     */
    public static Controller controller = new Controller();
    /**
     * The initialization page is available without logging in. A specific menu item and page
     */
    public static final LoginMenu loginMenu = new LoginMenu(controller);

    /**
     * The personal account page in the system is available after logging in.
     */
    public static PersonalMenu personalMenu = new PersonalMenu(controller);

    /**
     * Launching the banking system, exposes the welcome page - loginMenu. <br>
     * Works as long as {@link BankSystem#isWork}  != false.
     */
    public void start() {

        controller.setPage(Page.welcomePage(loginMenu, personalMenu));

        while (isWork) {
            menu(controller);
        }
    }

    /**
     * Prints menu items from the controller, sets actions, from the number read from the console
     *
     * @param controller {@link BankSystem#controller}
     */
    private void menu(Controller controller) {
        controller.printPage();
        controller.executeCommand(new Scanner(java.lang.System.in).nextInt());
    }
}