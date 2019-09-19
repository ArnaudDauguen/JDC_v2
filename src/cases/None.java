package cases;

import java.awt.Color;

public class None extends Biome{
	
	private static Color couleur = new Color(100,100,100);
	private static boolean feetCrossable = true;
	private static double bonusArmee = 0;
	private static int id = -1;

	//builder
	public None() {
		super(couleur, feetCrossable, bonusArmee, id);
		
	}
	
	//fct Parente
	public Color getColor() {return couleur;}

}
