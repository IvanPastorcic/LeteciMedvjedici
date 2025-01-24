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
	@MapsId("id")
	@JoinColumn(name = "id", nullable = false)
	private Report report;

	@NotEmpty
	@Column
	private String address;

	@NotNull
	@Enumerated(EnumType.STRING)
	private NeedStatus needStatus;

	@NotNull
	private int quantity;

	public Need() {
	}

	public Need(NeedType needType, Report report, String address, int quantity) {
		this.id.setNeedType(needType);
		this.report = report;
		this.address = address;
		this.quantity = quantity;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
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

	public Long getReportId() {
		return this.id.getId();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
