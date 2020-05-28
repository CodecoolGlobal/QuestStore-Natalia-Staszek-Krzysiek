package dao;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        UserDao dao = new UserDao("fbznochzdwosyl", "95a8e2f8c07cf64b80500a788f87ec46f1d2eaf6b2b542034f25f4e0311b0e2f", "d3nuc8s3988iho");

        dao.connect();
        dao.creepExtractor(dao.resultSet(dao.queryBuilder("users", "id", 1)));
        dao.disconnect();

    }

}
