package hr.fer.progi.backend.repository;

import hr.fer.progi.backend.model.Enum.DisasterType;
import hr.fer.progi.backend.model.Settlement;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.CrudRepository;

import hr.fer.progi.backend.model.NaturalDisaster;

import java.util.Optional;

public interface NaturalDisasterRepository extends CrudRepository<NaturalDisaster, Integer> {


    Optional<NaturalDisaster> findByDisasterTypeAndSettlement(@NotNull DisasterType disasterType, Settlement settlement);
}
