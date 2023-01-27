package pl.krakow.up.managementserver.authentication;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import pl.krakow.up.managementserver.exception.BadRequestException;
import pl.krakow.up.managementserver.exception.UnauthorizedException;
import pl.krakow.up.managementserver.persistence.model.User;
import pl.krakow.up.managementserver.service.UserService;


import static io.micrometer.common.util.StringUtils.isBlank;
import static java.util.Objects.isNull;

public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final String LOGIN_HEADER = "authenticate_login";
    private static final String PASSWORD_HEADER = "authenticate_password";

    public static final String USER_ATTRIBUTE = "authenticated_user";

    private final UserService userService;

    public AuthenticationInterceptor(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {

        if (!this.usesAuthentication(handler)) {
            return true;
        }

        final String login = request.getHeader(LOGIN_HEADER);
        final String password = request.getHeader(PASSWORD_HEADER);

        if (isNull(login) || isNull(password)) {
            throw new BadRequestException("Incorrect credentials - login [" + login + "] or password is null");
        }

        if (login.isBlank() || password.isBlank()) {
            throw new BadRequestException("Incorrect credentials, login [" + login + "] or password is blank");
        }

        final User user = this.userService.authorizeUser(login, password);

        request.setAttribute(USER_ATTRIBUTE, user);

        return true;
    }

    private boolean usesAuthentication(final Object handler) {
        return (handler instanceof HandlerMethod handlerMethod && handlerMethod.hasMethodAnnotation(Authenticate.class));
    }
}
