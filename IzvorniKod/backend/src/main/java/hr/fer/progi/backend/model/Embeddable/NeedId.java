package hr.fer.progi.backend.model.Embeddable;

import java.io.Serializable;

import hr.fer.progi.backend.model.Enum.NeedType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class NeedId implements Serializable {
	private static final long serialVersionUID = 2102611351575520855L;

	@NotNull
	@Enumerated(EnumType.STRING)
	private NeedType needType;

	@NotNull
	private Long id;

	public NeedId() {
	}

	public NeedId(NeedType needType) {
		this.needType = needType;
	}

	public NeedType getNeedType() {
		return needType;
	}

	public void setNeedType(NeedType needType) {
		this.needType = needType;
	}

	public Long getId() {
		return id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
