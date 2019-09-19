package cases;

import java.awt.Color;

public class Desert extends Biome{
	
	private static Color couleur = new Color(250,250,100);
	private static boolean feetCrossable = true;
	private static double bonusArmee = 1.5;
	private static int id = 6;

	//builder
	public Desert() {
		super(couleur, feetCrossable, bonusArmee, id);
		
	}
	
	//fct Parente
	public Color getColor() {return couleur;}

}
