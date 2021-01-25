package taxiservice.security.impl;

import taxiservice.exception.AuthenticationException;
import taxiservice.lib.Inject;
import taxiservice.lib.Service;
import taxiservice.model.Driver;
import taxiservice.security.AuthenticationService;
import taxiservice.service.DriverService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    DriverService driverService;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Driver input = driverService.findByLogin(login).orElseThrow(() ->
                new AuthenticationException("Incorrect username or password"));

        if (input.getPassword().equals(password)) {
            return input;
        }
        throw new AuthenticationException("Incorrect username or password");
    }
}
