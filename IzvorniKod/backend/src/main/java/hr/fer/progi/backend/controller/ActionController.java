package hr.fer.progi.backend.controller;

import java.util.List;

import hr.fer.progi.backend.dto.ActionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.progi.backend.model.Action;
import hr.fer.progi.backend.model.Report;
import hr.fer.progi.backend.repository.exception.InputIsNullException;
import hr.fer.progi.backend.service.ActionService;


//controller for managing humanitarian actions
@RestController
@RequestMapping("/actions")
public class ActionController {

private final ActionService actionService;
	
	public ActionController(ActionService actionService) {
		this.actionService = actionService;
	}


	//TODO: promijeniti u actions()
	@GetMapping()
	public ResponseEntity<List<Action>> getActions() {
		return ResponseEntity.ok(actionService.getAllActions());
	}


	@PostMapping("/add")
	@Secured("ROLE_HUMANITARIAN")
	Action newAction(@RequestBody ActionDTO newAction){
		return actionService.newAction(newAction);
	}	
	
	@GetMapping("/{id}") 
	 public ResponseEntity<Action> findById(@PathVariable Long id) {
		Action action = actionService.findById(id);
       
       if (action == null) {
       	 throw new InputIsNullException("Akcija ne postoji");
       }
       
       return ResponseEntity.ok(action);
   }
	
	@GetMapping("/actionName/{nameOfAction}") 
	 public List<Action> findByName(@PathVariable String nameOfAction) {
		return actionService.findByName(nameOfAction);
  }



	
}

