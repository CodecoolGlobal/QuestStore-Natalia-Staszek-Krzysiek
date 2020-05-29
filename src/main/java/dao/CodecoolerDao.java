package dao;

import model.Codecooler;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CodecoolerDao extends Dao implements UserDao{
    ArrayList<User> codecoolers = new ArrayList<>();
    Statement st;
    private final ItemDao itemDao;
    private final QuestDao questDao;

    public CodecoolerDao(String login, String password, String database, ItemDao itemDao, QuestDao questDao) {
        this.itemDao = itemDao;
        this.questDao = questDao;
    }

    public ArrayList<User> extractor() {

        try {
            connect();
            ResultSet rs = st.executeQuery("select * from user where id_role=3");
            while (rs.next()) {
                Codecooler codecooler = new Codecooler();
                codecooler.setId(rs.getInt("id"));
                codecooler.setName(rs.getString("name"));
                codecooler.setLogin(rs.getString("login"));
                codecooler.setEmail(rs.getString("email"));
                codecooler.setPassword(rs.getString("password"));
                codecooler.setPhoneNumber(rs.getString("phone_number"));
                codecooler.setRole(rs.getInt("id_role"));
                codecoolers.add(codecooler);
            }
            rs.close();
            disconnect();
            return codecoolers;
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
