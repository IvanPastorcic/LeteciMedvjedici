package hr.fer.progi.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;  // Correct import
import hr.fer.progi.backend.service.OAuth2Service;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.util.List;

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
                    authorize
                            .requestMatchers("/", "/reports/add", "/reports", "/location/settlementnames").permitAll()
                            .requestMatchers("/login/oauth2/code/google").permitAll() // Permit OAuth2 callback
                            .requestMatchers("/**").permitAll() // Ensure preflight (OPTIONS) requests are handled
                            .anyRequest().authenticated();
                })
                .oauth2Login(oauth2Login -> oauth2Login
                        .defaultSuccessUrl("https://safebear.onrender.com/home", true)
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                                .oidcUserService((OAuth2UserService<OidcUserRequest, OidcUser>) oAuth2Service) // Use OAuth2Service as the OIDC user service
                        )
                )
                .sessionManagement(sessionManagement -> 
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                .csrf(AbstractHttpConfigurer::disable) // Disabling CSRF (if needed)
                .cors(cors -> cors.configurationSource(corsConfigurationSource())); // Enable CORS here

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("https://safebear.onrender.com")); // Frontend URL
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allowed HTTP methods
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type")); // Allowed headers
        configuration.setAllowCredentials(true); // Allow credentials for cookies

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply configuration globally
        return source;
    }

    // Optional: Add a method to check and log the authentication type (for debugging purposes)
    private void logAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            System.out.println("Authentication token: " + authentication.getClass().getName());
        }
    }
}
