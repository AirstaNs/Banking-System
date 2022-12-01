import banking.DAO.ContextDataBase;
import banking.MenuActions.BankSystem;
import banking.client.User;

public class Main {
    /**
     * the database name is extracted from the program arguments. <br>
     * Example = -fileName test.db
     */
    public static void main(String[] args) {
        BankSystem system = new BankSystem();
        String nameDB = args[1];
        system.start(nameDB);
    }
}