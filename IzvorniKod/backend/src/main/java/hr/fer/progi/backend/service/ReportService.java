package hr.fer.progi.backend.service;

import java.util.List;
import org.springframework.stereotype.Service;

import hr.fer.progi.backend.dto.ReportDTO;
import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.model.Enum.ReportStatus;

@Service
public interface ReportService {
	
	public List<Report> getAllReports();
	// returns List of all reports

    public Report newReport(ReportDTO reportDTO);
    // adds new report

	public Report findById(Long id);
	// returns report with matching id

	public List<Report> findByReportStatus(ReportStatus status);
	// returns report with matching reportStatus

	public Report deleteById(Long id);
	// deletes report with matching id
}
