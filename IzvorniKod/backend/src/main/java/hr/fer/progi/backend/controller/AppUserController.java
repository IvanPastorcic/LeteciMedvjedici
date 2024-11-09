package hr.fer.progi.backend.controller;


import hr.fer.progi.backend.model.AppUser;
import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.repository.exception.InputIsNullException;
import hr.fer.progi.backend.repository.exception.WrongInputException;
import hr.fer.progi.backend.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/user")
public class AppUserController {

    private final UserService userService;

    public AppUserController(UserService userService) {
        this.userService = userService;
    }


    //dodati role
    @PostMapping("/register")
    public ResponseEntity<HttpStatus> saveUser(@RequestBody AppUser newUser)
    {
        if (userService.fetchUserByEmail(newUser.getEmail()) != null)
        {
            throw new WrongInputException("Već postoji korisnik s tim emailom");
        }

        Long newUserId = userService.insertUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).header(String.valueOf(newUserId)).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long userId)
    {
        if(userService.fetchUserById(userId) == null)
        {
            throw new InputIsNullException("Korisnik ne postoji");
        }

        return ResponseEntity.ok().body(userService.fetchUserById(userId));
    }

    @GetMapping("/{userId}/reports")
    public ResponseEntity<List<Report>> getUserReports(@PathVariable Long userId){

        List<Report> userReportList = new ArrayList<>(userService.fetchReportsByUser(userId));
        return ResponseEntity.ok(userReportList);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> delete(@PathVariable("userId") Long id){
        Optional<AppUser> existingUser = userService.findById(id);
        if(existingUser.isPresent()){
            this.userService.delete(existingUser.get());
            return ResponseEntity.ok("User deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

}
