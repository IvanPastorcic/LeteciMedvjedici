package hr.fer.progi.backend.service;

import hr.fer.progi.backend.model.Enum.DisasterType;
import hr.fer.progi.backend.model.NaturalDisaster;
import hr.fer.progi.backend.model.Settlement;
import org.springframework.stereotype.Service;
import hr.fer.progi.backend.repository.NaturalDisasterRepository;

@Service
public interface NaturalDisasterService  {


    public NaturalDisaster getOrCreateNaturalDisaster(DisasterType disasterType, Settlement settlement);

}
