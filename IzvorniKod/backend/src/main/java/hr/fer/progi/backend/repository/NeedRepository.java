package hr.fer.progi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.fer.progi.backend.model.Need;
import hr.fer.progi.backend.model.Embeddable.NeedId;

@Repository
public interface NeedRepository extends JpaRepository<Need, NeedId> {
	//nisam sigurna je li ovo dobro 
}