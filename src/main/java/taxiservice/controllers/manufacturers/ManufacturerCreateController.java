package taxiservice.controllers.manufacturers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taxiservice.lib.Injector;
import taxiservice.model.Manufacturer;
import taxiservice.service.ManufacturerService;

public class ManufacturerCreateController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxiservice");
    private static ManufacturerService manufacturerService = (ManufacturerService)
            injector.getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/manufacturers/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Manufacturer input = new Manufacturer(req.getParameter("name"),
                req.getParameter("country"));
        manufacturerService.create(input);
        resp.sendRedirect(req.getContextPath() + "/manufacturers/all");
    }
}
