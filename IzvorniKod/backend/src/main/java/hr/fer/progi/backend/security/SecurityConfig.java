package hr.fer.progi.backend.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> {authorize
                        .requestMatchers("/").permitAll();
                        //Ovdje napisi endpointe koji su dostupni svima
                        authorize.anyRequest().authenticated();})
                .oauth2Login(Customizer.withDefaults());
                        //ako nije uspjela prijava prebaci se na ovaj endpoint);



        System.out.println("aaaa");

        return http.build();

    }
}
