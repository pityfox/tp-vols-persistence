package vol.metier.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("Moral")
public class ClientMoral extends Client {

	private TitreMoral titre;
	private String siret;
	
	public ClientMoral() {
	}
	
	@Column(name="Titre")
	@Enumerated(EnumType.STRING)
	public TitreMoral getTitre() {
		return titre;
	}

	public void setTitre(TitreMoral titre) {
		this.titre = titre;
	}

	@Column(name="Siret",length=50)
	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	

}
