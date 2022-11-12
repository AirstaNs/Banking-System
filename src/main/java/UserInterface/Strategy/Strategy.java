package UserInterface.Strategy;

import UserInterface.Commands.Command;

public interface Strategy {
    Command execute(int operation);
}
