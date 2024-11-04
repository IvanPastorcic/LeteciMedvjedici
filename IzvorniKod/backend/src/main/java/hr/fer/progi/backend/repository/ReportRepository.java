package hr.fer.progi.backend.repository;

import org.springframework.data.repository.CrudRepository;
import hr.fer.progi.backend.model.Report;

public interface ReportRepository extends CrudRepository<Report, Integer> {

}
