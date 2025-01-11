package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.model.AppUser;
import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.repository.ReportRepository;
import hr.fer.progi.backend.repository.UserRepository;
import hr.fer.progi.backend.repository.exception.WrongInputException;
import hr.fer.progi.backend.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
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

    @Override
    public void delete(AppUser appUser) {
        this.userRepository.delete(appUser);

    }

    @Override
    public Optional<AppUser> fetchUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


/*
    public AppUser loadCurrentUser() {


        // Retrieve the OAuth2 token from the SecurityContext
        OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        // Extract the email from the token attributes
        String email = authentication.getPrincipal().getAttribute("email");
        System.out.println(email);

        final String emailToUse = email.isEmpty() ? "Anonimni korisnik" : email;
        // Fetch the AppUser from the database using the email

        //TODO: Dodati anonimnog korisnika u bazu podataka kako bi findByEmail() radilo
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new WrongInputException("User not found with email: " + emailToUse));
    }*/
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

}
