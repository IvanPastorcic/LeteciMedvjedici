package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.model.AppUser;
import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.repository.ReportRepository;
import hr.fer.progi.backend.repository.UserRepository;
import hr.fer.progi.backend.repository.exception.WrongInputException;
import hr.fer.progi.backend.service.UserService;
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



    public AppUser loadCurrentUser() {
        // Retrieve the OAuth2 token from the SecurityContext
        OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        // Extract the email from the token attributes
        String email = authentication.getPrincipal().getAttribute("email");

        // Fetch the AppUser from the database using the email
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new WrongInputException("User not found with email: " + email));
    }
}
