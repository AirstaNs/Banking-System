package banking.MenuActions;


import banking.MenuActions.Recivers.LoginMenu;
import banking.MenuActions.Recivers.PersonalMenu;
import banking.DAO.ContextDataBase;
import banking.client.UID;


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
    private static Controller controller;
    /**
     * The initialization page is available without logging in. A specific menu item and page
     */
    public static LoginMenu loginMenu;

    /**
     * The personal account page in the system is available after logging in.
     */
    public static PersonalMenu personalMenu;

    /**
     * Launching the banking system, exposes the welcome page - loginMenu. <br>
     * Works as long as {@link BankSystem#isWork}  != false.
     */
    public void start(String nameDB) {
        initSystem(nameDB);

        controller.setPage(Page.welcomePage(loginMenu));


        while (isWork) {
            menu(controller);
        }
    }

    /**
     * Initializing the Context, Controller and Menu Pages. <br>
     * Setting the UID counter from the database.
     *
     * @param nameDB - the name of the database to be accessed
     */
    private void initSystem(String nameDB) {
        var context = new ContextDataBase(nameDB);
        context.init();
        controller = new Controller(context);
        loginMenu = new LoginMenu();
        personalMenu = new PersonalMenu(controller);
        UID.setCount(context.getCountUser());
    }

    /**
     * Prints menu items from the controller, sets actions, from the number read from the console
     *
     * @param controller {@link BankSystem#controller}
     */
    private void menu(Controller controller) {
        controller.printPage();
        try {
            controller.executeCommand(new Scanner(java.lang.System.in).nextInt());
        } catch (RuntimeException e) {
            System.out.println("Wrong action\n");
        }
    }

}