package UserInterface.Strategy.Concrete;

import UserInterface.Commands.Command;
import UserInterface.Commands.login.ExitCommand;
import UserInterface.Commands.login.LoginAccountCommand;
import UserInterface.Commands.login.createAccountCommand;
import UserInterface.Strategy.Strategy;

public class loginStrategy implements Strategy {
    @Override
    public Command execute(int operation) {
       return switch (operation) {
            case 1 -> new LoginAccountCommand();
            case 2 -> new createAccountCommand();
            case 0 -> new ExitCommand();
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };
    }
}
