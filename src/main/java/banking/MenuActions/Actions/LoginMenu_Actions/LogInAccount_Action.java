package banking.MenuActions.Actions.LoginMenu_Actions;

import banking.MenuActions.Actions.Action;
import banking.MenuActions.Controller;
import banking.MenuActions.Recivers.LoginMenu;
import banking.MenuActions.Recivers.PersonalMenu;

public class LogInAccount_Action extends Action {
    private static final String title = "Log into account";
    private final LoginMenu loginMenu;


    public LogInAccount_Action(int numberItem, LoginMenu loginMenu) {
        super(title, numberItem);
        this.loginMenu = loginMenu;

    }


    @Override
    public void execute(Controller controller) {
        loginMenu.LogInAccount();
    }
}
