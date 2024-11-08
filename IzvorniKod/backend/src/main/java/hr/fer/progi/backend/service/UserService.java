package hr.fer.progi.backend.service;

import hr.fer.progi.backend.model.AppUser;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    AppUser fetchUserById(Long id);
}
