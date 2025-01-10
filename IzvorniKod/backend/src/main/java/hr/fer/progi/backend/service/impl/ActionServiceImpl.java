package hr.fer.progi.backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import hr.fer.progi.backend.model.Action;
import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.repository.ActionRepository;
import hr.fer.progi.backend.service.ActionService;

@Service
public class ActionServiceImpl implements ActionService{
	
	private final ActionRepository actionRepository;

	public ActionServiceImpl(ActionRepository actionRepository) {
		this.actionRepository = actionRepository;
	}

	@Override
	public List<Action> getAllReports() {
		return actionRepository.findAll();
	}

	@Override
	public Action newAction(Action newAction) {
		return actionRepository.save(newAction);
	}

	@Override
	public Action findById(Long id) {
		return actionRepository.findById(id).orElse(null);
	}

	@Override
	public List<Action> findByName(String nameOfAction) {
		return actionRepository.findByActionName(nameOfAction);
	}

	
}
