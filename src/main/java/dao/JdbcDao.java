package dao;

import java.util.List;

public interface JdbcDao<T> {
    T get(int id);
    List<T> getAll();
//    List<T> getAllByUserId(int userId);
    boolean add(T t);
    boolean update(T t);
    boolean remove(T t);
}
