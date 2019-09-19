package cases;

import java.awt.Color;

public class OceanProfond extends Relief{
	
	private static Color couleur = new Color(30,30,200);
	private static boolean feetCrossable = false;
	private static double bonusArmee = 0;
	private static int id = 0;

	//builder
	public OceanProfond() {
		super(couleur, feetCrossable, bonusArmee, id);
		
	}
	
	//fct Parente
	public Color getColor() {return couleur;}

}
