package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.service.OAuth2Service;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class OAuth2ServiceImpl implements OAuth2Service {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
      /*  OAuth2User oAuth2User = OAuth2UserRequest.loadUser(userRequest);

        // Process and save the user info here, e.g., save to a database
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        // Perform custom user registration logic here

        return oAuth2User;*/
        return null;
    }
}
