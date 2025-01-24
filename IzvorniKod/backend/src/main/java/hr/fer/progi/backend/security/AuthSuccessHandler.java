package hr.fer.progi.backend.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Check user roles and redirect accordingly
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            response.sendRedirect("https://safebear.onrender.com/homeadmin");
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_AUTHORITY"))) {
            response.sendRedirect("https://safebear.onrender.com/homeauthorities");
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_HUMANITARIAN"))) {
            response.sendRedirect("https://safebear.onrender.com/homehumanitarian");
        }else {
            // Default redirect for other users
            response.sendRedirect("https://safebear.onrender.com/home");
        }
    }
}
