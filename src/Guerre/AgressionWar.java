package Guerre;

import java.util.ArrayList;
//packages
import Civilisations.Civilisation;
import cases.Case;

public class AgressionWar extends Guerre{
	
	private static int idTypeGuerre = 1;
	private Civilisation attaquant;
	private Civilisation defenseur;
	private int[] baseStatsAttaquant;
	private int[] baseStatsDefenseur;

	public AgressionWar(Civilisation attaquant, Civilisation defenseur) {
		super(idTypeGuerre, attaquant, defenseur, "d'aggression");
		this.attaquant = attaquant;
		this.defenseur = defenseur;
		stockerStatsAvantGuerre();
		
	}


	public boolean warConclusion() {
		if(ArmeeAttaquantEnDeroute() || TropTerritoirePerduAttaquant()) {
			return true;
		}
		if(ArmeeDefenseurEnDeroute() || TropTerritoirePerduDefenseur()) {
			return true;
		}
		return false;
	}
	

	private void stockerStatsAvantGuerre() {
		baseStatsAttaquant = new int[3];
		baseStatsAttaquant[0] = attaquant.getArmee();
		baseStatsAttaquant[1] = attaquant.getPopulation();
		baseStatsAttaquant[2] = attaquant.getNbCase();
		baseStatsDefenseur = new int[3];
		baseStatsDefenseur[0] = defenseur.getArmee();
		baseStatsDefenseur[1] = defenseur.getPopulation();
		baseStatsDefenseur[2] = defenseur.getNbCase();
	}
	
	
	
	//fonctions rapides
	private boolean ArmeeAttaquantEnDeroute() {return (attaquant.getBonheur() <= 0 || attaquant.getArmee() < baseStatsAttaquant[0] * 0.1);}
	private boolean TropTerritoirePerduAttaquant() {return (attaquant.getNbCase() / baseStatsAttaquant[4] < attaquant.getRegime().getSoutienDeGuerre());}

	private boolean ArmeeDefenseurEnDeroute() {return (defenseur.getBonheur() <= 0 || defenseur.getArmee() < baseStatsDefenseur[0] * 0.1);}
	private boolean TropTerritoirePerduDefenseur() {return (defenseur.getNbCase() / baseStatsDefenseur[4] < defenseur.getRegime().getSoutienDeGuerre());}
	
	
	
	
	
	
}


/*
  
  Aggression,Reconquete,Conquete,Annihilation
  
  
 */
