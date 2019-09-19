package cases;

import java.awt.Color;

public class Volcan extends Biome{
	
	private static Color couleur = new Color(230,60,50);
	private static boolean feetCrossable = false;
	private static double bonusArmee = 3;
	private static int id = 4;

	//builder
	public Volcan() {
		super(couleur, feetCrossable, bonusArmee, id);
		
	}
	
	//fct Parente
	public Color getColor() {return couleur;}

}
