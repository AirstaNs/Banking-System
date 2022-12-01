package banking.MenuActions.Actions.PersonalMenu_Actions;

import banking.MenuActions.Actions.Action;
import banking.MenuActions.Controller;
import banking.MenuActions.Recivers.PersonalMenu;

public class AddIncome_Action extends Action {

    private static final String title = "Add income";

    private final PersonalMenu personalMenu;

    public AddIncome_Action(int numberItem, PersonalMenu personalMenu) {
        super(title, numberItem);
        this.personalMenu = personalMenu;
    }

    @Override
    public void execute(Controller controller) {
        personalMenu.addIncome();
    }
}
