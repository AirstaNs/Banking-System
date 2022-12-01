package banking.MenuActions.Actions.LoginMenu_Actions;

import banking.MenuActions.Actions.Action;
import banking.MenuActions.Controller;
import banking.MenuActions.Recivers.LoginMenu;

public class CreateAccount_Action extends Action {
    private static final String title = "Create an account";

    private final LoginMenu loginMenu;

    public CreateAccount_Action(int numberItem, LoginMenu loginMenu) {
        super(title, numberItem);
        this.loginMenu = loginMenu;
    }


    @Override
    public void execute(Controller controller) {
        loginMenu.createAccount(controller);
    }
}
