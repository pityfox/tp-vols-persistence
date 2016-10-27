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

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name= "Client", length=10, discriminatorType = DiscriminatorType.STRING)
public abstract class Client {

	private long id;
	private String nom;
	private int numeroTel;
	private int numeroFax;
	private String email;
	private Login login;
	private Adresse adresse;
	private List<Reservation> reservations;
	//private Set<Reservation> reservations = new HashSet<Reservation>();
	private int version;
	

	public Client() {
	}
	
	
	
	public Client(String nom) {
		this.nom = nom;
	}



	@Id @GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name="Nom",length=50)
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name="NumeroTel")
	public int getNumeroTel() {
		return numeroTel;
	}

	public void setNumeroTel(int numeroTel) {
		this.numeroTel = numeroTel;
	}

	@Column(name="NumeroFax")
	public int getNumeroFax() {
		return numeroFax;
	}

	public void setNumeroFax(int numeroFax) {
		this.numeroFax = numeroFax;
	}

	@Column(name="Email",length=100)
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
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + numeroFax;
		result = prime * result + numeroTel;
		result = prime * result
				+ ((reservations == null) ? 0 : reservations.hashCode());
		result = prime * result + version;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (numeroFax != other.numeroFax)
			return false;
		if (numeroTel != other.numeroTel)
			return false;
		if (reservations == null) {
			if (other.reservations != null)
				return false;
		} else if (!reservations.equals(other.reservations))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	// Avoir le type de client par la DiscriminatorValue
	@Transient
	public String getType() {
		return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}
	

}
