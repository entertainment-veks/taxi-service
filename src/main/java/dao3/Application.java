package dao3;

import dao3.lib.Injector;
import dao3.model.Car;
import dao3.model.Driver;
import dao3.model.Manufacturer;
import dao3.service.CarService;
import dao3.service.DriverService;
import dao3.service.ManufacturerService;

public class Application {
    private static final Injector injector = Injector.getInstance("dao3");

    public static void main(String[] args) {
        ManufacturerService manufacturerService = (ManufacturerService)
                injector.getInstance(ManufacturerService.class);
        DriverService driverService = (DriverService)
                injector.getInstance(DriverService.class);
        CarService carService = (CarService)
                injector.getInstance(CarService.class);

        Manufacturer manufacturer1 = new Manufacturer("Toyota", "Japan");
        Manufacturer manufacturer2 = new Manufacturer("Nissan", "Japan");
        Manufacturer manufacturer3 = new Manufacturer("Mazda", "Japan");
        Manufacturer manufacturer4 = new Manufacturer("Mercedes", "Germany");

        manufacturer1 = manufacturerService.create(manufacturer1);
        manufacturer2 = manufacturerService.create(manufacturer2);
        manufacturer3 = manufacturerService.create(manufacturer3);
        manufacturer4 = manufacturerService.create(manufacturer4);

        long manufacturer1id = manufacturer1.getId();
        long manufacturer2id = manufacturer2.getId();
        long manufacturer3id = manufacturer3.getId();
        long manufacturer4id = manufacturer4.getId();

        System.out.println("Manufacturer N1:");
        System.out.println(manufacturerService.get(manufacturer1id));

        System.out.println("All manufacturers");
        System.out.println(manufacturerService.getAll().toString());

        System.out.println("Let's change 2nd manufacturer country");
        manufacturer2.setCountry("Ukraine");
        manufacturerService.update(manufacturer2);

        System.out.println("Now db looks like:");
        System.out.println(manufacturerService.getAll().toString());

        System.out.println("Removing 1st and 3rd manufacturers");
        manufacturerService.delete(manufacturer1id);
        manufacturerService.delete(manufacturer3id);

        System.out.println("Now db looks like:");
        System.out.println(manufacturerService.getAll().toString());

        Driver driver1 = new Driver("Bob", "101");
        Driver driver2 = new Driver("John", "102");
        Driver driver3 = new Driver("Bruce", "103");
        Driver driver4 = new Driver("Abdul", "104");

        driver1 = driverService.create(driver1);
        driver2 = driverService.create(driver2);
        driver3 = driverService.create(driver3);
        driver4 = driverService.create(driver4);

        long driver1id = driver1.getId();
        long driver2id = driver2.getId();
        long driver3id = driver3.getId();
        long driver4id = driver4.getId();

        System.out.println("Driver N1:");
        System.out.println(driverService.get(driver1id));

        System.out.println("All drivers");
        System.out.println(driverService.getAll().toString());

        System.out.println("Let's change 2nd driver licence number");
        driver2.setLicenceNumber("200");
        driverService.update(driver2);

        System.out.println("Now db looks like:");
        System.out.println(driverService.getAll().toString());

        System.out.println("Removing 1st and 3rd manufacturers");
        driverService.delete(driver1id);
        driverService.delete(driver3id);

        System.out.println("Now db looks like:");
        System.out.println(driverService.getAll().toString());

        Car car1 = new Car("Toyota", manufacturer1);
        Car car2 = new Car("Nissan", manufacturer2);
        Car car3 = new Car("Mazda", manufacturer3);
        Car car4 = new Car("Mercedes", manufacturer4);

        car1 = carService.create(car1);
        car2 = carService.create(car2);
        car3 = carService.create(car3);
        car4 = carService.create(car4);

        long car1id = car1.getId();
        long car2id = car2.getId();
        long car3id = car3.getId();
        long car4id = car4.getId();

        System.out.println("Car N1:");
        System.out.println(carService.get(car1id));

        System.out.println("All drivers");
        System.out.println(carService.getAll().toString());

        System.out.println("Let's change 2nd car model");
        car2.setModel("BMW");
        carService.update(car2);

        System.out.println("Now db looks like:");
        System.out.println(carService.getAll().toString());

        System.out.println("Removing 1st and 3rd cars");
        carService.delete(car1id);
        carService.delete(car3id);

        System.out.println("Now db looks like:");
        System.out.println(carService.getAll().toString());

        System.out.println("Let's add driver to car:");
        carService.addDriverToCar(driver1, car1);
        System.out.println(car1);

        System.out.println("Let's remove driver from the car:");
        carService.removeDriverFromCar(driver1, car1);
        System.out.println(car1);

        System.out.println("Let's get all cars by driver:");
        carService.addDriverToCar(driver1, car1);
        carService.addDriverToCar(driver1, car2);
        carService.addDriverToCar(driver1, car3);
        carService.addDriverToCar(driver1, car4);
        System.out.println(carService.getAllByDriver(driver1id));
    }
}
