package dao3.db;

import dao3.model.Car;
import dao3.model.Driver;
import dao3.model.Manufacturer;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final List<Car> cars = new ArrayList<>();
    public static final List<Driver> drivers = new ArrayList<>();
    public static final List<Manufacturer> manufacturers = new ArrayList<>();

    private static Long carIds = 0L;
    private static Long driverIds = 0L;
    private static Long manufacturerIds = 0L;

    public static Car addCarWithId(Car car) {
        carIds++;
        car.setId(carIds);
        cars.add(car);
        return car;
    }

    public static Driver addDriverWithId(Driver driver) {
        driverIds++;
        driver.setId(driverIds);
        drivers.add(driver);
        return driver;
    }

    public static Manufacturer addManufacturerWithId(Manufacturer manufacturer) {
        manufacturerIds++;
        manufacturer.setId(manufacturerIds);
        manufacturers.add(manufacturer);
        return manufacturer;
    }
}
