package banking.MenuActions.Actions;

import banking.MenuActions.Recivers.LoginMenu;

public class CreateAccount_Action extends Action {
    private static final String title = "Create an account";

    private final LoginMenu loginMenu;

    public CreateAccount_Action(int numberItem, LoginMenu loginMenu) {
        super(title, numberItem);
        this.loginMenu = loginMenu;
    }

    @Override
    public void execute() {
        loginMenu.createAccount();
    }
}
