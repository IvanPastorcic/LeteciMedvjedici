package hr.fer.progi.backend.controller;

import org.springframework.web.bind.annotation.*;

import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.repository.ReportRepository;
import hr.fer.progi.backend.service.ReportService;

import java.util.List;


@RestController
@RequestMapping("/reports")
public class ReportController {
	
	private final ReportService reportService;
	
	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}
	
	@GetMapping("/all")
	List<Report> reports() {
		return reportService.getAllReports();
	}
	
	@PostMapping("/add")
	Report newReport(@RequestBody Report newReport){
		return reportService.newReport(newReport);
	}
	
	

	
}
