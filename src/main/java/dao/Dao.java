package dao;

public abstract class Dao {

    public abstract class DAO {
        protected final String login;
        protected final String password;
        protected final String database;
        protected Connection connection;
        PreparedStatement statement;
    }
}
