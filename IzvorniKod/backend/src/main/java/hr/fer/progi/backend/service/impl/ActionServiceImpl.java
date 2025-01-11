package hr.fer.progi.backend.service.impl;

import java.util.List;

import hr.fer.progi.backend.dto.ActionDTO;
import hr.fer.progi.backend.model.AppUser;
import hr.fer.progi.backend.model.HumanitarianOrganization;
import hr.fer.progi.backend.model.Settlement;
import hr.fer.progi.backend.repository.HumanitarianOrganizationRepository;
import hr.fer.progi.backend.repository.SettlementRepository;
import hr.fer.progi.backend.service.UserService;
import org.springframework.stereotype.Service;

import hr.fer.progi.backend.model.Action;
import hr.fer.progi.backend.repository.ActionRepository;
import hr.fer.progi.backend.service.ActionService;

@Service
public class ActionServiceImpl implements ActionService{
	
	private final ActionRepository actionRepository;
	private final UserService userService;
	private final SettlementRepository settlementRepository;
	private final HumanitarianOrganizationRepository humanitarianOrganizationRepository;

	public ActionServiceImpl(ActionRepository actionRepository, UserService userService, SettlementRepository settlementRepository, HumanitarianOrganizationRepository humanitarianOrganizationRepository) {
		this.actionRepository = actionRepository;
		this.userService = userService;
		this.settlementRepository = settlementRepository;
		this.humanitarianOrganizationRepository = humanitarianOrganizationRepository;
	}

	@Override
	public List<Action> getAllActions() {
		return actionRepository.findAll();
	}
	@Override
	public Action newAction(ActionDTO newAction) {

		AppUser appUser = userService.loadCurrentUser();
		HumanitarianOrganization organisation = new HumanitarianOrganization(appUser.getUsername());
		Settlement settlement = settlementRepository.findFirstBySettlementName(newAction.getSettlementName());


		System.out.println(appUser.getEmail() + " " + organisation.getOrganizationName() + " " + settlement.getSettlementName());
		if(humanitarianOrganizationRepository.findByOrganizationName(organisation.getOrganizationName()) == null){
			humanitarianOrganizationRepository.save(organisation);
		}

		Action action = new Action(newAction.getDescription(), newAction.getActionName(), settlement, organisation);
		return actionRepository.save(action);
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
