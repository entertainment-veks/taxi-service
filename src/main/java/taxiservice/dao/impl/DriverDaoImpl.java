package taxiservice.dao.impl;

import java.util.List;
import java.util.Optional;
import taxiservice.dao.DriverDao;
import taxiservice.db.Storage;
import taxiservice.lib.Dao;
import taxiservice.model.Driver;

@Dao
public class DriverDaoImpl implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        return Storage.addDriverWithId(driver);
    }

    @Override
    public Optional<Driver> get(Long id) {
        return Storage.drivers.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Driver> getAll() {
        return Storage.drivers;
    }

    @Override
    public Driver update(Driver driver) {
        for (int i = 0; i < Storage.drivers.size(); i++) {
            if (driver.getId().equals(Storage.drivers.get(i).getId())) {
                Storage.drivers.set(i, driver);
                return driver;
            }
        }
        throw new RuntimeException("Can't update, element " + driver + " doesn't exist");
    }

    @Override
    public boolean delete(Long id) {
        return Storage.drivers.removeIf(e -> e.getId().equals(id));
    }
}