package hr.fer.progi.backend.controller;


import hr.fer.progi.backend.model.AppUser;
import hr.fer.progi.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AppUserController {

    private final UserService userService;

    public AppUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long userId)
    {
        if(userService.fetchUserById(userId) == null)
        {
            throw new InputIsNullException("Korisnik ne postoji");
        }
        return ResponseEntity.ok().body(UserMapper.INSTANCE.toDto(userService.fetchUserById(userId)));
    }
}
