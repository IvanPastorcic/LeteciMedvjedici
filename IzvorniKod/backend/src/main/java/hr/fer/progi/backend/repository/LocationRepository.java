package hr.fer.progi.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hr.fer.progi.backend.model.Location;
import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.model.Settlement;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {
	Optional<Location> findBySettlement(Settlement settlement);
}