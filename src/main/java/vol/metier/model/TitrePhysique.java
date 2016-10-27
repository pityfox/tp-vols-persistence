package vol.metier.model;

public enum TitrePhysique {
	MR("titre.M"), MME("titre.MME"), MLLE("titre.MLLE");

	private final String label;

	private TitrePhysique(String label) {
		this.label = label;

	}

	public String getLabel() {
		return label;
	}
}
