package hr.fer.progi.backend.repository;

import hr.fer.progi.backend.model.HumanitarianOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hr.fer.progi.backend.model.Resource;
import hr.fer.progi.backend.model.Embeddable.ResourceId;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, ResourceId> {
    List<Resource> findAll();



    Optional<Resource> findById(ResourceId id);

    List<Resource> findByHumanitarianOrganization(HumanitarianOrganization username);
}
