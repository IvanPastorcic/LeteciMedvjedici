package hr.fer.progi.backend.service;

import org.springframework.stereotype.Service;

import hr.fer.progi.backend.model.Settlement;

@Service
public interface SettlementService {

	public Settlement createSettlement(Settlement settlement);
}
