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
                    authorize.requestMatchers("/", "/reports/add", "/reports", "/location/settlementnames").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .oauth2Login(oauth2Login -> oauth2Login
                        .defaultSuccessUrl("https://safebear.onrender.com/home", true)  // Update this for production
                        .userInfoEndpoint(userInfoEndpoint -> 
                                userInfoEndpoint
                                        .oidcUserService(oAuth2Service)  // No need for casting to OAuth2UserService
                        )
                )
                .sessionManagement(sessionManagement -> 
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                );
        http.csrf(AbstractHttpConfigurer::disable);  // Disable CSRF for API endpoints
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));  // Enable CORS for allowed origins

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Update this with your frontend URL for production
        configuration.setAllowedOrigins(List.of("https://safebear.onrender.com"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
