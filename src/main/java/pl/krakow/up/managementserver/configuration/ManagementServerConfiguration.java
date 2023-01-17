package pl.krakow.up.managementserver.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@PropertySource("classpath:/application.properties")
@EnableJpaRepositories(basePackages = "pl.krakow.up.managementserver.persistence.dao")
public class ManagementServerConfiguration {


}
