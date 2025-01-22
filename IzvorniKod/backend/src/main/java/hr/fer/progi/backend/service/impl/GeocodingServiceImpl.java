package hr.fer.progi.backend.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import hr.fer.progi.backend.service.GeocodingService;

@Service
public class GeocodingServiceImpl implements GeocodingService{

    private final RestTemplate restTemplate;

    public GeocodingServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    //vraća informacije o lokaciji prema koordinatama
    public String reverseGeocode(double latitude, double longitude) {
        String url = String.format(
            "https://nominatim.openstreetmap.org/reverse?format=json&lat=%f&lon=%f&addressdetails=1",
            latitude, longitude
        );

        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to reverse geocode coordinates: " + e.getMessage(), e);
        }
    }
    
    //vraća koordinate
    public String geocode(String settlementName) {
    	 String url = String.format(
    		        "https://nominatim.openstreetmap.org/search?format=json&q=%s&addressdetails=1&limit=1",
    		        settlementName + " Hrvatska" 
    		    );

        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to geocode settlement name: " + e.getMessage(), e);
        }
    }
}
