package cultures;

public abstract class Culture {
	
	private int id;
	private String nom;
	
	public Culture(int id, String nom) {
		this.id = id;
		this.nom = nom;
		
	}
	
	public int getId() {return id;}
}
