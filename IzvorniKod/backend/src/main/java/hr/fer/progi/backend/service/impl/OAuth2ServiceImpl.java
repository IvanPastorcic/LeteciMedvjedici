package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.model.AppUser;
import hr.fer.progi.backend.model.Enum.Role;
import hr.fer.progi.backend.repository.UserRepository;
import hr.fer.progi.backend.service.OAuth2Service;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class OAuth2ServiceImpl implements OAuth2Service, OAuth2UserService<OidcUserRequest, OidcUser>{

    private final UserRepository userRepository;

    public OAuth2ServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public AppUser processOAuthPostLogin(String email, String name) {
        System.out.println("korisnik:" + email + name);
        // Check if user already exists
        return userRepository.findByEmail(email).orElseGet(() -> {
            // If not, create a new user
            AppUser newUser = new AppUser();
            newUser.setUsername(name);
            newUser.setEmail(email);
            newUser.setRole(Role.ROLE_USER);
            return userRepository.save(newUser);
        });
    }

    @Override
    @Transactional
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        // Create an OidcUser instance with the token and empty authorities
        OidcUser oidcUser = new DefaultOidcUser(
                Collections.emptyList(), //prazna lista roleova
                userRequest.getIdToken()
        );

        // Extract the email and name attributes
        String email = (String) oidcUser.getAttribute("email");
        String name = (String) oidcUser.getAttribute("name");

        AppUser appUser = processOAuthPostLogin(email, name);

        // Map roles from the database to GrantedAuthority objects
        GrantedAuthority authority = new SimpleGrantedAuthority(appUser.getRole().name());

        //POTENCIJALNA GREŠKA
        return new DefaultOidcUser((Collection<? extends GrantedAuthority>) Collections.singletonList(authority), userRequest.getIdToken(), "email");

    }


}
