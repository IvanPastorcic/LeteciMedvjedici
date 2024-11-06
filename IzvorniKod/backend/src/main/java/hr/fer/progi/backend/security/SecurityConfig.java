package hr.fer.progi.backend.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = false)
public class SecurityConfig {


    //bean prepisan sa croz prezentacije vise manje
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login**", "/oauth2/**").permitAll()
                        //Ovdje napisi endpointe koji su dostupni svima
                        .anyRequest().authenticated()
                );

/* TODO: provjeriti kako ovo napisati bolje. trazi me da imam repo za autorizaciju*//*
                .oauth2Login(oauth2Login -> oauth2Login
                        .defaultSuccessUrl("/login/success", true)
                        //ako je uspjela prijava prebaci se na ovaj endpoint
                        .failureUrl("/login/failure")
                        //ako nije uspjela prijava prebaci se na ovaj endpoint
                );*/
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
