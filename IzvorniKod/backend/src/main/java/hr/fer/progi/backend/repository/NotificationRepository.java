package hr.fer.progi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.fer.progi.backend.model.Notification;
import hr.fer.progi.backend.model.Embeddable.NotificationId;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, NotificationId> {
	
}