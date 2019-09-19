package Guerre;

import java.util.ArrayList;
//packages
import Civilisations.Civilisation;
import cases.Case;

public class AnnihilationWar extends Guerre{
	
	private static int idTypeGuerre = 0;
	private Civilisation attaquant;
	private Civilisation defenseur;
	private int[] baseStatsAttaquant;
	private int[] baseStatsDefenseur;

	public AnnihilationWar(Civilisation attaquant, Civilisation defenseur) {
		super(idTypeGuerre, attaquant, defenseur, "d'annihilation");
		this.attaquant = attaquant;
		this.defenseur = defenseur;
		stockerStatsAvantGuerre();
		
	}


	public boolean warConclusion() {
		return false;
	}
	

	private void stockerStatsAvantGuerre() {
		baseStatsAttaquant = new int[5];
		baseStatsAttaquant[0] = attaquant.getArmee();
		baseStatsAttaquant[1] = attaquant.getPopulation();
		baseStatsAttaquant[2] = attaquant.getNourriture();
		baseStatsAttaquant[3] = attaquant.getBonheur();
		baseStatsAttaquant[4] = attaquant.getNbCase();
		baseStatsDefenseur = new int[5];
		baseStatsDefenseur[0] = defenseur.getArmee();
		baseStatsDefenseur[1] = defenseur.getPopulation();
		baseStatsDefenseur[2] = defenseur.getNourriture();
		baseStatsDefenseur[3] = defenseur.getBonheur();
		baseStatsDefenseur[4] = defenseur.getNbCase();
	}
	
	
	
	//fonctions rapides
	private boolean ArmeeAttaquantEnDeroute() {return (attaquant.getBonheur() <= 0 || attaquant.getArmee() < baseStatsAttaquant[0] * 0.1);}
	private boolean TropTerritoirePerduAttaquant() {return (attaquant.getNbCase() / baseStatsAttaquant[4] <= 0.01);}

	private boolean ArmeeDefenseurEnDeroute() {return (defenseur.getBonheur() <= 0 || defenseur.getArmee() < baseStatsDefenseur[0] * 0.1);}
	private boolean TropTerritoirePerduDefenseur() {return (defenseur.getNbCase() / baseStatsDefenseur[4] <= 0.01);}
	
	
	
	
	
	
}


/*
  
  Aggression,Reconquete,Conquete,Annihilation
  
  
 */
