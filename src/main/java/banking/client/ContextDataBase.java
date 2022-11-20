package banking.client;

import banking.Card.Card;

import java.sql.*;
import java.util.Optional;

public class ContextDataBase implements Context {
    private final String URL_SQLITE;
    private Connection connection;

    public ContextDataBase(String nameDB) {
        URL_SQLITE = "jdbc:sqlite:C:/sqlite/" + nameDB;
        try {
            connection = DriverManager.getConnection(URL_SQLITE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean dropTable() {
        try {
            var x = connection.prepareStatement("""
                     DROP TABLE IF EXISTS card;
                    """);
            return x.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean createTableCard() {
        try {
            var x = connection.prepareStatement("""
                    CREATE TABLE IF NOT EXISTS card(
                    id integer  PRIMARY KEY,
                    pin text NOT NULL,
                    number text NOT NULL,
                    balance integer DEFAULT 0);
                     """);
            return x.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean createTableUser() {
        try {
            var x = connection.prepareStatement("""
                    CREATE TABLE IF NOT EXISTS USER(
                    id integer  PRIMARY KEY,
                    pin text NOT NULL,
                    number text NOT NULL,
                    balance integer DEFAULT 0);
                     """);
            return x.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int addUser(User user) {
        try {
            var statement = connection.prepareStatement("""
                    INSERT INTO card
                    (pin,number)
                    VALUES(?,?);
                    CREATE TABLE IF NOT EXISTS card(
                    id integer  PRIMARY KEY,
                    pin varchar(30) NOT NULL,
                    balance integer DEFAULT 0);
                     """);
            statement.setString(1, user.getCard()
                                       .getPIN()
                                       .toString());
            statement.setString(2, user.getCard()
                                       .getNumber());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean containsUser(User user) {
        return false;
    }

    @Override
    public boolean containsUser(Card cardUser) {
        return false;
    }

    @Override
    public boolean containsUser(String number, String pin) {
        return false;
    }

    @Override
    public Optional<User> getUser(String number, String pin) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUser(Card card) {
        return Optional.empty();
    }

    @Override
    public boolean removeUser(User user) {
        return false;
    }

    public static void main(String[] args) {
        ContextDataBase contextDataBase = new ContextDataBase("test.db");
        System.out.println(contextDataBase.addUser(new User()));
    }
}
