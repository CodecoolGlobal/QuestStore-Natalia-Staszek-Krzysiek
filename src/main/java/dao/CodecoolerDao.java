package dao;

import model.Codecooler;
import model.Creep;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CodecoolerDao extends Dao implements UserDao{
    ArrayList<User> codecoolers = new ArrayList<>();

    public CodecoolerDao(String login, String password, String database) {
        super(login, password, database);
    }

    public ArrayList<User> extractor(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Codecooler codecooler = new Codecooler();
            codecooler.setId(resultSet.getInt("id"));
            codecooler.setName(resultSet.getString("name"));
            codecooler.setLogin(resultSet.getString("login"));
            codecooler.setEmail(resultSet.getString("email"));
            codecooler.setPassword(resultSet.getString("password"));
            codecooler.setPhoneNumber(resultSet.getString("phone_number"));
            codecooler.setRole(resultSet.getInt("id_role"));
            codecoolers.add(codecooler);
        }
        disconnect();
        return this.codecoolers;
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
