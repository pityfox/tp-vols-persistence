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
@Table(name="Villes")
public class Ville {

	
	private long id;
	private String nom;
	private List<VilleAeroport> villeAeroport;
	private int version;
	
	public Ville() {
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
	

	@OneToMany(mappedBy="ville")
	public List<VilleAeroport> getVilleAeroport() {
		return villeAeroport;
	}

	public void setVilleAeroport(List<VilleAeroport> villeAeroport) {
		this.villeAeroport = villeAeroport;
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
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + version;
		result = prime * result
				+ ((villeAeroport == null) ? 0 : villeAeroport.hashCode());
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
		Ville other = (Ville) obj;
		if (id != other.id)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (version != other.version)
			return false;
		if (villeAeroport == null) {
			if (other.villeAeroport != null)
				return false;
		} else if (!villeAeroport.equals(other.villeAeroport))
			return false;
		return true;
	}
	
	

}
