package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.model.AppUser;
import hr.fer.progi.backend.model.Enum.Role;
import hr.fer.progi.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OAuth2ServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OAuth2ServiceImpl oAuth2Service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void processOAuthPostLogin_ValidUser() {
        // Redovan slučaj
        String email = "email@example.com";
        String name = "John Doe";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(userRepository.save(any(AppUser.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AppUser result = oAuth2Service.processOAuthPostLogin(email, name);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
        assertEquals(name, result.getUsername());
        assertEquals(Role.ROLE_USER, result.getRole());
    }

    @Test
    void processOAuthPostLogin_InvalidEmail() {
        // Izazivanje pogreške
        String invalidEmail = "invalid-email";
        String name = "John Doe";

        assertThrows(IllegalArgumentException.class, () -> oAuth2Service.processOAuthPostLogin(invalidEmail, name));
    }

    @Test
    void processOAuthPostLogin_MaxLengthName() {
        // Rubni uvjet: Maksimalna duljina imena
        String email = "email@example.com";
        String name = "A".repeat(50);

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(userRepository.save(any(AppUser.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AppUser result = oAuth2Service.processOAuthPostLogin(email, name);

        assertNotNull(result);
        assertEquals(name, result.getUsername());
    }

    @Test
    void processOAuthPostLogin_TooLongName() {
        // Rubni uvjet: Ime dulje od 50 znakova
        String email = "email@example.com";
        String name = "A".repeat(51);

        assertThrows(IllegalArgumentException.class, () -> oAuth2Service.processOAuthPostLogin(email, name));
    }

    @Test
    void processOAuthPostLogin_UnknownField() {
        // Nepostojeće polje "username"
        String username = "unknown_user";
        String email = "email@example.com";
        String name = "John Doe";

        // Ovo simulira situaciju gdje se koristi nepostojeće polje
        assertThrows(UnsupportedOperationException.class, () -> {
            // Poziv metode koja nije predviđena za obradu "username" polja
            oAuth2Service.processOAuthPostLogin(email, name);
            // Ovo će u stvarnoj implementaciji baciti grešku jer "username" nije podržano
        });
    }

}
