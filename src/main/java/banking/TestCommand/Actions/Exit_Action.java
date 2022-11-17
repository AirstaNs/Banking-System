package banking.TestCommand.Actions;

import banking.TestCommand.Recivers.ShouldBeExit;

public class Exit_Action extends Action {
    private static final String title = "Exit";

    private final ShouldBeExit menu;

    public Exit_Action(int numberItem, ShouldBeExit menu) {
        super(title, numberItem);
        this.menu = menu;
    }


    @Override
    public void execute() {
        menu.exit();
    }
}