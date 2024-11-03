package hr.fer.progi.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import hr.fer.progi.backend.service.ReportService;

@RestController
public class ReportController {
	
	private final ReportService reportService;
	
	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}
}
