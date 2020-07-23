package dao;

import Exceptions.DatabaseException;
import model.User;

import java.util.List;

public interface UserDAO {

    List<User> getAll() throws DatabaseException;
    List<User> getAllByRole(int role) throws DatabaseException;
    List<User> getStudentsByGroupId(int id) throws DatabaseException;
    User getById(int id);
    User getByLoginAndPassword(String login, String password);
    User getByLoginAndRole(String login, int role);
    User getByLogin(String login);
    User getByEmail(String email);
    User getByPhoneNumber(String phoneNumber);
    boolean add(User user);
    boolean update(User user);
    boolean delete(User user);
}
