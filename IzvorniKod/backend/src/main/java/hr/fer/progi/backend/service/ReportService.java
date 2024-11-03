package hr.fer.progi.backend.service;

import org.springframework.stereotype.Service;
import hr.fer.progi.backend.repository.ReportRepository;

@Service
public class ReportService {
	private final ReportRepository reportRepository;
	
	public ReportService(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}
}
