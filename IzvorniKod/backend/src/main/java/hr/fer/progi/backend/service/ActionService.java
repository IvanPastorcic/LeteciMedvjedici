package hr.fer.progi.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import hr.fer.progi.backend.model.Action;
import hr.fer.progi.backend.model.Report;

@Service
public interface ActionService {

	List<Action> getAllReports();

	Action newAction(Action newAction);

	Action findById(Long id);

	List<Action> findByName(String nameOfAction);

}
