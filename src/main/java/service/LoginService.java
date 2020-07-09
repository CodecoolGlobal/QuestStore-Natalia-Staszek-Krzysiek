package service;

import dao.SQL.SQLUserDao;
import dao.UserDAO;
import model.User;

public class LoginService {

    UserDAO userDAO = new SQLUserDao();

    public User getUser(String login, String password) {
        try {
            return userDAO.getByLoginAndPassword(login,password);
        } catch (Throwable error) {
            error.printStackTrace();
        }
        return null;
    }

}
