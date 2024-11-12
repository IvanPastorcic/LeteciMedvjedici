package hr.fer.progi.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.progi.backend.model.County;
import hr.fer.progi.backend.model.Settlement;
import hr.fer.progi.backend.repository.CountyRepository;
import hr.fer.progi.backend.repository.SettlementRepository;
import hr.fer.progi.backend.service.CountyService;
import hr.fer.progi.backend.service.SettlementService;

@RestController
@RequestMapping("/location")
public class LocationController {

	@Autowired
	private CountyService countyService;
	
	@Autowired
	private SettlementService settlementService;
	
	@GetMapping("/settlement")
	List<Settlement> getAllSettlements() {
		return settlementService.getAll();
	}
	
	@GetMapping("/county")
	List<County> getAllCounties() {
		return countyService.getAll();
	}
	
	@GetMapping("/settlement/{countyID}")
	List<Settlement> getSettlementsByCountyID(@PathVariable("countyID") Long countyID) {
		return settlementService.findByCountyID(countyID);
	}
}
