package hr.fer.progi.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.fer.progi.backend.model.Action;
import hr.fer.progi.backend.model.Report;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long>{

	List<Action> findByActionName(String nameOfAction);

}
