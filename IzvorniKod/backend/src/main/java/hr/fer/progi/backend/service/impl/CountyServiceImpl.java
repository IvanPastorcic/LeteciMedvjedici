package hr.fer.progi.backend.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;
import hr.fer.progi.backend.model.County;
import hr.fer.progi.backend.model.Settlement;
import hr.fer.progi.backend.repository.CountyRepository;
import hr.fer.progi.backend.service.CountyService;

@Service
public class CountyServiceImpl implements CountyService {
	
	private final CountyRepository countyRepository;
	
	public CountyServiceImpl(CountyRepository countyRepository) {
		this.countyRepository = countyRepository;
	}

	@Override
	public County findById(Long id) {
		return countyRepository.findById(id).orElse(null);
	}
	
	@Override
	public County createCounty(County county) {
        return countyRepository.save(county);
    }

	@Override
	public List<County> getAll() {
		return countyRepository.findAll();
	}

}
