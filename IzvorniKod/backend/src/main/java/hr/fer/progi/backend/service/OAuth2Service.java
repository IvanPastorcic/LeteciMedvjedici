package hr.fer.progi.backend.service;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public interface OAuth2Service {

    public OAuth2User loadUser(OAuth2UserRequest userRequest);
}
