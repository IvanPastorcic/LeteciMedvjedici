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

	public GeocodingServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		this.objectMapper = new ObjectMapper();

		restTemplate.getInterceptors().add((request, body, execution) -> {
			HttpHeaders headers = request.getHeaders();
			headers.add("User-Agent", "SafeBear/1.0 (tsuki.luna177@gmail.com)");
			return execution.execute(request, body);
		});
	}

	// vraća informacije o lokaciji prema koordinatama
	public SettlementDTO reverseGeocode(float latitude, float longitude) {
		// Validate latitude and longitude
		if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
			throw new IllegalArgumentException(
					"Invalid coordinates. Latitude must be between -90 and 90, and longitude must be between -180 and 180.");
		}

		String url = String.format(Locale.US, // Use US locale to ensure period as decimal separator
				"https://nominatim.openstreetmap.org/reverse?format=json&lat=%f&lon=%f&addressdetails=1", latitude,
				longitude);

//        System.out.println("Generated URL: " + url); // Debugging

		try {
	        // Make the API request and get the response as a string
	        String response = restTemplate.getForObject(url, String.class);

	        // Parse the JSON response
	        JsonNode jsonNode = objectMapper.readTree(response);

	        // Extract address node
	        JsonNode addressNode = jsonNode.path("address");

	        // Check for city, town, or village in order of priority
	        String settlement = addressNode.path("village").asText();
	        if (settlement.isEmpty()) {
	            settlement = addressNode.path("town").asText();
	        }
	        if (settlement.isEmpty()) {
	            settlement = addressNode.path("city").asText();
	        }

	        // Extract county
	        String county = addressNode.path("county").asText();

	        // Create and return SettlementDTO object
	        return new SettlementDTO(settlement.isEmpty() ? "Location not found" : settlement, county);

	    } catch (Exception e) {
	        throw new RuntimeException("Failed to reverse geocode coordinates: " + e.getMessage(), e);
	    }
	}

	// vraća koordinate
	public CoordinatesDTO geocode(String settlementName) {
		String url = String.format(
				"https://nominatim.openstreetmap.org/search?format=json&q=%s&addressdetails=1&limit=1",
				settlementName + " Hrvatska");

		  try {
		        // Make the API request and get the response as a string
		        String response = restTemplate.getForObject(url, String.class);

		        // Parse the JSON response (expected to be an array)
		        JsonNode jsonArray = objectMapper.readTree(response);

		        // Ensure there's at least one result
		        if (jsonArray.isEmpty()) {
		            throw new RuntimeException("Location not found");
		        }

		        // Extract the first result
		        JsonNode firstResult = jsonArray.get(0);

		        // Extract latitude and longitude and convert them to float
		        float latitude = Float.parseFloat(firstResult.path("lat").asText());
		        float longitude = Float.parseFloat(firstResult.path("lon").asText());

		        // Return a CoordinatesDTO object
		        return new CoordinatesDTO(latitude, longitude);

		    } catch (Exception e) {
		        throw new RuntimeException("Failed to geocode settlement name: " + e.getMessage(), e);
		    }
	}
}
