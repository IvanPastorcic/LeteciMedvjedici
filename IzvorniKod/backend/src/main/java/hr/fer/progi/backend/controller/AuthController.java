package hr.fer.progi.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/")
    public String home(){
        return "Hello, Home!";
    }

    @GetMapping("/secured")
    public String secured(){
        return "Hello, Secured!";
    }

}
