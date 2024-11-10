package hr.fer.progi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.fer.progi.backend.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {}