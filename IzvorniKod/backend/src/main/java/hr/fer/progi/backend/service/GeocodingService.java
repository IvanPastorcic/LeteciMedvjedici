package hr.fer.progi.backend.service;

import org.springframework.stereotype.Service;

@Service
public interface GeocodingService {

	 public String reverseGeocode(double latitude, double longitude);
	//vraća informacije o lokaciji prema koordinatama
	 
	 
	 public String geocode(String settlementName);
	//vraća koordinate
	 
}
