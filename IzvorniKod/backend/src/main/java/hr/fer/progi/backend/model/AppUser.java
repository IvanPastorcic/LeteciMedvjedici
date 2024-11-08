package hr.fer.progi.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "APPUSER")
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String email;

	@Column
	private String username;

	/* 
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * @JoinColumn(name = "LOCATION_GEOGRAPHICCOORDINATES", nullable =
	 * false) private String reportGeographicCoordinates;
	 */
	
	public AppUser() {
	}

	public AppUser(String email, String username/* , @NotEmpty String reportGeographicCoordinates */) {
		this.email = email;

		this.username = username;
		/* this.reportGeographicCoordinates = reportGeographicCoordinates; */
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}
	
	/*
	 * public String getGeographicCoordinates() { return
	 * reportGeographicCoordinates; }
	 * 
	 * public void setGeographicCoordinates(String reportGeographicCoordinates) {
	 * this.reportGeographicCoordinates = reportGeographicCoordinates; }
	 */
}
