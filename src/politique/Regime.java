package politique;

import politique.PolTerritoriale;

public abstract class Regime {

	private int id;
	private String suffixe;
	private String nom;
	private int baseBonheur;
	private double baseEfficaciteArmee;
	private double tauxArmeeAttendu;
	private int stabilite;
	private PolTerritoriale politiqueTerritoriale;
	private double soutienDeGuere;
	private boolean canDeclareWar;
	
	
	public Regime(int id, String suffixe, String nom, int baseBonheur, double baseEfficaciteArmee, double tauxArmeeAttendu, int stabilite, PolTerritoriale polTerrClassic, double souteinDeGuerre, boolean canDeclareWar) {
		this.id = id;
		this.suffixe = suffixe;
		this.nom = nom;
		this.baseBonheur = baseBonheur;
		this.baseEfficaciteArmee = baseEfficaciteArmee;
		this.tauxArmeeAttendu = tauxArmeeAttendu;
		this.stabilite = stabilite;
		this.politiqueTerritoriale = polTerrClassic;
		this.soutienDeGuere = souteinDeGuerre;
		this.canDeclareWar = canDeclareWar;
	}
	
	public String toString() {return (suffixe + nom);}

	public int getId() {return id;}
	public String getNomComplet() {return (suffixe + nom);}
	public String getNom() {return nom;}
	public int getBaseBonheur() {return baseBonheur;}
	public double getEfficaciteArmee() {return baseEfficaciteArmee;}
	public double getTauxArmee() {return tauxArmeeAttendu;}
	public int getStabilite() {return stabilite;}
	public PolTerritoriale getPolTerritoriale() {return politiqueTerritoriale;}
	public double getSoutienDeGuerre() {return soutienDeGuere;}
	public boolean canDeclareWar() {return canDeclareWar;}
}


/*
Regimes id :
0: Anarchie
1: Autocratie
2: Democratie
3: Republique
4: Dictate
5: Etat policier
6: Etat Militaire
7: Monarchie
8: Tyranie


Anarchistes, //bordel infâme, merdier sans nom, effets randoms et changeants...

Républiquains, //dirigeant élu par le peuple (inverse de la monarchie),    bonheur++
Démocrates, //le gouvernement du peuple, par le peuple, pour le peuple, bonheur++++

Monarchistes, //le roi a des pouvoirs mais pas tout ni tout seul, bonheur+

Dictateurs, //le gouvernement fait ce qu'il veux,           bonheur--
Tyrans,    //le tyran fait ce qu'il veux, abuse du peuple, bonheur-----

Autocrate-Despotes, //despote = père de famille, il fait ce qu'il veux mais pour le bien de tous (~= monarchie absolue), bonheur++

Policiers,  //la police controle le peuple, 				  bonheur---
Militaires, //l'armée choisi le dirigeant quand elle veut..., bonheur---
*/
