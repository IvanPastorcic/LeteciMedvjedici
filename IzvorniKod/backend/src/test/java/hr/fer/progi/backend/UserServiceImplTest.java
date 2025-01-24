package hr.fer.progi.backend.service.impl;
import hr.fer.progi.backend.model.Enum.DisasterType;

import hr.fer.progi.backend.model.AppUser;
import hr.fer.progi.backend.repository.UserRepository;
import hr.fer.progi.backend.repository.exception.WrongInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import hr.fer.progi.backend.model.Enum.ReportStatus;


class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadCurrentUser_ValidOAuthUser() {
        // Redovan slučaj
        String email = "email@example.com";
        AppUser mockUser = new AppUser();
        mockUser.setEmail(email);

        // Simulacija autentifikacije
        OAuth2AuthenticationToken mockAuth = mock(OAuth2AuthenticationToken.class);
        OAuth2User mockUserPrincipal = mock(OAuth2User.class);
        when(mockUserPrincipal.getAttribute("email")).thenReturn(email);
        when(mockAuth.getPrincipal()).thenReturn(mockUserPrincipal);
        SecurityContextHolder.getContext().setAuthentication(mockAuth);

        // Mockiranje baze podataka
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));

        // Poziv metode
        AppUser result = userService.loadCurrentUser();

        // Provjera rezultata
        assertNotNull(result);
        assertEquals(email, result.getEmail());
    }

    @Test
    void loadCurrentUser_AnonymousUser() {
        // Nepostojeća funkcionalnost
        String email = "Anonimni korisnik";

        // Simulacija anonimnog korisnika
        SecurityContextHolder.clearContext();
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Provjera iznimke
        assertThrows(WrongInputException.class, userService::loadCurrentUser);
    }

    @Test
    void loadCurrentUser_UserNotFound() {
        // Izazivanje pogreške
        String email = "unknown@example.com";

        // Simulacija autentifikacije
        OAuth2AuthenticationToken mockAuth = mock(OAuth2AuthenticationToken.class);
        OAuth2User mockUserPrincipal = mock(OAuth2User.class);
        when(mockUserPrincipal.getAttribute("email")).thenReturn(email);
        when(mockAuth.getPrincipal()).thenReturn(mockUserPrincipal);
        SecurityContextHolder.getContext().setAuthentication(mockAuth);

        // Mockiranje baze podataka
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Provjera iznimke
        assertThrows(WrongInputException.class, userService::loadCurrentUser);
    }

    @Test
    void loadCurrentUser_BorderlineEmail() {
        // Rubni uvjet
        String email = "u@x.com";
        AppUser mockUser = new AppUser();
        mockUser.setEmail(email);

        // Simulacija autentifikacije
        OAuth2AuthenticationToken mockAuth = mock(OAuth2AuthenticationToken.class);
        OAuth2User mockUserPrincipal = mock(OAuth2User.class);
        when(mockUserPrincipal.getAttribute("email")).thenReturn(email);
        when(mockAuth.getPrincipal()).thenReturn(mockUserPrincipal);
        SecurityContextHolder.getContext().setAuthentication(mockAuth);

        // Mockiranje baze podataka
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));

        // Poziv metode
        AppUser result = userService.loadCurrentUser();

        // Provjera rezultata
        assertNotNull(result);
        assertEquals(email, result.getEmail());
    }
}
