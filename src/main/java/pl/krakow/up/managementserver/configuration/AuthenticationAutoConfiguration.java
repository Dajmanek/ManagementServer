package pl.krakow.up.managementserver.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.krakow.up.managementserver.authentication.AuthenticationHandlerMethodArgumentResolver;
import pl.krakow.up.managementserver.authentication.AuthenticationInterceptor;
import pl.krakow.up.managementserver.service.UserService;

import java.util.List;

@Configuration
@ConditionalOnClass(WebMvcConfigurer.class)
public class AuthenticationAutoConfiguration implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    AuthenticationInterceptor authenticationInterceptor(final UserService userService) {
        return new AuthenticationInterceptor(userService);
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(this.applicationContext.getBean(AuthenticationInterceptor.class));
    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AuthenticationHandlerMethodArgumentResolver());
    }
}
