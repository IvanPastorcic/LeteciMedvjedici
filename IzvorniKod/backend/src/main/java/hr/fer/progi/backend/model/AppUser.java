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


	/*TODO: skuzit kako spremamo password*/
	@Column
	private String password;



	@Column
	private String username;

	public AppUser() {
		
	}
	
	public AppUser(String email, String password, String username) {
		super();
		this.email = email;
		this.password = password;
		this.username = username;
	}
	


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public void setId(Long id) {this.id = id;}

	public Long getId() { return id;}
}
