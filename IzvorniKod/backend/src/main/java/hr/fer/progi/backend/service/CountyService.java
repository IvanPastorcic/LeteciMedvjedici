package hr.fer.progi.backend.service;


import org.springframework.stereotype.Service;
import hr.fer.progi.backend.model.County;

@Service
public interface CountyService {

	public County findById(Long id); 
	
	public County createCounty(County county);
  
}
