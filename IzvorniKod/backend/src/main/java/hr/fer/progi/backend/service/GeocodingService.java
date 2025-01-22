package hr.fer.progi.backend.service;

import org.springframework.stereotype.Service;

import hr.fer.progi.backend.dto.CoordinatesDTO;
import hr.fer.progi.backend.dto.SettlementDTO;
import hr.fer.progi.backend.model.Settlement;

@Service
public interface GeocodingService {

	 public SettlementDTO reverseGeocode(float latitude, float longitude);
	//vraća informacije o lokaciji prema koordinatama
	 
	 
	 public CoordinatesDTO geocode(String settlementName);
	//vraća koordinate
	 
}
