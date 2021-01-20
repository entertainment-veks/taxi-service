package taxiservice.service;

import java.util.List;
import taxiservice.model.Car;
import taxiservice.model.Driver;

public interface CarService extends GenericService<Car, Long> {
    @Override
    Car create(Car car);

    @Override
    Car get(Long id);

    @Override
    List<Car> getAll();

    @Override
    Car update(Car car);

    @Override
    boolean delete(Long id);

    void addDriverToCar(Driver driver, Car car);

    void removeDriverFromCar(Driver driver, Car car);

    List<Car> getAllByDriver(Long driverId);
}
