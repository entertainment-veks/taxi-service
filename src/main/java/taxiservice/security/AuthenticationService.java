package taxiservice.security;

import taxiservice.exception.AuthenticationException;
import taxiservice.model.Driver;

public interface AuthenticationService {
    Driver login(String login, String password) throws AuthenticationException;
}
