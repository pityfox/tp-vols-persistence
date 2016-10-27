package vol.metier.model;


public enum TitreMoral {
	SARL("titre.SARL"), EURL("titre.EURL"), SAS("titre.SAS");

	private final String label;

	private TitreMoral(String label) {
		this.label = label;

	}

	public String getLabel() {
		return label;
	}
	
}
