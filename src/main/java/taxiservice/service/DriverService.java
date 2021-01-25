package taxiservice.service;

import java.util.Optional;
import taxiservice.model.Driver;

public interface DriverService extends GenericService<Driver, Long> {
    Optional<Driver> findByLogin(String login);
}
