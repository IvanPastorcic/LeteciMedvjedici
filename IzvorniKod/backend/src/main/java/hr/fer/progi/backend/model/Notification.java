package hr.fer.progi.backend.model;

import hr.fer.progi.backend.model.Embeddable.NotificationId;
import hr.fer.progi.backend.model.Enum.NotificationType;
import hr.fer.progi.backend.model.Enum.Region;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "NOTIFICATION")
public class Notification {

	@EmbeddedId
	private NotificationId id = new NotificationId();
	
	@NotNull
	@ManyToOne
	@MapsId("appUserId")
	@JoinColumn(name = "id", nullable = false)
	private AppUser appUser;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column
	private NotificationType notificationType;

	public Notification() {
	}
	
	public Notification(Region region, AppUser appUser, NotificationType notificationType) {
		this.id.setRegion(region);
		this.appUser = appUser;
		this.notificationType = notificationType;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}

	public NotificationId getId() {
		return id;
	}
	
	// from embedded
	
	public void setRegion(Region region) {
		this.id.setRegion(region);
	}
	
	public Region getRegion() {
		return this.id.getRegion();
	}

	public Long getAppUserId() {
		return this.id.getAppUserId();
	}
}
