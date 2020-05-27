package dao;

public abstract class Dao {

    public abstract class DAO {
        protected final String login;
        protected final String password;
        protected final String database;
        protected Connection connection;
        PreparedStatement statement;
    }

    private void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-75-246-118.eu-west-1.compute.amazonaws.com/" +
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

}
