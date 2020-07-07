package handler;

import java.util.List;

public interface Controller<T> {

    boolean create(T t);
    List<T> readAll();
    T read(int id);
    boolean update(T t);
    boolean delete(T t);
}
