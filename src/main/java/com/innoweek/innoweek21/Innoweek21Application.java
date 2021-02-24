package com.innoweek.innoweek21;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;

@CrossOrigin(origins = "*")
@RestController
@SpringBootApplication
public class Innoweek21Application {

    private static final String port_number = "8080";

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Innoweek21Application.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", port_number));
        app.run(args);
    }

}
