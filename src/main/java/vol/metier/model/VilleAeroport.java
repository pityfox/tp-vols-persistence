package vol.metier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="VillesAeroports")
@IdClass(VilleAeroportId.class)
public class VilleAeroport {


	private Ville ville;
	private Aeroport aeroport;
	private int version;
	
	public VilleAeroport() {
	}

	public VilleAeroport(Ville ville, Aeroport aeroport) {
		super();
		this.ville = ville;
		this.aeroport = aeroport;
	}


	@Version
	@Column(name="Version")
	public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}


	@Id 
	@ManyToOne
	@JoinColumn(name="Ville_id")
	public Ville getVille() {
		return ville;
	}


	public void setVille(Ville ville) {
		this.ville = ville;
	}


	@Id 
	@ManyToOne
	@JoinColumn(name="Aeroport_id")
	public Aeroport getAeroport() {
		return aeroport;
	}


	public void setAeroport(Aeroport aeroport) {
		this.aeroport = aeroport;
	}

}
