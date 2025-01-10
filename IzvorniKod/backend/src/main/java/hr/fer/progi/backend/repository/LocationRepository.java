package hr.fer.progi.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hr.fer.progi.backend.model.Location;
import hr.fer.progi.backend.model.Report;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {
	
	@Query("select geographical_coordinates from location natural join report")
	List<String> findReported();
}