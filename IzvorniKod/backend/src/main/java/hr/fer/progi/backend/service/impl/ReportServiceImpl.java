package hr.fer.progi.backend.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.model.Enum.ReportStatus;
import hr.fer.progi.backend.repository.ReportRepository;
import hr.fer.progi.backend.service.ReportService;

@Component
public class ReportServiceImpl implements ReportService {
	
	private final ReportRepository reportRepository;
	
	public ReportServiceImpl(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}
	
	@Override
	public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

	@Override
    public Report newReport(Report report) {
        return reportRepository.save(report);
    }

	 public Report findById(Long id) {
	        return reportRepository.findById(id).orElse(null);
	    }

	@Override
	public List<Report> findByReportStatus(ReportStatus status) {
		return reportRepository.findByReportStatus(status);
	}

	
}
