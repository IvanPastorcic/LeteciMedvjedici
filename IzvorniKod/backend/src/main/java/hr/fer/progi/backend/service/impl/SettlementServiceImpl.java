package hr.fer.progi.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.fer.progi.backend.model.Settlement;
import hr.fer.progi.backend.repository.SettlementRepository;
import hr.fer.progi.backend.service.SettlementService;

@Service
public class SettlementServiceImpl implements SettlementService{

	@Autowired
	private SettlementRepository settlementRepository;

	public Settlement createSettlement(Settlement settlement) {
		return settlementRepository.save(settlement);
	}

	@Override
	public List<Settlement> getAll() {
		return settlementRepository.findAll();
	}

	@Override
	public List<Settlement> findByCountyID(Long id) {
		return settlementRepository.findByCountyId(id);
	}

	@Override
	public Settlement findById(Long id) {
		return settlementRepository.findById(id).orElse(null);
	}

	

}
