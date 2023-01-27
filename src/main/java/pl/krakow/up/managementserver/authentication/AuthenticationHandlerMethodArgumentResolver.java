package pl.krakow.up.managementserver.authentication;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import pl.krakow.up.managementserver.persistence.model.User;

import static pl.krakow.up.managementserver.authentication.AuthenticationInterceptor.USER_ATTRIBUTE;

public class AuthenticationHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(User.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter,
                                  final ModelAndViewContainer mavContainer,
                                  final NativeWebRequest webRequest,
                                  final WebDataBinderFactory binderFactory) {

        if (!(webRequest.getAttribute(USER_ATTRIBUTE, 0) instanceof User user)) {
            return null;
        }

        webRequest.removeAttribute(USER_ATTRIBUTE, 0);
        return user;
    }
}
