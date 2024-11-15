package hr.fer.progi.backend.controller;

import hr.fer.progi.backend.model.Enum.DisasterType;
import org.springframework.web.bind.annotation.*;

import hr.fer.progi.backend.dto.ReportDTO;
import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.model.Enum.ReportStatus;
import hr.fer.progi.backend.repository.exception.InputIsNullException;
import hr.fer.progi.backend.service.ReportService;


import java.util.List;

import org.springframework.http.ResponseEntity;


@RestController
@CrossOrigin
@RequestMapping("/reports")
public class ReportController {
	
	private final ReportService reportService;
	
	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}
	
	//get list of all reports
	@GetMapping
	public ResponseEntity<List<Report>> reports() {
		return ResponseEntity.ok(reportService.getAllReports());
	}
	
	//add new report
	@PostMapping("/add")
	public ResponseEntity<Report> newReport(@RequestBody ReportDTO dto){
		//ReportDTO dto = new ReportDTO(settlementName, disasterType, shortDescription, "");
		System.out.println(dto.getDisasterType());

		Report report = reportService.newReport(dto);
		return ResponseEntity.ok(report);
	}
	
	//get report with matching id
	@GetMapping("/{id}") 
	 public ResponseEntity<Report> findById(@PathVariable Long id) {
        Report report = reportService.findById(id);
        
        if (report == null) {
        	 throw new InputIsNullException("Prijava ne postoji");
        }
        
        return ResponseEntity.ok(report);
    }
	
	//get report with matching reportStatus
	@GetMapping("/status/{status}") 
	 public ResponseEntity<List<Report>> filterByStatus(@PathVariable String status) { // mislim da je moglo i bez responseentity ovdje jer u najgorem slucaju vraca praznu listu
		ReportStatus rstatus;
		if(status.equalsIgnoreCase("accepted")) {
			rstatus = ReportStatus.ACCEPTED;
		} else if(status.equalsIgnoreCase("proccesing")) {
			rstatus = ReportStatus.PROCESSING;
		} else {
			rstatus = ReportStatus.DENIED;
		}
		List<Report> reports = reportService.findByReportStatus(rstatus);
       
       if (reports == null) {
           return ResponseEntity.notFound().build(); //404
       }
       
       return ResponseEntity.ok(reports);
   }
	
	//delete report
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<Report> deleteById(@PathVariable Long id) {
        Report report = reportService.deleteById(id);
        
        if (report == null) {
        	 throw new InputIsNullException("Prijava ne postoji");
        }
        
        return ResponseEntity.ok(report);
    }
	
	
	
	
}
