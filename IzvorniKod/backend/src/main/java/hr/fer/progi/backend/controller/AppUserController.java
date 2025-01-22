package hr.fer.progi.backend.controller;


import hr.fer.progi.backend.dto.UserDTO;
import hr.fer.progi.backend.model.AppUser;
import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.repository.exception.InputIsNullException;
import hr.fer.progi.backend.repository.exception.WrongInputException;
import hr.fer.progi.backend.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/user")
public class AppUserController {

    private final UserService userService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Authentication authentication;

    public AppUserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/id/{userId}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long userId)
    {
        if(userService.fetchUserById(userId) == null)
        {
            throw new InputIsNullException("Korisnik ne postoji");
        }

        return ResponseEntity.ok().body(userService.fetchUserById(userId));
    }

    @GetMapping("/email/{userEmail}")
    public ResponseEntity<AppUser> getUserByEmail(@PathVariable String userEmail)
    {
        if(userService.fetchUserByEmail(userEmail).isEmpty())
        {
            throw new InputIsNullException("Korisnik ne postoji");
        }

        return ResponseEntity.ok().body(userService.fetchUserByEmail(userEmail).orElseThrow());
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

    @GetMapping("/all")
    public ResponseEntity<List<AppUser>> all(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping
    public ResponseEntity<AppUser> current() {
        return  ResponseEntity.ok(userService.loadCurrentUser());
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        this.request = request;
        this.response = response;
        this.authentication = authentication;
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        response.sendRedirect("http://localhost:3000/"); // Redirect to a success page or URL
    }

    @PostMapping("/create")
    public ResponseEntity<AppUser> newUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.createNew(userDTO));
    }


}
