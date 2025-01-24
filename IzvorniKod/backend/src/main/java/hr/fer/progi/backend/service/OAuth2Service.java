package hr.fer.progi.backend.service;

import hr.fer.progi.backend.model.AppUser;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


public interface OAuth2Service  {

    public AppUser processOAuthPostLogin(String email, String name);
    public OidcUser loadUser(OidcUserRequest userRequest);
}
