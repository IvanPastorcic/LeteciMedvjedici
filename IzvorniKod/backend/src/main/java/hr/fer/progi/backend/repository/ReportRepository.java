package hr.fer.progi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import hr.fer.progi.backend.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

}