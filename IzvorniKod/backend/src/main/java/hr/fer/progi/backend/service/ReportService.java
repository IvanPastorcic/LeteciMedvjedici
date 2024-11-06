package hr.fer.progi.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.repository.ReportRepository;

@Service
public class ReportService {
	private final ReportRepository reportRepository;
	
	public ReportService(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}
	
	public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Report newReport(Report report) {
        return reportRepository.save(report);
    }

}
