package hr.fer.progi.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.progi.backend.model.County;
import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.model.Settlement;
import hr.fer.progi.backend.repository.CountyRepository;
import hr.fer.progi.backend.repository.SettlementRepository;
import hr.fer.progi.backend.repository.exception.InputIsNullException;
import hr.fer.progi.backend.service.CountyService;
import hr.fer.progi.backend.service.LocationService;
import hr.fer.progi.backend.service.SettlementService;

@RestController
@RequestMapping("/location")
public class LocationController {

	@Autowired
	private CountyService countyService;
	
	@Autowired
	private SettlementService settlementService;
	
	@Autowired
	private LocationService locationService;
	
	@GetMapping("/coordinates") 
	List<String> getAllReportedCoordinates(){
		return locationService.getAllReported();
	}
	
	@GetMapping("/settlement")
	List<Settlement> getAllSettlements() {
		return settlementService.getAll();
	}

	@GetMapping("/settlementnames")
	List<String> getAllSettlementNames(){
		List<Settlement> allSettlements = settlementService.getAll();
		return allSettlements.stream()
				.map(Settlement::getSettlementName)
				.collect(Collectors.toList());

	}

	@GetMapping("/county")
	List<County> getAllCounties() {
		return countyService.getAll();
	}
	
	@GetMapping("/settlement/byCounty/{countyID}")
	List<Settlement> getSettlementsByCountyID(@PathVariable("countyID") Long countyID) {
		return settlementService.findByCountyID(countyID);
	}
	
	@GetMapping("/settlement/{id}")
	 public ResponseEntity<Settlement> findById(@PathVariable Long id) {
		Settlement settlement = settlementService.findById(id);
        
        if (settlement == null) {
        	 throw new InputIsNullException("Grad ne postoji");
        }
        
        return ResponseEntity.ok(settlement);
    }
	
	@GetMapping("/county/{id}")
	 public ResponseEntity<County> findCById(@PathVariable Long id) {
		County County = countyService.findById(id);
       
       if (County == null) {
       	 throw new InputIsNullException("Županija ne postoji");
       }
       
       return ResponseEntity.ok(County);
   }
}
