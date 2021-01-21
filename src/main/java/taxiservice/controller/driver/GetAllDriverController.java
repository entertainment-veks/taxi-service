package taxiservice.controller.driver;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taxiservice.lib.Injector;
import taxiservice.model.Driver;
import taxiservice.service.DriverService;

public class GetAllDriverController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxiservice");
    private static DriverService driverService = (DriverService)
            injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Driver> allDrivers = driverService.getAll();

        req.setAttribute("allDrivers", allDrivers);
        req.getRequestDispatcher("/WEB-INF/views/driver/all.jsp").forward(req, resp);
    }
}
