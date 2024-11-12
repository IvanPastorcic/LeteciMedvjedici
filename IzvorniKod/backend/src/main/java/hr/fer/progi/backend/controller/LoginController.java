package hr.fer.progi.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//controller za provjeru autentifikacije. TODO: obrisati ovu klasu kasnije
@RestController
public class LoginController {

    @GetMapping("/")
    public String hello(){
        return "this is / endpoint";
    }
}
