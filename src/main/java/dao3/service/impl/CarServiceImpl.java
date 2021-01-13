package dao3.service.impl;

import dao3.dao.CarDao;
import dao3.lib.Inject;
import dao3.lib.Service;
import dao3.model.Car;
import dao3.model.Driver;
import dao3.service.CarService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Inject
    private CarDao carDao;

    @Override
    public Car create(Car car) {
        return carDao.create(car);
    }

    @Override
    public Car get(Long id) {
        return carDao.get(id).get();
    }

    @Override
    public List<Car> getAll() {
        return carDao.getAll();
    }

    @Override
    public Car update(Car car) {
        return carDao.update(car);
    }

    @Override
    public boolean delete(Long id) {
        return carDao.delete(id);
    }

    @Override
    public void addDriverToCar(Driver driver, Car car) {
        car.getDrivers().add(driver);
    }

    @Override
    public void removeDriverFromCar(Driver driver, Car car) {
        car.getDrivers().remove(driver);
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        List<Car> result = new ArrayList<>();
        for (Car currentCar : carDao.getAll()) {
            for (Driver currentDriver : currentCar.getDrivers()) {
                if (currentDriver.getId().equals(driverId)) {
                    result.add(currentCar);
                }
            }
        }
        return result;
    }
}
