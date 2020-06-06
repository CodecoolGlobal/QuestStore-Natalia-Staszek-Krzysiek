package dao;

import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CreepDao implements UserDao {

    ArrayList<User> creeps = new ArrayList<>();
    Statement st;

    @Override
    public ArrayList<User> extractor() {

        try {
            connect();
            ResultSet rs = st.executeQuery("select * from users where id_role=1");
            while (rs.next()) {
                Creep creep = new Creep();
                creep.setId(rs.getInt("id"));
                creep.setName(rs.getString("name"));
                creep.setLogin(rs.getString("login"));
                creep.setEmail(rs.getString("email"));
                creep.setPassword(rs.getString("password"));
                creep.setPhoneNumber(rs.getString("phone_number"));
                creep.setRole(rs.getInt("id_role"));
                creeps.add(creep);
            }
            rs.close();
            disconnect();
            return creeps;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return null;
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

