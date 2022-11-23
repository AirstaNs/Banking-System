package banking.DAO;

import banking.Card.Card;
import banking.client.User;

import java.sql.*;
import java.util.Optional;

public class ContextDataBase implements Context {
    private final String URL_SQLITE;
    private Connection connection;

    public ContextDataBase(String nameDB) {
        this.URL_SQLITE = "jdbc:sqlite:C:/sqlite/" + nameDB;
        try {
            this.connection = DriverManager.getConnection(URL_SQLITE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        boolean b = createTableCard();
    }

    private boolean dropTable() {
        String drop = """
                 DROP TABLE IF EXISTS card;
                """;
        try (var prepareStatement = connection.prepareStatement(drop)) {
            return prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean createTableCard() {
        String createCardTable = """
                CREATE TABLE IF NOT EXISTS card(
                id integer  PRIMARY KEY,
                number
                 text NOT NULL,
                pin text NOT NULL,
                balance integer DEFAULT 0);
                 """;
        try (var preparedStatement = connection.prepareStatement(createCardTable)) {
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
        try (var statement = connection.prepareStatement(insertUser)) {
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
        String containsUser = """
                SELECT *
                FROM card
                WHERE id = ?
                 """;
        try (var statement = connection.prepareStatement(containsUser)) {
            statement.setInt(1, user.getID()
                                    .getID());

            var us = statement.executeQuery();
            while (us.next()) {
                int id = us.getInt("id");
                String number = us.getString("number");
                String pin = us.getString("pin");
                int balance = us.getInt("balance");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean containsUser(Card cardUser) {
        String contains = """
                SELECT * FROM card
                WHERE number = ? AND pin = ?
                 """;
        try (var statement = connection.prepareStatement(contains)) {
            statement.setString(1, cardUser.getNumber());
            statement.setString(2, cardUser.getPIN()
                                           .toString());

            var us = statement.executeQuery();
            return us.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean containsUser(String number, String pin) {
        return false;
    }

    @Override
    public Optional<User> getUser(String number, String pin) {
        return getUser(new Card(number, pin));
    }

    @Override
    public Optional<User> getUser(Card card) {
        String selectUser = """
                SELECT * FROM card
                WHERE number = ? AND pin = ?;
                """;
        try (var statement = connection.prepareStatement(selectUser)) {
            statement.setString(1, card.getNumber());
            statement.setString(2, card.getPIN()
                                       .toString());

            var us = statement.executeQuery();
            if (us.next()) {
                int id = us.getInt("id");
                String number = us.getString("number" + "");
                String pin = us.getString("pin");
                int balance = us.getInt("balance");

                return Optional.of(new User(id, number, pin, balance));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean removeUser(User user) {
        String deleteUser = """
                DELETE FROM card
                WHERE id = ?;
                 """;
        try (var statement = connection.prepareStatement(deleteUser)) {

            statement.setInt(1, user.getID()
                                    .getID());
            var us = statement.executeQuery();

            return us.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getCountUser() {
        String selectUser = """
                SELECT COUNT(*) AS count_user
                FROM card;
                """;
        int count_user = -1;
        try (var statement = connection.prepareStatement(selectUser)) {
            var us = statement.executeQuery();
            if (us.next()) {
                count_user = us.getInt("count_user");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count_user;
    }
}
