package handler;

import Exceptions.DatabaseException;

import java.util.List;

public interface Controller<T> {

    boolean create(T t);
    List<T> readAll() throws DatabaseException;
    T read(int id) throws DatabaseException;
    boolean update(T t);
    boolean delete(T t);
}
