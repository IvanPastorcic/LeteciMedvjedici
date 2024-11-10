package hr.fer.progi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.fer.progi.backend.model.Resource;
import hr.fer.progi.backend.model.Embeddable.ResourceId;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, ResourceId> {
	
}
