package dev.rdkln.tracker.security.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/auth")
@RestController
public class AuthenticationController {



    @PostMapping("login")
    public String login(@RequestBody String entity) {
        return entity;
    }
    @PostMapping("/register")
    public String register(@RequestBody String email) {
        return email;
    }

    @PostMapping("refresh-token")
    public String refreshToken(@RequestBody String entity) {
        //TODO: process POST request

        return entity;
    }


}
