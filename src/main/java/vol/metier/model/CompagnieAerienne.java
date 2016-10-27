package vol.metier.model;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="CompagnieAerienne")
public class CompagnieAerienne {

	private long id;
	private String nom;
	private List<CompagnieAerienneVol> compagniesAerienneVol;
	private int version;
	
	public CompagnieAerienne() {
	}

	@Id @GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name="Nom", length=50)
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@OneToMany(mappedBy="id.compagnieAerienne")
	public List<CompagnieAerienneVol> getCompagniesAerienneVol() {
		return compagniesAerienneVol;
	}

	public void setCompagniesAerienneVol(
			List<CompagnieAerienneVol> compagniesAerienneVol) {
		this.compagniesAerienneVol = compagniesAerienneVol;
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
		result = prime
				* result
				+ ((compagniesAerienneVol == null) ? 0 : compagniesAerienneVol
						.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
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
		CompagnieAerienne other = (CompagnieAerienne) obj;
		if (compagniesAerienneVol == null) {
			if (other.compagniesAerienneVol != null)
				return false;
		} else if (!compagniesAerienneVol.equals(other.compagniesAerienneVol))
			return false;
		if (id != other.id)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	
	

}
