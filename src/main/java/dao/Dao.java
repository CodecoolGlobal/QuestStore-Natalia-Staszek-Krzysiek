package dao;

import java.sql.*;

public abstract class Dao {
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

}
