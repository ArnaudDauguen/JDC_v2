package cases;

import java.awt.Color;

public abstract class Relief {
	
	
	private static Color couleur;
	private static boolean feetCrossable;
	private double bonusArmee;
	private int idRelief;
	
	//builder
	public Relief(Color couleur, boolean feetCrossable, double bonusArmee, int idRelief) {
		this.couleur = couleur;
		this.feetCrossable = feetCrossable;
		this.bonusArmee = bonusArmee;
		this.idRelief = idRelief;
	}
	
	
	//fct
	public Color getColor() {return couleur;}
	public boolean getCrossable() {return feetCrossable;}
	public double getArmeeBonus() {return bonusArmee;}
	public int getId() {return idRelief;}
}
