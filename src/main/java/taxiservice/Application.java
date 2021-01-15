package taxiservice;

import taxiservice.lib.Injector;
import taxiservice.model.Manufacturer;
import taxiservice.service.CarService;
import taxiservice.service.DriverService;
import taxiservice.service.ManufacturerService;

public class Application {
    private static final Injector injector = Injector.getInstance("taxiservice");

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

        manufacturer1 = manufacturerService.create(manufacturer1);
        manufacturer2 = manufacturerService.create(manufacturer2);
        manufacturer3 = manufacturerService.create(manufacturer3);

        long manufacturer1id = manufacturer1.getId();

        System.out.println("Manufacturer N1:");
        System.out.println(manufacturerService.get(manufacturer1id));

        System.out.println("All manufacturers");
        System.out.println(manufacturerService.getAll().toString());

        System.out.println("Let's change 2nd manufacturer country");
        manufacturer2.setCountry("Ukraine");
        manufacturerService.update(manufacturer2);

        System.out.println("Now db looks like:");
        System.out.println(manufacturerService.getAll().toString());

        long manufacturer3id = manufacturer3.getId();

        System.out.println("Removing 1st and 3rd manufacturers");
        manufacturerService.delete(manufacturer1id);
        manufacturerService.delete(manufacturer3id);

        System.out.println("Now db looks like:");
        System.out.println(manufacturerService.getAll().toString());
    }
}
