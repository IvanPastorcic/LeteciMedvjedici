package hr.fer.progi.backend.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/").permitAll()
                        //Ovdje napisi endpointe koji su dostupni svima
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2Login -> oauth2Login
                                .defaultSuccessUrl("/secured", true)
                                //ako je uspjela prijava prebaci se na ovaj endpoint
                                .failureUrl("/"));
                        //ako nije uspjela prijava prebaci se na ovaj endpoint);

        return http.build();
    }
}
