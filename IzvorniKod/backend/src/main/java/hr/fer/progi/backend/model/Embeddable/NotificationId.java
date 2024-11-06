package hr.fer.progi.backend.model.Embeddable;

import java.io.Serializable;

import hr.fer.progi.backend.model.Enum.Region;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class NotificationId implements Serializable {
	private static final long serialVersionUID = -5700779965628960104L;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Region region;

	@NotNull
	private Long appUserId;

	public NotificationId() {
	}

	public void setRegion(Region region) {
		this.region = region;
	}
	
	public Region getRegion() {
		return region;
	}

	public Long getAppUserId() {
		return appUserId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
