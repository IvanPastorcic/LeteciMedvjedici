package hr.fer.progi.backend.model;

import java.sql.Date;

import hr.fer.progi.backend.model.Enum.ReportStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "REPORT")
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column
	@Enumerated(EnumType.STRING)
	private ReportStatus reportStatus;

	@NotNull
	@Column
	private Date time;

	@NotEmpty
	@Column
	private String shortDescription;

	@Column
	private String photo; // putanja do slike?

	/*
	 ** NOTE**
	 * 
	 * @JoinColumn(name ="ovdje_ide_NAZIVTABLICE_NAZIVCOLUMNA", nullable=false)
	 * 
	 * 
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * @JoinColumn(name = "LOCATION_GEOGRAPHICCOORDINATES", nullable =
	 * false) private String reportGeographicCoordinates;
	 */
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private AppUser appUser;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NATURAL_DISASTER_ID", nullable = false)
	private NaturalDisaster naturalDisaster;

	public Report() {
	}

	// without relationships
	public Report(ReportStatus reportStatus, Date time, String shortDescription, String photo) {
		this.reportStatus = reportStatus;
		this.time = time;
		this.shortDescription = shortDescription;
		this.photo = photo;
	}

	// with relationships
	public Report(ReportStatus reportStatus, Date time/* , @NotEmpty String reportGeographicCoordinates */,
			String shortDescription, String photo, AppUser appUser, NaturalDisaster naturalDisaster) {
		this.reportStatus = reportStatus;
		this.time = time;
		/* this.reportGeographicCoordinates = reportGeographicCoordinates; */
		this.shortDescription = shortDescription;
		this.photo = photo;
		this.appUser = appUser;
		this.naturalDisaster = naturalDisaster;
	}

	public Long getId() {
		return id;
	}

	public ReportStatus getStatus() {
		return reportStatus;
	}

	public void setStatus(ReportStatus reportStatus) {
		this.reportStatus = reportStatus;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	/*
	 * public String getGeographicCoordinates() { return
	 * reportGeographicCoordinates; }
	 * 
	 * public void setGeographicCoordinates(String reportGeographicCoordinates) {
	 * this.reportGeographicCoordinates = reportGeographicCoordinates; }
	 */

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public AppUser getUser() {
		return appUser;
	}

	public void setUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public NaturalDisaster getDisaster() {
		return naturalDisaster;
	}

	public void setDisaster(NaturalDisaster naturalDisaster) {
		this.naturalDisaster = naturalDisaster;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
