package taxiservice.web.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taxiservice.lib.Injector;
import taxiservice.service.DriverService;

public class AuthentificationFilter implements Filter {
    private static final String DRIVER_ID = "driver_id";
    private static final Injector injector = Injector.getInstance("taxiservice");
    private static DriverService driverService = (DriverService)
            injector.getInstance(DriverService.class);
    private Set<String> links = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        links.add("/drivers/login");
        links.add("/drivers/add");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String url = req.getServletPath();
        if (links.contains(url)) {
            chain.doFilter(req, resp);
            return;
        }

        Long driverId = (Long) req.getSession().getAttribute(DRIVER_ID);
        if (driverId == null || driverService.get(driverId) == null) {
            resp.sendRedirect(req.getContextPath() + "/drivers/login");
            return;
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
