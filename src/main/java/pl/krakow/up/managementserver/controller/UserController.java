package pl.krakow.up.managementserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.krakow.up.managementserver.authentication.Authenticate;
import pl.krakow.up.managementserver.controller.advice.ControllerAdviceProvider;

@Controller
@RequestMapping("/user")
public class UserController implements ControllerAdviceProvider {

    @Authenticate
    @GetMapping("/authenticate")
    public ResponseEntity<String> authenticate() {
        return ResponseEntity.ok("authenticated");
    }

}
