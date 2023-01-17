package pl.krakow.up.managementserver.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final String response;

    public TestService(@Value("${test.property}") final String response) {
        this.response = response;
    }

    public String getResponse() {
        return this.response;
    }

}
