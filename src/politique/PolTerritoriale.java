package politique;

public abstract class PolTerritoriale {
	
	private int id;
	private String nom;
	private double nbNewCaseProbable;
	
	public PolTerritoriale(int id, String nom, double nbNewCaseProbable) {
		this.id = id;
		this.nom = nom;
		this.nbNewCaseProbable = nbNewCaseProbable;
	}
	
	public int getId() {return id;}
	public String getNom() {return nom;}
	public String toString() {return nom;}
	public double getNbNewCase() {return nbNewCaseProbable;}
}

