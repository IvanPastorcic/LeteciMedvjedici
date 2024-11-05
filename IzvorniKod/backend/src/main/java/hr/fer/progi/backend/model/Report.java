package hr.fer.progi.backend.model;

import java.sql.Date;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "REPORT")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @NotNull
    private String status;
    
    @NotNull
    private Date time;
    
    private String shortDescription;

	/*
	**NOTE**
	@JoinColumn(name ="ovdje_ide_NAZIVTABLICE_NAZIVCOLUMNA", nullable=false)
	npr.
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "geographicCoordinates", nullable = false)
    private String reportGeographicCoordinates;
*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NATURAL_DISASTER_ID", nullable = false)
    private NaturalDisaster disaster;

    public Report() {}

    // without relationships
    public Report(String status, Date time, String shortDescription) {
        this.status = status;
        this.time = time;
        this.shortDescription = shortDescription;
    }

    // without relationships
    public Report(String status, Date time/*, String reportGeographicCoordinates*/, String shortDescription, AppUser appUser, NaturalDisaster disaster) {
        this.status = status;
        this.time = time;
        /*this.reportGeographicCoordinates = reportGeographicCoordinates;*/
        this.shortDescription = shortDescription;
        this.appUser = appUser;
        this.disaster = disaster;
    }

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	/*public String getGeographicCoordinates() {
		return reportGeographicCoordinates;
	}

	public void setGeographicCoordinates(String reportGeographicCoordinates) {
		this.reportGeographicCoordinates = reportGeographicCoordinates;
	}*/

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
		return disaster;
	}

	public void setDisaster(NaturalDisaster disaster) {
		this.disaster = disaster;
	}

}

