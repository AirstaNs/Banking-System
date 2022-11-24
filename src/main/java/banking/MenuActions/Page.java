package banking.MenuActions;

import banking.MenuActions.Actions.*;
import banking.MenuActions.Actions.LoginMenu_Actions.CreateAccount_Action;
import banking.MenuActions.Actions.LoginMenu_Actions.LogInAccount_Action;
import banking.MenuActions.Actions.PersonalMenu_Actions.*;
import banking.MenuActions.Recivers.LoginMenu;
import banking.MenuActions.Recivers.PersonalMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that provides pages for menus in the console.
 */
public class Page {
    /**
     * @param loginMenu    {@link LoginMenu}
     * @param personalMenu {@link PersonalMenu}
     * @return Returns "Page" - a list of home page menu actions.
     */
    public static List<Action> welcomePage(LoginMenu loginMenu, PersonalMenu personalMenu) {
        return new ArrayList<>() {{
            add(new Exit_Action(this.size(), loginMenu));
            add(new CreateAccount_Action(this.size(), loginMenu));
            add(new LogInAccount_Action(this.size(), loginMenu, personalMenu));
        }};
    }

    /**
     * @param personalMenu {@link PersonalMenu}
     * @return Returns "Page" - a list of Personal account page menu actions.
     */
    public static List<Action> personalPage(PersonalMenu personalMenu) {
        return new ArrayList<>() {{
            add(new Exit_Action(this.size(), personalMenu));
            add(new Balance_Action(this.size(), personalMenu));
            add(new AddIncome_Action(this.size(), personalMenu));
            add(new Transfer_Action(this.size(), personalMenu));
            add(new CloseAccount_action(this.size(), personalMenu));
            add(new LogOutAccount_Action(this.size(), personalMenu));
        }};
    }
}
