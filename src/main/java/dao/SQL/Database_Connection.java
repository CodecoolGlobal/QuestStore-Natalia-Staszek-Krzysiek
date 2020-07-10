package dao.SQL;

import java.sql.*;
import java.util.List;

public class Database_Connection {

    protected final String login;
    protected final String password;
    protected final String database;
    protected Connection connection;
    protected String databaseURL;

    public Database_Connection(){
        this.login = "oocsahevsxdcvi";
        this.password = "69f61ddc12ee0f09faf874f3da714d72f73dc24361245701e74f662a87272146";
        this.database = "dapdcgql52bu1b";
        this.databaseURL = "jdbc:postgresql://ec2-54-75-225-52.eu-west-1.compute.amazonaws.com/";
    }

    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-75-225-52.eu-west-1.compute.amazonaws.com:5432/dapdcgql52bu1b","oocsahevsxdcvi","69f61ddc12ee0f09faf874f3da714d72f73dc24361245701e74f662a87272146");
            this.connection.setAutoCommit(true);
            if (connection != null) {
                System.out.println("Connected");
            } else {
                System.out.println("Connection Failed");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
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

    protected ResultSet query(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }

    public boolean update(PreparedStatement statement) {

        try {
            connect();
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            disconnect();
        }
        return false;
    }

    protected PreparedStatement getPreparedStatement(String sqlStatement) throws SQLException {
        connect();
        return connection.prepareStatement(sqlStatement);
    }

    public PreparedStatement getPreparedStatementBy(List args, String sqlStatement) {
        PreparedStatement statement = null;
        try {
            statement = getPreparedStatement(sqlStatement);
            if (!args.isEmpty()) {
                int index = 1;
                for (Object argument : args) {
                    statement.setObject(index, argument);
                    index++;
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return statement;
    }
}

