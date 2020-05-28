package dao;

import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao {

    public ArrayList<User> extractor(ResultSet resultSet) throws SQLException;

    public void injector(User user);


}
