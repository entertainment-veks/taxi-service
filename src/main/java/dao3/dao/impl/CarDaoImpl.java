package dao3.dao.impl;

import dao3.dao.CarDao;
import dao3.db.Storage;
import dao3.lib.Dao;
import dao3.model.Car;
import dao3.model.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class CarDaoImpl implements CarDao {
    @Override
    public Car create(Car car) {
        return Storage.addCarWithId(car);
    }

    @Override
    public Optional<Car> get(Long id) {
        return Storage.cars.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Car> getAll() {
        return Storage.cars;
    }

    @Override
    public Car update(Car car) {
        for (int i = 0; i < Storage.cars.size(); i++) {
            if (car.getId().equals(Storage.cars.get(i).getId())) {
                Storage.cars.set(i, car);
                return car;
            }
        }
        throw new RuntimeException("Can't update, element " + car + " doesn't exist");
    }

    @Override
    public boolean delete(Long id) {
        return Storage.cars.removeIf(e -> e.getId().equals(id));
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        List<Car> result = new ArrayList<>();
        for (Car currentCar : getAll()) {
            for (Driver currentDriver : currentCar.getDrivers()) {
                if (currentDriver.getId().equals(driverId)) {
                    result.add(currentCar);
                }
            }
        }
        return result;
    }
}
