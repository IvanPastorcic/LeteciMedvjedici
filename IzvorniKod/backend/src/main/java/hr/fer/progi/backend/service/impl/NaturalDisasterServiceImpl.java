package hr.fer.progi.backend.service.impl;

import hr.fer.progi.backend.model.Enum.DisasterType;
import hr.fer.progi.backend.model.NaturalDisaster;
import hr.fer.progi.backend.model.Settlement;
import hr.fer.progi.backend.repository.NaturalDisasterRepository;
import hr.fer.progi.backend.service.NaturalDisasterService;
import org.springframework.stereotype.Service;

@Service
public class NaturalDisasterServiceImpl implements NaturalDisasterService {

    private final NaturalDisasterRepository naturalDisasterRepository;

    public NaturalDisasterServiceImpl(NaturalDisasterRepository naturalDisasterRepository) {
        this.naturalDisasterRepository = naturalDisasterRepository;
    }


    @Override
    public NaturalDisaster getOrCreateNaturalDisaster(DisasterType disasterType, Settlement settlement) {
        return naturalDisasterRepository.findByDisasterTypeAndSettlement(disasterType, settlement)
                .orElseGet(() -> {
                    NaturalDisaster newDisaster = new NaturalDisaster(disasterType, settlement);
                    return naturalDisasterRepository.save(newDisaster);
                });
    }
}
