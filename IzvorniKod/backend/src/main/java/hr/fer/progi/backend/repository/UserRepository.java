package hr.fer.progi.backend.repository;

import hr.fer.progi.backend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long>{

    //provjeriti je li ovo dobar query
    @Query("select pm from AppUser pm where pm.id = :id")
    AppUser findAppUserByIdJPQL(Long id);


}
