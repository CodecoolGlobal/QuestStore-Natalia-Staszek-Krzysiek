package data;

import java.sql.*;
import java.util.List;

public class Database_Connection {

    protected final String login;
    protected final String password;
    protected final String database;
    protected Connection connection;
    protected String databaseURL;

    public Database_Connection(){
        this.login = "fbznochzdwosyl";
        this.password = "95a8e2f8c07cf64b80500a788f87ec46f1d2eaf6b2b542034f25f4e0311b0e2f";
        this.database = "d3nuc8s3988iho";
        this.databaseURL = "jdbc:postgresql://ec2-54-75-246-118.eu-west-1.compute.amazonaws.com/";
    }

    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-75-246-118.eu-west-1.compute.amazonaws.com:5432/d3nuc8s3988iho","fbznochzdwosyl","95a8e2f8c07cf64b80500a788f87ec46f1d2eaf6b2b542034f25f4e0311b0e2f");
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

