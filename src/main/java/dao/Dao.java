package dao;

import java.sql.*;

public class Dao {
    protected final String login;
    protected final String password;
    protected final String database;
    protected Connection connection;
    protected String databaseURL = "jdbc:postgresql://ec2-54-75-246-118.eu-west-1.compute.amazonaws.com/";
    PreparedStatement statement;

    protected Dao(String login, String password, String database) {
        this.login = login;
        this.password = password;
        this.database = database;
    }

    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(databaseURL +
                            database,
                    login,
                    password);

            if (connection != null) {
                System.out.println("Connected");
            } else {
                System.out.println("Connection Failed");
            }
        } catch (
                Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void disconnect() {
        try {
            connection.close();
            System.out.println("Disconnected");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public ResultSet resultSet(String query) throws SQLException {
        connect();
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public String queryBuilder(String table, String column, int id) {
        return "select * from" + table + " where " + column + " like '%" + id + "%';";
    }

    public String queryBuilder(String table) {
        return "select * from public." + table + ";";
    }

    public boolean isUserDataCorrect(String login, String password) {
        connect();
        try {
            statement = connection.prepareStatement("SELECT LOGIN, PASSWORD FROM USERS WHERE LOGIN=? AND PASSWORD=?;");
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                final boolean isCorrect = rs.getString("LOGIN").equals(login) && rs.getString("PASSWORD").equals(password);
                disconnect();
                return isCorrect;
            }
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return false;
    }

    public String getUserType(String login) {
        int Id = getIdByLogin(login);
        connect();
        try {
            ResultSet rs = statement.executeQuery(String.format("SELECT USER.USER_ID,USER_TYPES.TYPE FROM USER" +
                    " INNER JOIN USER_TYPES ON  USER_TYPES.TYPE_ID = USER.TYPE_ID " +
                    "WHERE USER.USER_ID = %d;", Id));
            if (!rs.isClosed()) {
                String type = rs.getString("TYPE");
                disconnect();
                return type;
            }
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return null;
    }

    public int getIdByLogin(String login) {//TODO change to private when tested.
        connect();
        try {
            ResultSet rs = statement.executeQuery(String.format("SELECT ID FROM USERS WHERE LOGIN='%s'", login));

            if(!rs.isClosed()) {
                int user_id = rs.getInt("ID");
                rs.close();
                disconnect();
                return user_id;
            }
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return 0;
    }
}
