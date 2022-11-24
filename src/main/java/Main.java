import banking.DAO.ContextDataBase;
import banking.MenuActions.BankSystem;
import banking.client.User;

public class Main {
    /**
     * the database name is extracted from the program arguments. <br>
     * Example = -fileName test.db
     */
    public static void main(String[] args) {
       var  cont = new ContextDataBase(args[1]);
        for (int i = 0; i <1000 ; i++) {
            cont.addUser(new User());
        }
//        BankSystem system = new BankSystem();
//        String nameDB = args[1];
//        system.start(nameDB);
    }
}