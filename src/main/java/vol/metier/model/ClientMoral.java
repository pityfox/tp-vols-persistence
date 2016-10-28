package vol.metier.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

@Entity
@DiscriminatorValue("Moral")
@Validated
public class ClientMoral extends Client {

	private TitreMoral titre;
	private String siret;
	
	public ClientMoral() {
	}
	
	@Column(name="Titre")
	@Enumerated(EnumType.STRING)
	@NotNull(message="{client.titre.notNull}")
	public TitreMoral getTitre() {
		return titre;
	}

	public void setTitre(TitreMoral titre) {
		this.titre = titre;
	}

	@Column(name="Siret",length=50)
	@NotEmpty(message="{client.siret.notNull}")
	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	

}
