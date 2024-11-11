package hr.fer.progi.backend.service.impl;

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

}
