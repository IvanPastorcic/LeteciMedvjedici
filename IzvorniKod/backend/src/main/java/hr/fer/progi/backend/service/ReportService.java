package hr.fer.progi.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.model.Enum.ReportStatus;
import hr.fer.progi.backend.repository.ReportRepository;

@Service
public interface ReportService {
	
	public List<Report> getAllReports();

    public Report newReport(Report report);

	public Report findById(Long id);

	public List<Report> findByReportStatus(ReportStatus status);
}
