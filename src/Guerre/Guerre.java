package Guerre;

import java.util.ArrayList;
import java.util.HashSet;
//package
import Civilisations.Civilisation;
import cases.Case;

public abstract class Guerre {
	
	private int idTypeGuerre;
	private Civilisation attaquant;
	private Civilisation defenseur;
	private String typeDeGuerre;
	private ArrayList<Case> frontAttaquant;
	private ArrayList<Case> frontDefenseur;
	private int soldatLibreAttaquant;
	private int soldatLibreDefenseur;
	
	public Guerre(int idTypeGuerre, Civilisation attaquant, Civilisation defenseur, String type) {
		this.idTypeGuerre = idTypeGuerre;
		this.attaquant = attaquant;
		this.defenseur = defenseur;
		this.typeDeGuerre = type;
		this.frontAttaquant = attaquant.getFrontWith(defenseur);
		this.frontDefenseur = defenseur.getFrontWith(attaquant);
		soldatLibreAttaquant = attaquant.getArmee();
		soldatLibreDefenseur = defenseur.getArmee();
		attaquant.addAggresiveWar(this);
		defenseur.addDefensiveWar(this);
		
		System.out.println(attaquant.getNom() + " rentre en guerre contre " + defenseur.getNom());
	}
	
	
	
	public void warAdvancement(int newArmeeAttaquant, int newArmeeDefenseur) {
		int totalA = 0;
		for(Case c : frontAttaquant) totalA += c.getLocalArmies();
		int totalD = 0;
		for(Case c : frontDefenseur) totalD += c.getLocalArmies();
		//System.out.println("attaquant:" + totalA + " " + attaquant.getArmee() + " " + attaquant.getPopulation() + " defenseur: " + totalD + " " + defenseur.getArmee() + " " + defenseur.getPopulation());
		
		update(newArmeeAttaquant, newArmeeDefenseur);
		repartitionArmees();
		attaque();
		contre_attaque();
		transfertDesCases();
		if(warConclusion() || (frontAttaquant.size() == 0 && frontDefenseur.size() == 0)) terminerGuerre();
	}
	
	
	//fonctions
	public abstract boolean warConclusion();
	
	
	public void update(int newArmeeAttaquant, int newArmeeDefenseur) {
		frontAttaquant.clear();
		frontDefenseur.clear();
		frontAttaquant = attaquant.getFrontWith(defenseur);
		frontDefenseur = defenseur.getFrontWith(attaquant);
		soldatLibreAttaquant = newArmeeAttaquant;
		soldatLibreDefenseur = newArmeeDefenseur;
		attaquant.setArmee(0);
		defenseur.setArmee(0);
	}
	
	public void attaque() {//charge, Bonzaiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii !
		for(Case c : frontAttaquant) {
			if(c.getLocalArmies() > 0) {
				ArrayList<Case> localFront = new ArrayList<Case>();
				for(Case ennemi : c.getVoisines()) {
					if(ennemi.getOwner() != null) if(ennemi.getOwner().equals(defenseur)) if(ennemi.getLocalArmies() >= 0) localFront.add(ennemi);
				}
				int totalPuissanceAttaquant = (int) Math.ceil(c.getLocalArmies() * attaquant.getEfficaciteArmee() / localFront.size());
				for(Case ennemi : localFront) {
					int totalPuissanceDefenseur = (int)(ennemi.getLocalArmies() * defenseur.getEfficaciteArmee() * ennemi.bonusArmee());
					//coté attaquant de la bataille
					int damageAtt = (totalPuissanceDefenseur / 2);
					int nbMortsAttaquant;
					if(damageAtt < 0) {
						nbMortsAttaquant = c.getLocalArmies() / localFront.size();
					}else {
						nbMortsAttaquant = (int) Math.ceil((damageAtt / attaquant.getEfficaciteArmee()));
					}
					//coté defenseur de la bataille
					int damageDef = (totalPuissanceAttaquant / 2);
					int nbMortsDefenseur;
					if(damageDef < 0) {
						nbMortsDefenseur = ennemi.getLocalArmies();
					}else {
						nbMortsDefenseur = (int) Math.ceil((damageDef / (defenseur.getEfficaciteArmee() * ennemi.bonusArmee())));
					}
					if(nbMortsAttaquant < 0) nbMortsAttaquant = 0;
					if(nbMortsDefenseur < 0) nbMortsDefenseur = 0;
					c.decreaseArmies(nbMortsAttaquant);
					ennemi.decreaseArmies(nbMortsDefenseur);
					
				}
			}
		}
	}
	
	public void contre_attaque() {//charge, Bonzaiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii !
		for(Case c : frontDefenseur) {
			if(c.getLocalArmies() > 0) {
				ArrayList<Case> localFront = new ArrayList<Case>();
				for(Case ennemi : c.getVoisines()) {
					if(ennemi.getOwner() != null) if(ennemi.getOwner().equals(attaquant)) if(ennemi.getLocalArmies() >= 0) localFront.add(ennemi);
				}
				int totalPuissanceDefenseur = (int) Math.ceil(c.getLocalArmies() * defenseur.getEfficaciteArmee() / localFront.size()); //c'est le defenseur qui attaque -_-
				for(Case ennemi : localFront) {
					int totalPuissanceAttaquant = (int)(ennemi.getLocalArmies() * attaquant.getEfficaciteArmee() * ennemi.bonusArmee());
					//coté attaquant de la bataille
					int damageDef = (totalPuissanceAttaquant / 2);
					int nbMortsDefenseur;
					if(damageDef < 0) {
						nbMortsDefenseur = c.getLocalArmies() / localFront.size();
					}else {
						 nbMortsDefenseur = (int) Math.ceil((damageDef / defenseur.getEfficaciteArmee()));
					}
					//coté defenseur de la bataille
					int damageAtt = (totalPuissanceDefenseur / 2);
					int nbMortsAttaquant;
					if(damageAtt < 0) {
						nbMortsAttaquant = ennemi.getLocalArmies();
					}else {
						nbMortsAttaquant = (int) Math.ceil((damageAtt / (attaquant.getEfficaciteArmee() * ennemi.bonusArmee())));
					}
					if(nbMortsDefenseur < 0) nbMortsDefenseur = 0;
					if(nbMortsAttaquant < 0) nbMortsAttaquant = 0;
					c.decreaseArmies(nbMortsDefenseur);
					ennemi.decreaseArmies(nbMortsAttaquant);
				}
			}
		}
	}
	
	public void transfertDesCases() {
		HashSet<Case> newCasePourAttaquant = new HashSet<>();
		HashSet<Case> newCasePourDefenseur = new HashSet<>();
		for(Case c : frontAttaquant) {
			if(c.getLocalArmies() > 0) {
				for(Case voisines : c.getVoisines()) {
					if(frontDefenseur.contains(voisines)) if(voisines.getLocalArmies() <= 0) newCasePourAttaquant.add(voisines); 
				}
			}
		}
		for(Case c : frontDefenseur) {
			if(c.getLocalArmies() > 0) {
				for(Case voisines : c.getVoisines()) {
					if(frontAttaquant.contains(voisines)) if(voisines.getLocalArmies() <= 0) newCasePourDefenseur.add(voisines); 
				}
			}
		}
		//réaffectation des cases
		frontAttaquant.removeAll(newCasePourDefenseur);
		attaquant.removeAllCase(newCasePourDefenseur);
		frontDefenseur.removeAll(newCasePourAttaquant);
		defenseur.removeAllCase(newCasePourAttaquant);
		attaquant.addAllCase(newCasePourAttaquant);
		defenseur.addAllCase(newCasePourDefenseur);
		
		//retrait des soldats qui ne sont mnt plus au front
		for(Case c : frontAttaquant) {
			if(c.checkEncerclement(attaquant)) {
				attaquant.addArmies(c.getLocalArmies());
				c.setArmies(0);
			}
		}
	}
	
	public void repartitionArmees() {
		if(frontAttaquant.size() > 0 && frontDefenseur.size() > 0) {
			int count = 0;
			//répartition la plus équitable possible des troupes au front
			while(soldatLibreAttaquant > 0) {
				count++;
				for(Case c : frontAttaquant) {
					if(c.getLocalArmies() < count) {
						if(c.checkEncerclement(defenseur) == false || frontAttaquant.size() <= 5) {//une case totalement encerclée ne peux recevoir de renforts a moins que ce soit parmis les dernières cases
							c.addArmies(1);
							soldatLibreAttaquant--;
						}
					}
				}
			}
			count = 0;
			while(soldatLibreDefenseur > 0) {
				count++;
				for(Case c : frontDefenseur) {
					if(c.getLocalArmies() < count) {
						if(c.checkEncerclement(attaquant) == false || frontDefenseur.size() <= 5) {//une case totalement encerclée ne peux recevoir de renforts a moins que ce soit parmis les dernières cases
							c.addArmies(1);
							soldatLibreDefenseur--;
						}
					}
				}
			}
		}
	}

	public void rappatriement() {
		for(Case c : frontAttaquant) {
			attaquant.addArmies(c.getLocalArmies());
			c.setArmies(0);
		}
		for(Case c : frontDefenseur) {
			defenseur.addArmies(c.getLocalArmies());
			c.setArmies(0);
		}
	}
	
	public String toString() {
		return("guerre " + typeDeGuerre + " opposant " + attaquant.getNom() + " à " + defenseur.getNom());
	}
	public String save() {return idTypeGuerre + " " + defenseur.getId();}
	
	
	public Civilisation getDefenseur() {return defenseur;}
	public String getTypeGuerre() {return typeDeGuerre;}
	//c'est la fin
	public void terminerGuerre() {
		rappatriement();
		attaquant.removeAggresiveWar(this);
		defenseur.removeDefensiveWar(this);
		this.finalize();
	}
	public void finalize(){
         System.out.println("La " + this + " est terminée");   
    }

	//LOAD
		public void createWar(Civilisation att, Civilisation def, int idTypeGuerre) {
			switch (idTypeGuerre) {
			case 0: new AnnihilationWar(att, def);
			case 1: new AgressionWar(att, def);
			case 2: new AnnihilationWar(att, def);

			default:
				break;
			}
		}
}
