package cases;

import java.awt.Color;

public class Marais extends Biome{
	
	private static Color couleur = new Color(140,175,125);
	private static boolean feetCrossable = true;
	private static double bonusArmee = 0.5;
	private static int id = 5;

	//builder
	public Marais() {
		super(couleur, feetCrossable, bonusArmee, id);
		
	}
	
	//fct Parente
	public Color getColor() {return couleur;}

}
