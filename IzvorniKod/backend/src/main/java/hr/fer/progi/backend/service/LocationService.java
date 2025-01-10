package hr.fer.progi.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface LocationService {

	public List<String> getAllReported();

}
