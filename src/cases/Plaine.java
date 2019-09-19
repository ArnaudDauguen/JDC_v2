package cases;

import java.awt.Color;

public class Plaine extends Relief{
	
	private static Color couleur = new Color(0,0,0);
	private static boolean feetCrossable = true;
	private static double bonusArmee = 1;
	private static int id = 8;

	//builder
	public Plaine() {
		super(couleur, feetCrossable, bonusArmee, id);
		
	}
	
	//fct Parente (Terrain)
	public Color getColor() {return couleur;}

}
