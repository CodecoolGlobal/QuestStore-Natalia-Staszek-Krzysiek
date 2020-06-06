package dao;

import model.Mentor;
import model.User;
import view.View;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MentorDao implements UserDao {
    Statement st;
    ArrayList<User> mentors = new ArrayList<>();

    public static void main(String[] args) {
        MentorDao mentorDao = new MentorDao();
        View.showPersonList(mentorDao.extractor());
    }

//    public MentorDao(String login, String password, String database) {
//        super(login, password, database);
//    }

    @Override
    public ArrayList<User> extractor() {
        try {
            connect();
            ResultSet rs = st.executeQuery("SELECT * FROM users WHERE id_role=2");
            while (rs.next()) {
                Mentor mentor = new Mentor();
                mentor.setId(rs.getInt("id"));
                mentor.setName(rs.getString("name"));
                mentor.setLogin(rs.getString("login"));
                mentor.setEmail(rs.getString("email"));
                mentor.setPassword(rs.getString("password"));
                mentor.setPhoneNumber(rs.getString("phone_number"));
                mentor.setRole(rs.getInt("id_role"));
                mentors.add(mentor);
            }
            rs.close();
            disconnect();
            return mentors;
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
