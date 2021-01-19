package taxiservice;

import java.util.ArrayList;
import java.util.List;
import taxiservice.lib.Injector;
import taxiservice.model.Car;
import taxiservice.model.Driver;
import taxiservice.model.Manufacturer;
import taxiservice.service.CarService;
import taxiservice.service.DriverService;
import taxiservice.service.ManufacturerService;

public class Application {
    private static final Injector injector = Injector.getInstance("taxiservice");

    private static ManufacturerService manufacturerService = (ManufacturerService)
            injector.getInstance(ManufacturerService.class);

    private static DriverService driverService = (DriverService)
            injector.getInstance(DriverService.class);

    private static CarService carService = (CarService)
            injector.getInstance(CarService.class);

    private static Manufacturer toyotaManufacturer;
    private static Manufacturer nissanManufacturer;
    private static Manufacturer mazdaManufacturer;

    private static Driver bobDriver;
    private static Driver johnDriver;
    private static Driver bruceDriver;

    private static long bobDriverId;

    public static void main(String[] args) {
        manufacturersCheck();
        driversCheck();
        carCheck();
    }

    private static void manufacturersCheck() {
        toyotaManufacturer = new Manufacturer("Toyota", "Japan");
        nissanManufacturer = new Manufacturer("Nissan", "Japan");
        mazdaManufacturer = new Manufacturer("Mazda", "Japan");

        toyotaManufacturer = manufacturerService.create(toyotaManufacturer);
        nissanManufacturer = manufacturerService.create(nissanManufacturer);
        mazdaManufacturer = manufacturerService.create(mazdaManufacturer);

        long toyotaManufacturerId = toyotaManufacturer.getId();

        System.out.println("Manufacturer N1:");
        System.out.println(manufacturerService.get(toyotaManufacturerId));

        System.out.println("All manufacturers");
        System.out.println(manufacturerService.getAll().toString());

        System.out.println("Let's change 2nd manufacturer country");
        nissanManufacturer.setCountry("Ukraine");
        manufacturerService.update(nissanManufacturer);

        System.out.println("Now db looks like:");
        System.out.println(manufacturerService.getAll().toString());
    }

    private static void driversCheck() {
        bobDriver = new Driver("Bob", "101");
        johnDriver = new Driver("John", "102");
        bruceDriver = new Driver("Bruce", "103");

        bobDriver = driverService.create(bobDriver);
        johnDriver = driverService.create(johnDriver);
        bruceDriver = driverService.create(bruceDriver);

        bobDriverId = bobDriver.getId();

        System.out.println("Driver N1:");
        System.out.println(driverService.get(bobDriverId));

        System.out.println("All drivers");
        System.out.println(driverService.getAll().toString());

        System.out.println("Let's change 2nd driver licence number");
        johnDriver.setLicenceNumber("200");
        driverService.update(johnDriver);
        System.out.println("Now db looks like:");
        System.out.println(driverService.getAll().toString());
    }

    private static void carCheck() {
        Car toyotaCar = new Car("Toyota", toyotaManufacturer);
        Car nissanCar = new Car("Nissan", nissanManufacturer);
        Car mazdaCar = new Car("Mazda", mazdaManufacturer);

        List<Driver> toyotaDrivers = List.of(bobDriver, johnDriver);
        List<Driver> nissanDrivers = List.of(bruceDriver);
        List<Driver> mazdaCDrivers = new ArrayList<>();

        toyotaCar.setDrivers(toyotaDrivers);
        nissanCar.setDrivers(nissanDrivers);
        mazdaCar.setDrivers(mazdaCDrivers);

        toyotaCar = carService.create(toyotaCar);
        nissanCar = carService.create(nissanCar);
        mazdaCar = carService.create(mazdaCar);

        long toyotaCarId = toyotaCar.getId();
        long mazdaCarId = mazdaCar.getId();

        System.out.println("Car N1:");
        System.out.println(carService.get(toyotaCarId));

        System.out.println("All cars");
        System.out.println(carService.getAll().toString());

        System.out.println("Let's change 2nd car model");
        nissanCar.setModel("BMW");
        carService.update(nissanCar);

        System.out.println("Now db looks like:");
        System.out.println(carService.getAll().toString());

        System.out.println("Let's get all cars by driver:");
        System.out.println(carService.getAllByDriver(bobDriverId));
    }
}
