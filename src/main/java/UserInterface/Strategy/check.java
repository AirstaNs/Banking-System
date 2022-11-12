package UserInterface.Strategy;

import UserInterface.Strategy.Concrete.loginStrategy;

import java.util.Scanner;

public class check {
    public static void main(String[] args) {
        Context context = new Context();
        context.setStrategy(new loginStrategy());
        context.executeStrategy(new Scanner(System.in).nextInt()).execute();
    }
}
