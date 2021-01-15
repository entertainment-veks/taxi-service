package dao3.dao;

import dao3.model.Car;
import java.util.List;
import java.util.Optional;

public interface CarDao extends GenericDao<Car, Long> {
    List<Car> getAllByDriver(Long driverId);
}
