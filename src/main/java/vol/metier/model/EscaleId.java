package vol.metier.model;

import java.io.Serializable;


@SuppressWarnings("serial")
public class EscaleId implements Serializable{

	private long aeroport;
	private long vol;
	
	public EscaleId() {
	}

	public long getAeroport() {
		return aeroport;
	}

	public void setAeroport(long aeroport) {
		this.aeroport = aeroport;
	}

	public long getVol() {
		return vol;
	}

	public void setVol(long vol) {
		this.vol = vol;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (aeroport ^ (aeroport >>> 32));
		result = prime * result + (int) (vol ^ (vol >>> 32));
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
		EscaleId other = (EscaleId) obj;
		if (aeroport != other.aeroport)
			return false;
		if (vol != other.vol)
			return false;
		return true;
	}
	

	
	
	

}
