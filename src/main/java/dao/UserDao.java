package dao;

import model.User;

import java.util.ArrayList;

public interface UserDao {

    public ArrayList<User> extractor();

    public void injector(User user);


}
