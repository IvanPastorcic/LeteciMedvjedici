package hr.fer.progi.backend.controller;

import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.repository.exception.InputIsNullException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


//controller za provjeru autentifikacije.
@RestController
public class LoginController {

    @GetMapping("/login")
    public ResponseEntity<String> login() {

        return ResponseEntity.ok("Welcome user!");
    }

    @GetMapping("/home")
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("Accessed home page");
    }
}
