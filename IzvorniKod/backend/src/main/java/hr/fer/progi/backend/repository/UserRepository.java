package hr.fer.progi.backend.repository;

import hr.fer.progi.backend.model.AppUser;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository {

    @Query("select pm from AppUser pm where pm.id = :id")
    AppUser findAppUserByIdJPQL(Long id);
}
