package hr.fer.progi.backend.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import hr.fer.progi.backend.model.Location;
import hr.fer.progi.backend.model.Settlement;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> { // Promjena String -> Long

    Optional<Location> findBySettlement(Settlement settlement);

    Optional<Location> findByLatitudeAndLongitude(Double latitude, Double longitude); // Nova metoda
}
