package hr.fer.progi.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ACTION")
public class Action {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column
	private String actionDescription;

	@NotEmpty
	@Column
	private String actionName;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SETTLEMENT_ID", nullable = false)
	private Settlement settlement;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "HUMANITARIAN_ORGANIZATION_ID", nullable = false)
	private HumanitarianOrganization humanitarianOrganization;

	public Action() {
	}

	// without relationships
	public Action(String actionDescription, String actionName) {
		super();
		this.actionDescription = actionDescription;
		this.actionName = actionName;
	}

	// with relationships
	public Action(String actionDescription, String actionName, Settlement settlement,
			HumanitarianOrganization humanitarianOrganization) {
		super();
		this.actionDescription = actionDescription;
		this.actionName = actionName;
		this.settlement = settlement;
		this.humanitarianOrganization = humanitarianOrganization;
	}

	public String getActionDescription() {
		return actionDescription;
	}

	public void setActionDescription(String actionDescription) {
		this.actionDescription = actionDescription;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Settlement getSettlement() {
		return settlement;
	}

	public void setSettlement(Settlement settlement) {
		this.settlement = settlement;
	}

	public HumanitarianOrganization getHumanitarianOrganization() {
		return humanitarianOrganization;
	}

	public void setHumanitarianOrganization(HumanitarianOrganization humanitarianOrganization) {
		this.humanitarianOrganization = humanitarianOrganization;
	}

	public Long getId() {
		return id;
	}

}
