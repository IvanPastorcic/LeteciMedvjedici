package hr.fer.progi.backend.repository;

import hr.fer.progi.backend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long>{

    //provjeriti je li ovo dobar query
    @Query("select pm from AppUser pm where pm.id = :id")
    AppUser findAppUserByIdJPQL(Long id);


    Optional<AppUser> findByEmail(String email);


    List<AppUser> findAll();

<<<<<<< HEAD
    Optional<AppUser> findByUsername(String anonimniKorisnik);
=======
>>>>>>> b189cd5 (Added admin functionalities)
}
