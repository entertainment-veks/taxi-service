package taxiservice.service;

import java.util.List;

public interface GenericService<T> {
    T create(T value);

    T get(Long id);

    List<T> getAll();

    T update(T value);

    boolean delete(Long id);
}
