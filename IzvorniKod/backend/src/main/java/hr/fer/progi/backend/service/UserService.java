package hr.fer.progi.backend.service;

import hr.fer.progi.backend.dto.UserDTO;
import hr.fer.progi.backend.model.AppUser;
import hr.fer.progi.backend.model.Report;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    AppUser fetchUserById(Long id);

    List<Report> fetchReportsByUser(Long userId);

    Optional<AppUser> findById(Long id);

    void delete(AppUser appUser);

    Optional<AppUser> fetchUserByEmail(String email);

    AppUser loadCurrentUser();

    List<AppUser> findAllUsers();

    AppUser createNew(UserDTO userDTO);


    // AppUser loadCurrentUser();
}
