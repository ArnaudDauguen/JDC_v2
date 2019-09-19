package cases;

import java.awt.Color;

public class Foret extends Biome{
	
	private static Color couleur = new Color(50,180,75);
	private static boolean feetCrossable = true;
	private static double bonusArmee = 2;
	private static int id = 1;

	//builder
	public Foret() {
		super(couleur, feetCrossable, bonusArmee, id);
		
	}
	
	//fct Parente
	public Color getColor() {return couleur;}

}
