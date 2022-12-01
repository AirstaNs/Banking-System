package banking.MenuActions;

import banking.DAO.Context;
import banking.MenuActions.Actions.Action;
import banking.client.User;

import java.util.List;

/**
 * INVOKER  in Command pattern. <br>
 * Execute commands for the given menu item number. <br>
 * Displays all items on the page.
 */

public class Controller {

    /**
     * Page with menu actions.
     */
    private List<Action> page;

    private User user;
    private Context context;

    public Controller(Context context) {
        this.context = context;
    }

    /**
     * Set the user for this controller.
     * @param user Personal account of users, detection action for it.
     */
    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    /**
     * Sets the page that will then be printed to the console.
     * @param actions List with possible actions.
     */
    public void setPage(List<Action> actions) {
        this.page = actions;
    }

    /**
     * The method performs an action on the given page number.
     *
     * @param number the number of the menu item to be set.
     */
    public void executeCommand(int number) {
        if (number >= page.size()) throw new RuntimeException();
        page.get(number).execute(this);
    }

    /**
     * Output all menu items to the console. <br>
     * If there is no Page, it throws an NullPointerException.
     */
    public void printPage() {
        if (page == null) {
            throw new NullPointerException("No Actions");
        } else {
            page.stream()
                .map(Action::toString)
                .forEach(System.out::println);
        }
    }

    public Context getContext() {
        return context;
    }
}
