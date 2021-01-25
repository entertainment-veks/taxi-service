package taxiservice.controller.driver;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taxiservice.lib.Injector;
import taxiservice.model.Car;
import taxiservice.service.CarService;

public class GetMyCurrentCarsController extends HttpServlet { //todo
    private static final String DRIVER_ID = "driver_id";
    private static final Injector injector = Injector.getInstance("taxiservice");
    private static CarService carService = (CarService)
            injector.getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long driverId = (Long) req.getSession().getAttribute(DRIVER_ID);
        List<Car> allCars = carService.getAllByDriver(driverId);

        req.setAttribute("allCars", allCars);
        req.getRequestDispatcher("/WEB-INF/views/car/all.jsp").forward(req, resp);
    }
}
