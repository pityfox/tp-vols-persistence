package vol.metier.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name= "Client", length=10, discriminatorType = DiscriminatorType.STRING)
public abstract class Client {

	private Long id;
	private String nom;
	private Integer numeroTel;
	private Integer numeroFax;
	private String email;
	private Login login;
	private Adresse adresse;
	private List<Reservation> reservations;
	//private Set<Reservation> reservations = new HashSet<Reservation>();
	private Integer version;
	

	public Client() {
	}
	
	
	
	public Client(String nom) {
		this.nom = nom;
	}



	@Id @GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="Nom",length=50)
	@NotEmpty(message="{client.nom.notNull}")
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name="NumeroTel")
	public Integer getNumeroTel() {
		return numeroTel;
	}

	public void setNumeroTel(Integer numeroTel) {
		this.numeroTel = numeroTel;
	}

	@Column(name="NumeroFax")
	public Integer getNumeroFax() {
		return numeroFax;
	}

	public void setNumeroFax(Integer numeroFax) {
		this.numeroFax = numeroFax;
	}

	@Column(name="Email",length=100)
	@NotEmpty(message="{client.email.notNull}")
	@Email(message="{client.email.format}")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Login_Id")
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="adresse",column=@Column(name="C_RUE",length=50)),
		@AttributeOverride(name="codePostal",column=@Column(name="C_CP",length=20)),
		@AttributeOverride(name="ville",column=@Column(name="C_VILLE",length=50)),
		@AttributeOverride(name="pays",column=@Column(name="C_PAYS",length=50))
		})
	@Valid
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
//	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
//	public Set<Reservation> getReservations() {
//		return reservations;
//	}
//
//	public void setReservations(Set<Reservation> reservations) {
//		this.reservations = reservations;
//	}
	
	
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	public List<Reservation> getReservations() {
		return reservations;
	}



	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Version
	@Column(name="Version")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}





	// Avoir le type de client par la DiscriminatorValue
	@Transient
	public String getType() {
		return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}
	

}