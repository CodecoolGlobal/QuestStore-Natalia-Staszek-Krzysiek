package service;

<<<<<<< HEAD
import dao.SQL.SQLUserDao;
=======

>>>>>>> 9c77cb373b06ee0dbe17eb9f1ab6a93850922590
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
