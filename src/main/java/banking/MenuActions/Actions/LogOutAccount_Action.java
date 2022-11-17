package banking.MenuActions.Actions;

import banking.MenuActions.Recivers.PersonalMenu;

public class LogOutAccount_Action extends Action {

    private static final String title = "Log out";
    private final PersonalMenu personalMenu;


    public LogOutAccount_Action(int numberItem, PersonalMenu personalMenu) {
        super(title, numberItem);
        this.personalMenu = personalMenu;
    }


    @Override
    public void execute() {
        personalMenu.LogOut();
    }
}
