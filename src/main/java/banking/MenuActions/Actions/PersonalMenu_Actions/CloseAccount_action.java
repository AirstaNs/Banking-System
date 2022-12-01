package banking.MenuActions.Actions.PersonalMenu_Actions;

import banking.DAO.Context;
import banking.MenuActions.Actions.Action;
import banking.MenuActions.Controller;
import banking.MenuActions.Recivers.PersonalMenu;

public class CloseAccount_action extends Action {
    private static final String title = "Close account";

    private final PersonalMenu personalMenu;

    public CloseAccount_action(int numberItem, PersonalMenu personalMenu) {
        super(title, numberItem);
        this.personalMenu = personalMenu;
    }

    @Override
    public void execute(Controller controller) {
        Context context = controller.getContext();
        boolean isRemove = context.removeUser(controller.getUser());
        personalMenu.closeAccount(isRemove);
    }
}
