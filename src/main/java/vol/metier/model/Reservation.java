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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name="Reservations")
public class Reservation {

	private Long id;
	private Date date;
	private Integer numero;
	private Passager passager;
	private Vol vol;
	private Integer version;
	
	private Client client;
	
	public Reservation() {
	}

	@Id  @GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message="{reservation.date.past}")
	@NotNull(message="{reservation.date.notNull}")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name="Numero", length = 30)
	@NotNull(message="{reservation.numero.notNull}")
		public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
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
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
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
