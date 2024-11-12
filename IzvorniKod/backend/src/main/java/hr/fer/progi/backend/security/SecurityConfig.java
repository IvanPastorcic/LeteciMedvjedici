package hr.fer.progi.backend.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import hr.fer.progi.backend.service.OAuth2Service;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final OAuth2Service oAuth2Service;

    public SecurityConfig(OAuth2Service oAuth2Service) {
        this.oAuth2Service = oAuth2Service;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/", "/reports/add").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .oauth2Login(oauth2Login -> oauth2Login
                        .defaultSuccessUrl("/user/home", true)
                        .userInfoEndpoint(userInfoEndpoint ->
                                userInfoEndpoint
                                        .oidcUserService((OAuth2UserService) oAuth2Service)
                ));
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();

    }
}
