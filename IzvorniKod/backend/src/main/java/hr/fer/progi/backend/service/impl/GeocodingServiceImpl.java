package hr.fer.progi.backend.service.impl;

import java.util.Locale;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import hr.fer.progi.backend.dto.CoordinatesDTO;
import hr.fer.progi.backend.dto.SettlementDTO;
import hr.fer.progi.backend.service.GeocodingService;

@Service
public class GeocodingServiceImpl implements GeocodingService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String openCageApiKey = "632780c5cf6c4c00b282a271baad4db9"; 

    public GeocodingServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper();

       
        restTemplate.getInterceptors().add((request, body, execution) -> {
            HttpHeaders headers = request.getHeaders();
            headers.add("User-Agent", "SafeBear/1.0 (tsuki.luna177@email.com)");
            return execution.execute(request, body);
        });
    }

    @Override
    public SettlementDTO reverseGeocode(float latitude, float longitude) {
        // Validate latitude and longitude
        if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException(
                    "Invalid coordinates. Latitude must be between -90 and 90, and longitude must be between -180 and 180.");
        }

        
        String url = String.format(Locale.US,
                "https://api.opencagedata.com/geocode/v1/json?q=%f+%f&key=%s",
                latitude, longitude, openCageApiKey);

        try {
          
            String response = restTemplate.getForObject(url, String.class);

            JsonNode jsonNode = objectMapper.readTree(response);

           
            if (jsonNode.path("results").isEmpty()) {
                throw new RuntimeException("Location not found");
            }

           
            JsonNode firstResult = jsonNode.path("results").get(0);
            JsonNode components = firstResult.path("components");

        
            String settlement = components.path("city").asText();
            if (settlement.isEmpty()) {
                settlement = components.path("town").asText();
            }
            if (settlement.isEmpty()) {
                settlement = components.path("village").asText();
            }

            String county = components.path("county").asText();

         
            return new SettlementDTO(settlement.isEmpty() ? "Location not found" : settlement, county);

        } catch (Exception e) {
            throw new RuntimeException("Failed to reverse geocode coordinates: " + e.getMessage(), e);
        }
    }

    @Override
    public CoordinatesDTO geocode(String settlementName) {
      
        String url = String.format(
                "https://api.opencagedata.com/geocode/v1/json?q=%s&key=%s",
                settlementName + " Hrvatska", openCageApiKey);

        try {
           
            String response = restTemplate.getForObject(url, String.class);

           
            JsonNode jsonNode = objectMapper.readTree(response);

            
            if (jsonNode.path("results").isEmpty()) {
                throw new RuntimeException("Location not found");
            }

            
            JsonNode firstResult = jsonNode.path("results").get(0);
            JsonNode geometry = firstResult.path("geometry");

          
            float latitude = (float) geometry.path("lat").asDouble();
            float longitude = (float) geometry.path("lng").asDouble();

         
            return new CoordinatesDTO(latitude, longitude);

        } catch (Exception e) {
            throw new RuntimeException("Failed to geocode settlement name: " + e.getMessage(), e);
        }
    }
}