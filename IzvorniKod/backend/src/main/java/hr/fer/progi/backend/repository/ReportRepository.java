package hr.fer.progi.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.model.Enum.ReportStatus;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
	
	Optional<Report> findById(Long Id);
	
	List<Report> findByReportStatus(ReportStatus status);


	@Query("select f from Report f where f.appUser.id = :userId")
	List<Report> findByUserIdJPQL(Long userId);

    List<Report> findAllByReportStatus(ReportStatus status);


    void deleteByAppUserId(Long id);

	List<Report> findByAppUserId(Long id);
}