package banking.MenuActions.Actions;


import banking.MenuActions.Recivers.PersonalMenu;

public class Balance_Action extends Action {

    private static final String title = "Balance";

    private final PersonalMenu personalMenu;

    public Balance_Action(int numberItem, PersonalMenu personalMenu) {
        super(title, numberItem);
        this.personalMenu = personalMenu;
    }

    @Override
    public void execute() {
        personalMenu.balance();
    }
}
