package banking.DAO;

import banking.Card.Card;
import banking.client.UID;
import banking.client.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Optional;

public class ContextDataBase implements Context {
    private final String URL_SQLITE;
    private Connection connection;

    public ContextDataBase(String nameDB) {
        String path = "D:/BankingSystemDB/";
        this.URL_SQLITE = "jdbc:sqlite:" + path + nameDB;
        createDataBase(path, nameDB);
        try {
            this.connection = DriverManager.getConnection(URL_SQLITE);
            UID.setCount(this.getCountUser());
        } catch (SQLException e) {e.printStackTrace();}
    }

    public void init() {
        boolean b = createTableCard();
    }

    private void createDataBase(String path, String nameDB) {
        try {
            var tempF = Path.of(path + nameDB);
            if (!Files.exists(tempF)) {
                Files.createDirectories(Path.of(path));
                Files.createFile(tempF);
            }
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    private boolean dropTable() {
        String drop = "DROP TABLE IF EXISTS card;" ;
        boolean isDrop = false;
        try (var prepareStatement = connection.prepareStatement(drop)) {
            isDrop = prepareStatement.execute();
        } catch (SQLException e) {e.printStackTrace();}
        return isDrop;
    }

    private boolean createTableCard() {
        String createCardTable = """
                CREATE TABLE IF NOT EXISTS card(
                id integer  PRIMARY KEY,
                number text NOT NULL,
                pin text NOT NULL,
                balance integer DEFAULT 0);
                 """;
        boolean isCreate = false;
        try (var preparedStatement = connection.prepareStatement(createCardTable)) {
            isCreate = preparedStatement.execute();
        } catch (SQLException e) {e.printStackTrace();}
        return isCreate;
    }

    private boolean createTableUser() {
        String createUserTable = """
                CREATE TABLE IF NOT EXISTS USER(
                id integer  PRIMARY KEY,
                pin text NOT NULL,
                number text NOT NULL,
                balance integer DEFAULT 0);
                 """;
        try (var preparedStatement = connection.prepareStatement(createUserTable)) {

            return preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int addUser(User user) {
        String insertUser = """
                INSERT INTO card
                (pin,number)
                VALUES(?,?);
                 """;
        int updateRows = -1;
        try (var statement = connection.prepareStatement(insertUser)) {
            statement.setString(1, user.Card().PIN().toString());
            statement.setString(2, user.Card().getNumber());

            updateRows = statement.executeUpdate();
        } catch (SQLException e) {e.printStackTrace();}
        return updateRows;
    }

    @Override
    public boolean containsUser(User user) {
        String containsUser = "SELECT * FROM card WHERE id = ?" ;
        boolean existsUser = false;
        try (var statement = connection.prepareStatement(containsUser)) {
            statement.setInt(1, user.ID().getID());

            var userDb = statement.executeQuery();
            existsUser = userDb.next();

        } catch (SQLException e) {e.printStackTrace();}
        return existsUser;
    }

    @Override
    public boolean containsUser(Card cardUser) {
        String contains = """
                SELECT * FROM card
                WHERE number = ? AND pin = ?
                 """;
        boolean existsUser = false;
        try (var statement = connection.prepareStatement(contains)) {
            statement.setString(1, cardUser.getNumber());
            statement.setString(2, cardUser.PIN().toString());
            var user = statement.executeQuery();
            existsUser = user.next();
        } catch (SQLException e) {e.printStackTrace();}

        return existsUser;
    }

    @Override
    public boolean containsUser(String number, String pin) {
        return false;
    }

    @Override
    public boolean containsUser(String card) {
        String contains = "SELECT * FROM card WHERE number = ?";
        boolean existsUser = false;
        try (var statement = connection.prepareStatement(contains)) {
            statement.setString(1, card);
            var user = statement.executeQuery();
            existsUser = user.next();
        } catch (SQLException e) {e.printStackTrace();}

        return existsUser;
    }

    @Override
    public Optional<User> getUser(String number, String pin) {
        return getUser(new Card(number, pin));
    }

    @Override
    public Optional<User> getUser(Card card) {
        String selectUser = """ 
                SELECT * FROM card
                WHERE number = ? AND pin = ?
                """;
        Optional<User> optionalUser = Optional.empty();
        try (var statement = connection.prepareStatement(selectUser)) {
            statement.setString(1, card.getNumber());
            statement.setString(2, card.PIN().toString());

            var user = statement.executeQuery();
            if (user.next()) {
                int id = user.getInt("id");
                String number = user.getString("number" + "");
                String pin = user.getString("pin");
                long balance = user.getLong("balance");

                optionalUser = Optional.of(new User(id, number, pin, balance));
            }
        } catch (SQLException e) {e.printStackTrace();}
        return optionalUser;
    }

    @Override
    public boolean removeUser(User user) {
        String deleteUser = "DELETE FROM card WHERE id = ?" ;
        boolean isRemove = false;
        try (var statement = connection.prepareStatement(deleteUser)) {

            statement.setInt(1, user.ID().getID());
            var removeUser = statement.executeUpdate();
            isRemove = removeUser > 0;

        } catch (SQLException e) {e.printStackTrace();}
        return isRemove;
    }

    public int getCountUser() {
        String selectUser = "SELECT COUNT(*) AS count_user FROM card" ;
        int count_user = -1;
        try (var statement = connection.prepareStatement(selectUser)) {
            var userCount = statement.executeQuery();
            if (userCount.next()) {
                count_user = userCount.getInt("count_user");
            }
        } catch (SQLException e) {e.printStackTrace();}
        return count_user;
    }

    @Override
    public int updateBalance(User user) {
        String update = """
                UPDATE  card
                SET balance = ?
                WHERE id = ?
                 """;
        int updateRows = -1;
        try (var statement = connection.prepareStatement(update)) {
            statement.setLong(1, user.Card().Balance().getBalance());
            statement.setInt(2, user.ID().getID());

            updateRows = statement.executeUpdate();
        } catch (SQLException e) {e.printStackTrace();}
        return updateRows;
    }

    @Override
    public int transfer(User user, String number, long amount) {
        String fromUser = """
                UPDATE  card
                SET balance = balance-?
                WHERE id = ?
                 """;
        String toCard = """
                UPDATE card
                SET balance = balance+?
                WHERE number = ?;
                """;
        int updateRows = -1;
        try (var fromStatement = connection.prepareStatement(fromUser); var toStatement = connection.prepareStatement(toCard)) {
            connection.setAutoCommit(false);
            user.Card().Balance().withdraw(amount);
            fromStatement.setLong(1, amount);
            fromStatement.setInt(2, user.ID().getID());
            updateRows = fromStatement.executeUpdate();


            toStatement.setLong(1, amount);
            toStatement.setString(2, number);
            toStatement.executeUpdate();

            connection.setAutoCommit(true);
        } catch (SQLException e) {
            user.Card().Balance().deposit(amount);
            try {
                connection.rollback();
            } catch (SQLException exception) {exception.printStackTrace();}
        }
        return updateRows;
    }
}
