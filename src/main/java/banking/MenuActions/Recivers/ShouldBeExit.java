package banking.MenuActions.Recivers;

import banking.MenuActions.BankSystem;

public interface ShouldBeExit {
     default void exit(){
         System.out.println("Bye!");
         BankSystem.isWork = false; //что-то с этим сделать
     }
}
