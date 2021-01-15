package dao3;

import dao3.lib.Injector;
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
    }
}
