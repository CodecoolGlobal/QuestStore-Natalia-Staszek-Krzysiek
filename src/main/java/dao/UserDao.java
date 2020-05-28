package dao;

import model.Creep;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao extends Dao{

    public UserDao(String login, String password, String database) {
        super(login, password, database);
    }

    public ArrayList<Creep> creepExtractor(ResultSet resultSet) throws SQLException {
        ArrayList<Creep> creeps = new ArrayList<>();
        while (resultSet.next()) {
            Creep creep = new Creep();
            creep.setId(resultSet.getInt("id"));
            creep.setName(resultSet.getString("first_name"));
            creep.setLogin(resultSet.getString("last_name"));
            creep.setEmail(resultSet.getString("email"));
            creep.setPassword(resultSet.getString("password"));
            creep.setPhoneNumber(resultSet.getString("phone_number"));
            creep.setRole(resultSet.getString("role"));
            creeps.add(creep);
        }
        disconnect();
        return creeps;
    }
}
