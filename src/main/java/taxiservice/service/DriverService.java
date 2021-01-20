package taxiservice.service;

import java.util.List;
import taxiservice.model.Driver;

public interface DriverService extends GenericService<Driver, Long> {
    @Override
    Driver create(Driver driver);

    @Override
    Driver get(Long id);

    @Override
    List<Driver> getAll();

    @Override
    Driver update(Driver driver);

    @Override
    boolean delete(Long id);
}
