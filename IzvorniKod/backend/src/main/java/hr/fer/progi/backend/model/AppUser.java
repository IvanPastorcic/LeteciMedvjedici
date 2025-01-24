package hr.fer.progi.backend.model;

import hr.fer.progi.backend.model.Enum.NeedStatus;
import hr.fer.progi.backend.model.Enum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

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

	@NotNull
	@Enumerated(EnumType.STRING)
	private Role role;

	public AppUser() {
	}

	public AppUser(String email, String username/* , @NotEmpty String reportGeographicCoordinates */, Role role) {
		this.email = email;

		this.username = username;
		/* this.reportGeographicCoordinates = reportGeographicCoordinates; */
		this.role = role;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
