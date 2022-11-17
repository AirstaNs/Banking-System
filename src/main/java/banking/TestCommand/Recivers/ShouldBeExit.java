package banking.TestCommand.Recivers;

import banking.TestCommand.BankSystem;

public interface ShouldBeExit {
     default void exit(){
         System.out.println("Bye!");
         BankSystem.isWork = false; //что-то с этим сделать
     }
}
