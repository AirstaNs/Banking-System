package banking.MenuActions.Actions;

import banking.MenuActions.Recivers.LoginMenu;
import banking.MenuActions.Recivers.PersonalMenu;

public class LogInAccount_Action extends Action {
    private static final String title = "Log into account";
    private final LoginMenu loginMenu;

    private final  PersonalMenu personalMenu;

    public LogInAccount_Action(int numberItem, LoginMenu loginMenu, PersonalMenu personalMenu) {
        super(title, numberItem);
        this.loginMenu = loginMenu;
        this.personalMenu = personalMenu;
    }


    @Override
    public void execute() {
        loginMenu.LogInAccount(personalMenu);
    }
}
