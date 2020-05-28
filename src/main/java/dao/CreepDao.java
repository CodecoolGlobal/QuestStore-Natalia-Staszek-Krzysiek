package dao;

import model.Creep;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreepDao extends Dao implements UserDao {

    protected CreepDao(String login, String password, String database) {
        super(login, password, database);
    }

    public ArrayList<User> extractor(ResultSet resultSet) throws SQLException {
        ArrayList<User> creeps = new ArrayList<>();
        while (resultSet.next()) {
            Creep creep = new Creep();
            creep.setId(resultSet.getInt("id"));
            creep.setName(resultSet.getString("name"));
            creep.setLogin(resultSet.getString("login"));
            creep.setEmail(resultSet.getString("email"));
            creep.setPassword(resultSet.getString("password"));
            creep.setPhoneNumber(resultSet.getString("phone_number"));
            creep.setRole(resultSet.getInt("id_role"));
            creeps.add(creep);
        }
        disconnect();
        return creeps;
    }

    @Override
    public void injector(User creep) {
        connect();
        String SQL = "INSERT INTO public.users(name, login, email, password, phone_number, id_role) VALUES(?,?,?,?,?,?)";
        try {
            statement = connection.prepareStatement(SQL);
            statement.setString(1, creep.getName());
            statement.setString(2, creep.getLogin());
            statement.setString(3, creep.getEmail());
            statement.setString(4, creep.getPassword());
            statement.setString(5, creep.getPhoneNumber());
            statement.setInt(6, creep.getRole());
            statement.executeUpdate();
            disconnect();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}

