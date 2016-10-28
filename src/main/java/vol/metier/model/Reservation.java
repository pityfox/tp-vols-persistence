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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name="Reservations")
public class Reservation {

	private long id;
	private Date date;
	private int numero;
	private Passager passager;
	private Vol vol;
	private int version;
	
	private Client client;
	
	public Reservation() {
	}

	@Id  @GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message="La date de reservation doit être inférieure à la date du jour")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name="Numero", length = 30)
	@Min(value=1, message="Le numero doit comporter que des chiffres")
		public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="Passager_Id")
	public Passager getPassager() {
		return passager;
	}

	public void setPassager(Passager passager) {
		this.passager = passager;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Client_Id")
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	
	
	

}
