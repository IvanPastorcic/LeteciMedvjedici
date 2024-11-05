package hr.fer.progi.backend.model;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table("REPORT")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @NotEmpty
    private String status;
    
    @NotEmpty
    private Date time;
    
    private String shortDescription;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "geographicCoordinates", nullable = false)
    private String reportGeographicCoordinates;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disasterId", nullable = false)
    private NaturalDisaster disaster;

    public Report() {}

    // without relationships
    public Report(String status, Date time, String shortDescription) {
        this.status = status;
        this.time = time;
        this.shortDescription = shortDescription;
    }

    // without relationships
    public Report(String status, Date time, String reportGeographicCoordinates, String shortDescription, User user, NaturalDisaster disaster) {
        this.status = status;
        this.time = time;
        this.reportGeographicCoordinates = reportGeographicCoordinates;
        this.shortDescription = shortDescription;
        this.user = user;
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

	public String getGeographicCoordinates() {
		return reportGeographicCoordinates;
	}

	public void setGeographicCoordinates(String reportGeographicCoordinates) {
		this.reportGeographicCoordinates = reportGeographicCoordinates;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public NaturalDisaster getDisaster() {
		return disaster;
	}

	public void setDisaster(NaturalDisaster disaster) {
		this.disaster = disaster;
	}

}

