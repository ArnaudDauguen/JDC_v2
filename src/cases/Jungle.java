package cases;

import java.awt.Color;

public class Jungle extends Biome{
	
	private static Color couleur = new Color(125,165,40);
	private static boolean feetCrossable = true;
	private static double bonusArmee = 3;
	private static int id = 2;

	//builder
	public Jungle() {
		super(couleur, feetCrossable, bonusArmee, id);
		
	}
	
	//fct Parente
	public Color getColor() {return couleur;}

}
