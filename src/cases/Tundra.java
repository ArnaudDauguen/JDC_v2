package cases;

import java.awt.Color;

public class Tundra extends Biome{
	
	//private static Color couleur = new Color(140,130,150);
	private static Color couleur = new Color(255,255,255);
	private static boolean feetCrossable = true;
	private static double bonusArmee = 5;
	private static int id = 3;

	//builder
	public Tundra() {
		super(couleur, feetCrossable, bonusArmee, id);
		
	}
	
	//fct Parente
	public Color getColor() {return couleur;}

}
