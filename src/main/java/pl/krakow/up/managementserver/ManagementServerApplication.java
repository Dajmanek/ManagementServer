package pl.krakow.up.managementserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class ManagementServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementServerApplication.class, args);
    }

}
