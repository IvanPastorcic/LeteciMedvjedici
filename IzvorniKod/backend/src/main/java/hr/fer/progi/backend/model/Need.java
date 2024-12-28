package hr.fer.progi.backend.model;

import hr.fer.progi.backend.model.Embeddable.NeedId;
import hr.fer.progi.backend.model.Enum.NeedStatus;
import hr.fer.progi.backend.model.Enum.NeedType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "NEED")
public class Need {

	@EmbeddedId
	private NeedId id = new NeedId();

	@NotNull
	@ManyToOne
	@MapsId("appUserId")
	@JoinColumn(name = "id", nullable = false)
	private AppUser appUser;

	@NotEmpty
	@Column
	private String address;

	@NotNull
	@Enumerated(EnumType.STRING)
	private NeedStatus needStatus;

	public Need() {
	}

	public Need(NeedType needType, AppUser appUser, String address) {
		this.id.setNeedType(needType);
		this.appUser = appUser;
		this.address = address;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public NeedId getId() {
		return id;
	}

	public NeedStatus getNeedStatus() {
		return needStatus;
	}

	public void setNeedStatus(NeedStatus needStatus) {
		this.needStatus = needStatus;
	}
	
	// from embedded
	
	public NeedType getNeedType() {
		return this.id.getNeedType();
	}

	public void setNeedType(NeedType needType) {
		this.id.setNeedType(needType);
	}

	public Long getAppUserId() {
		return this.id.getAppUserId();
	}

}
