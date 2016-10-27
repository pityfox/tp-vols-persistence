package vol.metier.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;



@Entity
@DiscriminatorValue("Physique")
public class ClientPhysique extends Client {

	private TitrePhysique titre;
	private String prenom;
	
	public ClientPhysique() {
		super();
	}

	@Column(name="Titre")
	@Enumerated(EnumType.STRING)
	public TitrePhysique getTitre() {
		return titre;
	}

	public void setTitre(TitrePhysique titre) {
		this.titre = titre;
	}

	@Column(name="Prenom", length=50)
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	

}
