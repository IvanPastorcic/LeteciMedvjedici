package hr.fer.progi.backend.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import hr.fer.progi.backend.service.OAuth2Service;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.http.SessionCreationPolicy;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)

public class SecurityConfig {


    private final OAuth2Service oAuth2Service;

    public SecurityConfig(OAuth2Service oAuth2Service) {
        this.oAuth2Service = oAuth2Service;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/",
                            "/reports/{id}", "/reports", "reports/add",
                            "/location/settlementnames",
                            "/actions", "/actions/{id}", "/actions/actionName/{nameOfAction}",
                            "/user/**", //provjeri sa ostalima
                            "/needs/{id}"
                                ).permitAll();
                    authorize.anyRequest().authenticated();
                })
                /*.oauth2Login(oauth2Login -> oauth2Login
                        .defaultSuccessUrl("http://localhost:3000/home", true)
                        .userInfoEndpoint(userInfoEndpoint ->
                                userInfoEndpoint
                                        .oidcUserService((OAuth2UserService) oAuth2Service)
                        )
                )*/
                .oauth2Login(oauth2Login -> oauth2Login
                        .successHandler(successHandler())  // Use the custom success handler
                        .userInfoEndpoint(userInfoEndpoint ->
                                userInfoEndpoint.oidcUserService((OAuth2UserService) oAuth2Service)
                        ))
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                );
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthSuccessHandler successHandler() {
        return new AuthSuccessHandler();
    }
}