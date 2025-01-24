package hr.fer.progi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import hr.fer.progi.backend.model.HumanitarianOrganization;

@Repository
public interface HumanitarianOrganizationRepository extends JpaRepository<HumanitarianOrganization, Long> {

    HumanitarianOrganization findByOrganizationName(String organisation);
}
