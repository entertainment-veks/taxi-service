package taxiservice.service;

import java.util.List;
import taxiservice.model.Driver;

public interface DriverService {
    Driver create(Driver driver);

    Driver get(Long id);

    List<Driver> getAll();

    Driver update(Driver driver);

    boolean delete(Long id);
}
