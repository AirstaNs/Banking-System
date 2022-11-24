package banking.MenuActions.Actions.PersonalMenu_Actions;

import banking.MenuActions.Actions.Action;
import banking.MenuActions.Recivers.PersonalMenu;

public class Transfer_Action extends Action {
    private static final String title = "Do transfer";

    private final PersonalMenu personalMenu;

    public Transfer_Action(int numberItem, PersonalMenu personalMenu) {
        super(title, numberItem);
        this.personalMenu = personalMenu;
    }

    @Override
    public void execute() {
        personalMenu.transfer();
    }
}
