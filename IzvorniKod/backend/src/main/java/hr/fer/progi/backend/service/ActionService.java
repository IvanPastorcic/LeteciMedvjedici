package hr.fer.progi.backend.service;

import java.util.List;

import hr.fer.progi.backend.dto.ActionDTO;
import org.springframework.stereotype.Service;

import hr.fer.progi.backend.model.Action;
import hr.fer.progi.backend.model.Report;

@Service
public interface ActionService {

	List<Action> getAllActions();

	Action newAction(ActionDTO newAction);

	Action findById(Long id);

	List<Action> findByName(String nameOfAction);

}
