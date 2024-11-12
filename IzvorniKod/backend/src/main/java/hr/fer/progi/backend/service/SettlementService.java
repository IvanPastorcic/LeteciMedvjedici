package hr.fer.progi.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import hr.fer.progi.backend.model.Settlement;

@Service
public interface SettlementService {

	public Settlement createSettlement(Settlement settlement);

	public List<Settlement> getAll();

	public List<Settlement> findByCountyID(Long id);
	
	public Settlement findById(Long id);


}
