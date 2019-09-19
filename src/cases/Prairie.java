package cases;

import java.awt.Color;

public class Prairie extends Biome{
	
	private static Color couleur = new Color(50,230,75);
	private static boolean feetCrossable = true;
	private static double bonusArmee = 1;
	private static int id = 0;

	//builder
	public Prairie() {
		super(couleur, feetCrossable, bonusArmee, id);
		
	}
	
	//fct Parente
	public Color getColor() {return couleur;}

}
