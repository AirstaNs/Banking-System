package UserInterface.Commands.PersonalAcc;

import UserInterface.Commands.Command;

public class BalanceCommand implements Command {
    PersonalAccount personalAccount;

    public BalanceCommand(PersonalAccount personalAccount) {
        this.personalAccount = personalAccount;
    }

    @Override
    public void execute() {
        personalAccount.Balance();
    }
}
