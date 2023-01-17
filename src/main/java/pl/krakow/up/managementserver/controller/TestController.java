package pl.krakow.up.managementserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.krakow.up.managementserver.persistence.dao.ClientDao;
import pl.krakow.up.managementserver.persistence.model.Client;
import pl.krakow.up.managementserver.service.EncryptionService;
import pl.krakow.up.managementserver.service.TestService;

import java.util.Optional;

@Controller
public class TestController {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private TestService testService;

    @Autowired
    private EncryptionService encryptionService;

    @GetMapping("/test")
    public ResponseEntity<Client> test() {
        final Optional<Client> optionalClient = this.clientDao.findAll().stream().findFirst();
        return ResponseEntity.ok(optionalClient.get());
        //return ResponseEntity.ok(testService.getResponse());
    }

    @GetMapping("encrypt")
    public ResponseEntity<String> encrypt(final String text) {
        return ResponseEntity.ok(this.encryptionService.encrypt(text));
    }

}
