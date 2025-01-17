package hr.fer.progi.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import hr.fer.progi.backend.model.Enum.DisasterType;

@Service
public interface LocationService {

	public List<String> getAllReported();

	public List<String> getCoordinatesByType(DisasterType disasterType);

}
