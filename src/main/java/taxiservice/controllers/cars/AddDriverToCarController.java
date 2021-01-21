package taxiservice.controllers.cars;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taxiservice.lib.Injector;
import taxiservice.service.CarService;
import taxiservice.service.DriverService;

public class AddDriverToCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxiservice");
    private static CarService carService = (CarService)
            injector.getInstance(CarService.class);
    private static DriverService driverService = (DriverService)
            injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/cars/addDriver.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        carService.addDriverToCar(driverService.get(Long.valueOf(req.getParameter("driver_id"))),
                carService.get(Long.valueOf(req.getParameter("car_id"))));
        resp.sendRedirect(req.getContextPath() + "/cars/all");
    }
}
