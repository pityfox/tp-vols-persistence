package vol.metier.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name="Escale")
@IdClass(EscaleId.class)
public class Escale {

	private Date heureDepart;
	private Date heureArrivee;
	private Aeroport aeroport;
	private Vol vol;
	private int version;
	
	public Escale() {
	}
	
	

	public Escale(Date heureDepart, Date heureArrivee, Aeroport aeroport,Vol vol) {
		this.heureDepart = heureDepart;
		this.heureArrivee = heureArrivee;
		this.aeroport = aeroport;
		this.vol = vol;
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

	@Id 
	@ManyToOne
	@JoinColumn(name="Aeroport_Id")
	public Aeroport getAeroport() {
		return aeroport;
	}

	public void setAeroport(Aeroport aeroport) {
		this.aeroport = aeroport;
	}

	@Id
	@ManyToOne
	@JoinColumn(name="Vol_Id")
	public Vol getVol() {
		return vol;
	}

	public void setVol(Vol vol) {
		this.vol = vol;
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
		result = prime * result
				+ ((aeroport == null) ? 0 : aeroport.hashCode());
		result = prime * result
				+ ((heureArrivee == null) ? 0 : heureArrivee.hashCode());
		result = prime * result
				+ ((heureDepart == null) ? 0 : heureDepart.hashCode());
		result = prime * result + version;
		result = prime * result + ((vol == null) ? 0 : vol.hashCode());
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
		Escale other = (Escale) obj;
		if (aeroport == null) {
			if (other.aeroport != null)
				return false;
		} else if (!aeroport.equals(other.aeroport))
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
		if (version != other.version)
			return false;
		if (vol == null) {
			if (other.vol != null)
				return false;
		} else if (!vol.equals(other.vol))
			return false;
		return true;
	}

	

	
}
