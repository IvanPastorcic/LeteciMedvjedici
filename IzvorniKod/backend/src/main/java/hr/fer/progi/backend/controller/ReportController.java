package hr.fer.progi.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.model.Enum.ReportStatus;
import hr.fer.progi.backend.repository.exception.InputIsNullException;
import hr.fer.progi.backend.service.ReportService;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/reports")
public class ReportController {
	
	private final ReportService reportService;
	
	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}
	
	//get list of all reports
	@GetMapping
	List<Report> reports() {
		return reportService.getAllReports();
	}
	
	//add new report
	@PostMapping("/add")
	Report newReport(@RequestBody Report newReport){
		return reportService.newReport(newReport);
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
