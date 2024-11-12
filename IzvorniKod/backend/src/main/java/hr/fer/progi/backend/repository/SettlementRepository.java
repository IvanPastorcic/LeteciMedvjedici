package hr.fer.progi.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hr.fer.progi.backend.model.Settlement;


@Repository
public interface SettlementRepository extends JpaRepository<Settlement, Long> {
	
	 @Query("SELECT s FROM Settlement s WHERE s.county.id = :countyId")
	 List<Settlement> findByCountyId(Long countyId);
	
}
