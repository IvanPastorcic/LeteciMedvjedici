package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.dto.UserDTO;
import hr.fer.progi.backend.dto.UsernameDTO;
import hr.fer.progi.backend.model.AppUser;
import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.repository.ReportRepository;
import hr.fer.progi.backend.repository.UserRepository;
import hr.fer.progi.backend.repository.exception.WrongInputException;
import hr.fer.progi.backend.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ReportRepository reportRepository;

    public UserServiceImpl(UserRepository userRepository, ReportRepository reportRepository) {
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
    }

    @Override
    public AppUser fetchUserById(Long id) {
        return userRepository.findAppUserByIdJPQL(id);
    }

    public Optional<AppUser> findById(Long id){
        return userRepository.findById(id);
    }
    @Override
    public List<Report> fetchReportsByUser(Long userId) {
        return reportRepository.findByUserIdJPQL(userId);
    }

    @Transactional
    public void delete(AppUser appUser) {
        // Find the anonymous user
        Optional<AppUser> anonymousUserOpt = userRepository.findByUsername("Anonimni korisnik");

        if (anonymousUserOpt.isPresent()) {
            AppUser anonymousUser = anonymousUserOpt.get();

            // Find all reports associated with the user to be deleted
            List<Report> reports = reportRepository.findByAppUserId(appUser.getId());

            for (Report report : reports) {
                report.setUser(anonymousUser);  // Reassign report to anonymous user
                reportRepository.save(report);  // Save the updated report
            }

            // Now delete the original user
            userRepository.delete(appUser);
        } else {
            throw new IllegalStateException("Anonimni korisnik not found!");
        }
    }




    @Override
    public Optional<AppUser> fetchUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }



public AppUser loadCurrentUser() {
    // Retrieve the authentication object from the SecurityContext
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    // Check if the user is authenticated
    if (authentication instanceof OAuth2AuthenticationToken) {
        // The user is authenticated via OAuth2
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        String email = oauthToken.getPrincipal().getAttribute("email");

        // Fetch the user from the database using their email
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new WrongInputException("User not found with email: " + email));
    } else {
        // The user is not authenticated (anonymous user)
        return userRepository.findByEmail("Anonimni korisnik")
                .orElseThrow(() -> new WrongInputException("Anonymous user not found in the database."));
    }
}

    @Override
    public List<AppUser> findAllUsers() {
        return userRepository.findAll();
    }

<<<<<<< HEAD
    @Override
    public AppUser createNew(UserDTO userDTO) {

        AppUser user = new AppUser(userDTO.getEmail(), userDTO.getUsername(), userDTO.getRole() );

        return userRepository.save(user);
    }

    @Override
    public AppUser editUserName(UsernameDTO username) {
        AppUser user = loadCurrentUser();
        user.setUsername(username.getUsername());
        return userRepository.save(user);
    }


=======
>>>>>>> b189cd5 (Added admin functionalities)
}
