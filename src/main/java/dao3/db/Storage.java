package dao3.db;

import dao3.model.Car;
import dao3.model.Driver;
import dao3.model.Manufacturer;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static List<Car> cars = new ArrayList<>();
    public static List<Driver> drivers = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();

    private static int carIds = 0;
    private static int driverIds = 0;
    private static int manufacturerIds = 0;

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
