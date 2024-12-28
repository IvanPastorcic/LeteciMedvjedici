package hr.fer.progi.backend.service;


import java.util.List;

import org.springframework.stereotype.Service;
import hr.fer.progi.backend.model.County;
import hr.fer.progi.backend.model.Settlement;

@Service
public interface CountyService {

	public County findById(Long id); 
	
	public County createCounty(County county);

	public List<County> getAll();
  
}
