package vol.metier.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name="Vols")
public class Vol {

	private long id;
	private Date dateDepart;
	private Date dateArrivee;
	private Date heureDepart;
	private Date heureArrivee;
	private List<Escale> escales;
	private List<Reservation> reservations;
	private Aeroport depart;
	private Aeroport arrivee;
	private List<CompagnieAerienneVol> compagniesAerienneVol;
	private int version;
	
	public Vol() {
	}

	@Id  @GeneratedValue 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name="DateDepart")
	@Temporal(TemporalType.DATE)
	public Date getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}

	@Column(name="DateArrivee")
	@Temporal(TemporalType.DATE)
	public Date getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(Date dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

	@Column(name="HeureDepart")
	@Temporal(TemporalType.TIME)
	public Date getHeureDepart() {
		return heureDepart;
	}

	public void setHeureDepart(Date heureDepart) {
		this.heureDepart = heureDepart;
	}

	@Column(name="HeureArrivee")
	@Temporal(TemporalType.TIME)
	public Date getHeureArrivee() {
		return heureArrivee;
	}

	public void setHeureArrivee(Date heureArrivee) {
		this.heureArrivee = heureArrivee;
	}

	@OneToMany(mappedBy="vol",fetch = FetchType.LAZY)
	public List<Escale> getEscales() {
		return escales;
	}

	public void setEscales(List<Escale> escales) {
		this.escales = escales;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AeroportDep_Id")
	public Aeroport getDepart() {
		return depart;
	}

	
	public void setDepart(Aeroport depart) {
		this.depart = depart;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AeroportArr_Id")
	public Aeroport getArrivee() {
		return arrivee;
	}

	public void setArrivee(Aeroport arrivee) {
		this.arrivee = arrivee;
	}

	@Version
	@Column(name="Version")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@OneToMany(mappedBy = "vol",fetch = FetchType.LAZY)
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	@OneToMany(mappedBy="id.vol",fetch = FetchType.LAZY)
	public List<CompagnieAerienneVol> getCompagniesAerienneVol() {
		return compagniesAerienneVol;
	}

	public void setCompagniesAerienneVol(
			List<CompagnieAerienneVol> compagniesAerienneVol) {
		this.compagniesAerienneVol = compagniesAerienneVol;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivee == null) ? 0 : arrivee.hashCode());
		result = prime
				* result
				+ ((compagniesAerienneVol == null) ? 0 : compagniesAerienneVol
						.hashCode());
		result = prime * result
				+ ((dateArrivee == null) ? 0 : dateArrivee.hashCode());
		result = prime * result
				+ ((dateDepart == null) ? 0 : dateDepart.hashCode());
		result = prime * result + ((depart == null) ? 0 : depart.hashCode());
		result = prime * result + ((escales == null) ? 0 : escales.hashCode());
		result = prime * result
				+ ((heureArrivee == null) ? 0 : heureArrivee.hashCode());
		result = prime * result
				+ ((heureDepart == null) ? 0 : heureDepart.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Vol other = (Vol) obj;
		if (arrivee == null) {
			if (other.arrivee != null)
				return false;
		} else if (!arrivee.equals(other.arrivee))
			return false;
		if (compagniesAerienneVol == null) {
			if (other.compagniesAerienneVol != null)
				return false;
		} else if (!compagniesAerienneVol.equals(other.compagniesAerienneVol))
			return false;
		if (dateArrivee == null) {
			if (other.dateArrivee != null)
				return false;
		} else if (!dateArrivee.equals(other.dateArrivee))
			return false;
		if (dateDepart == null) {
			if (other.dateDepart != null)
				return false;
		} else if (!dateDepart.equals(other.dateDepart))
			return false;
		if (depart == null) {
			if (other.depart != null)
				return false;
		} else if (!depart.equals(other.depart))
			return false;
		if (escales == null) {
			if (other.escales != null)
				return false;
		} else if (!escales.equals(other.escales))
			return false;
		if (heureArrivee == null) {
			if (other.heureArrivee != null)
				return false;
		} else if (!heureArrivee.equals(other.heureArrivee))
			return false;
		if (heureDepart == null) {
			if (other.heureDepart != null)
				return false;
		} else if (!heureDepart.equals(other.heureDepart))
			return false;
		if (id != other.id)
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
	
	

}
