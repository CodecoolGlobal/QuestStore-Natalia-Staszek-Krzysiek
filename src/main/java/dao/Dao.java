package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Dao {
    protected final String login;
    protected final String password;
    protected final String database;
    protected Connection connection;
    protected String databaseURL;
    PreparedStatement statement;

    public Dao(){
        this.login = "fbznochzdwosyl, ";
        this.password = "95a8e2f8c07cf64b80500a788f87ec46f1d2eaf6b2b542034f25f4e0311b0e2f";
        this.database = "d3nuc8s3988iho, ";
        this.databaseURL = "jdbc:postgresql://ec2-54-75-246-118.eu-west-1.compute.amazonaws.com/";
    }

    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");

            this.connection = DriverManager.getConnection(databaseURL +
                            database + login + password);
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
}
