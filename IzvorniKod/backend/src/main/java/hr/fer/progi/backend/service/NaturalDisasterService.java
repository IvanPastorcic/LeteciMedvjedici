package hr.fer.progi.backend.service;

import org.springframework.stereotype.Service;
import hr.fer.progi.backend.repository.NaturalDisasterRepository;

@Service
public interface NaturalDisasterService {
	private final NaturalDisasterRepository naturalDisasterRepository;
	
	public NaturalDisasterService(NaturalDisasterRepository naturalDisasterRepository) {
		this.naturalDisasterRepository = naturalDisasterRepository;
	}
}
