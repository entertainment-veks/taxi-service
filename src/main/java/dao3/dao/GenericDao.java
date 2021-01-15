package dao3.dao;

import dao3.model.Driver;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T, I> {
    T create(T t);

    Optional<T> get(I i);

    List<T> getAll();

    T update(T t);

    boolean delete(I i);
}
