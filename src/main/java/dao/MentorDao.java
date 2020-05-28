package dao;

import model.Mentor;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MentorDao extends Dao implements UserDao{
    ArrayList<User> mentors = new ArrayList<>();

    public MentorDao(String login, String password, String database) {
        super(login, password, database);
    }

    public ArrayList<User> extractor(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            User mentor = new Mentor();
            mentor.setId(resultSet.getInt("id"));
            mentor.setName(resultSet.getString("name"));
            mentor.setLogin(resultSet.getString("login"));
            mentor.setEmail(resultSet.getString("email"));
            mentor.setPassword(resultSet.getString("password"));
            mentor.setPhoneNumber(resultSet.getString("phone_number"));
            mentor.setRole(resultSet.getInt("id_role"));
            mentors.add(mentor);
        }
        disconnect();
        return mentors;
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
