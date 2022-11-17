package banking.TestCommand.Recivers;

import banking.TestCommand.HomeAutomationDemo;

public interface ShouldBeExit {
     default void exit(){
         System.out.println("Bye!");
         HomeAutomationDemo.isWork = false; //что-то с этим сделать
     }
}
