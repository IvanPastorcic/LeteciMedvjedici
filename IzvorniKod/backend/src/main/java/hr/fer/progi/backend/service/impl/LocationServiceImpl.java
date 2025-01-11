package hr.fer.progi.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.fer.progi.backend.model.Location;
import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.model.Settlement;
import hr.fer.progi.backend.repository.CountyRepository;
import hr.fer.progi.backend.repository.LocationRepository;
import hr.fer.progi.backend.service.LocationService;
import hr.fer.progi.backend.service.ReportService;
import hr.fer.progi.backend.service.SettlementService;

@Service
public class LocationServiceImpl implements LocationService{
	
	
	private final LocationRepository locationRepository;

	public LocationServiceImpl(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}
	
	@Autowired 
	private ReportService reportService;
	
	@Autowired
	private SettlementService settlementService;
	
	
	public List<String> getAllReported(){
		List<Report> allReports = reportService.getAllReports();
		List<String> coordinates = new ArrayList<String>();
		for(Report report : allReports) {
			Long id = report.getId();
			Report rep = reportService.findById(id);
			Settlement s = rep.getDisaster().getSettlement();
			Location location = locationRepository.findBySettlement(s).orElse(null);
			String geoCoor = location.getGeographicalCoordinates();
			
			if(geoCoor.split(",").length == 2)
			coordinates.add(geoCoor);
			
		}
		
		
		return coordinates;
	}
}
