package vol.metier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table (name="Login")
public class Login {

	private Long id;
	private String login;
	private String motDePasse;
	private boolean admin;
	private Integer version;
	private Client client;
	
	
	public Login() {
	}

	@Id  @GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="Login", length=50, unique=true)
	@Size(min = 5, message="{login.login.required}") 
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name="MotDePasse",length=50)
	@Size(min = 6, message="{login.motDePasse.required}")
	@Pattern(regexp = ".*[@#$%]", message="{login.motDePasse.required2}")
	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	@Column(name="Admin")
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	@OneToOne(mappedBy ="login", fetch= FetchType.LAZY)//eager
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Version
	@Column(name="Version")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
}
