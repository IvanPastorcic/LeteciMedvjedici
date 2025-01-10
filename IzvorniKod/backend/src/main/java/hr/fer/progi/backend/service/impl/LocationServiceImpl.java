package hr.fer.progi.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.repository.CountyRepository;
import hr.fer.progi.backend.repository.LocationRepository;
import hr.fer.progi.backend.service.ReportService;

@Service
public class LocationServiceImpl {
	
	
	private final LocationRepository locationRepository;

	public LocationServiceImpl(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}
	
	@Autowired
	private ReportService reportService;
	
	List<String> getAllReported(){
		List<String> coordinates = locationRepository.findReported();
		return coordinates;
	}
}
