package dao3.dao.jdbc;

import dao3.dao.DriverDao;
import dao3.model.Driver;

import java.util.List;
import java.util.Optional;

//todo in dao 6
public class DriverDaoJDBCImpl implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        return null;
    }

    @Override
    public Optional<Driver> get(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Driver> getAll() {
        return null;
    }

    @Override
    public Driver update(Driver driver) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
