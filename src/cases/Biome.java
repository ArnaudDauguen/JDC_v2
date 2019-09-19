package cases;

import java.awt.Color;

public abstract class Biome {
	
	
	private Color couleur;
	private boolean feetCrossable;
	private double bonusArmee;
	private int idBiome;
	
	//builder
	public Biome(Color couleur, boolean feetCrossable, double bonusArmee, int idBiome) {
		this.couleur = couleur;
		this.feetCrossable = feetCrossable;
		this.bonusArmee = bonusArmee;
		this.idBiome = idBiome;
	}
	
	
	//fct
	public abstract Color getColor();
	public boolean getCrossable() {return feetCrossable;}
	public double getArmeeBonus() {return bonusArmee;}
	public int getId() {return idBiome;}
}
