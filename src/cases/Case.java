package cases;

import java.awt.Color;
import java.util.ArrayList;

import Civilisations.Civilisation;
import cases.*;

public class Case {
	
	private static Case[][] monde;
	private static int tailleX;
	private static int tailleY;
	private static ArrayList<Civilisation> civilisations;
	
	//Nocif,Noséabond, Actif, Haride,Brulant,Chaud, Sec, Humide,Luxiuriante,Abondante,Abondant,Dense, Ensoleillé,Tempéré,Frais,Froid, Enneigé, Glacé
	private static Climat[] climatMarais = {Climat.Nocif,Climat.Noséabond,Climat.Abondant};
	private static Climat[] climatDesert = {Climat.Haride,Climat.Brulant,Climat.Chaud,Climat.Chaud,Climat.Chaud,Climat.Sec};
	private static Climat[] climatJungle = {Climat.Humide,Climat.Luxiuriante,Climat.Abondante,Climat.Abondante,Climat.Dense,Climat.Dense};
	private static Climat[] climatForet = {Climat.Abondante,Climat.Dense};
	private static Climat[] climatOcean = {Climat.Abondant,Climat.Glacé,Climat.Froid,Climat.Froid,Climat.Froid,Climat.Froid};
	private static Climat[] climatPrairie = {Climat.Ensoleillé,Climat.Tempéré,Climat.Froid,Climat.Sec};
	//Chevaux,Bétail,Chiens,Chats,Rongeurs,Insectes,Loups,Baleines,Poissons,Crabe,Cervidés,Elephants
	private static Animaux[] animauxDesert = {Animaux.Elephants, Animaux.Chats, Animaux.Rongeurs};
	private static Animaux[] animauxPrairie = {Animaux.Chevaux, Animaux.Cervidés, Animaux.Bétail};
	private static Animaux[] animauxOceanProfond = {Animaux.Baleines, Animaux.Poissons};
	private static Animaux[] animauxOcean = {Animaux.Baleines, Animaux.Poissons, Animaux.Poissons, Animaux.Crabe};
	private static Animaux[] animauxForet = {Animaux.Loups, Animaux.Insectes, Animaux.Chats, Animaux.Chiens, Animaux.Cervidés};
	private static Animaux[] animauxJungle = {Animaux.Insectes, Animaux.Chats, Animaux.Rongeurs};
	//Uranium,Or,Pétrole,Fer,Charbon,Plomb,Cuivre,Aluminium,Argent,Cacao,Coton,Epices,Diamants,Marbre,Perles, Sel, Soie, Sucre,Teinture,Vin
	private static StrategicRessource[] ressourcePlaine = {StrategicRessource.Uranium, StrategicRessource.Or, StrategicRessource.Pétrole, StrategicRessource.Charbon, StrategicRessource.Plomb, StrategicRessource.Cuivre, StrategicRessource.Aluminium, StrategicRessource.Argent, StrategicRessource.Cacao, StrategicRessource.Coton, StrategicRessource.Epices, StrategicRessource.Marbre, StrategicRessource.Soie, StrategicRessource.Sucre, StrategicRessource.Teinture, StrategicRessource.Vin};
	private static StrategicRessource[] ressourceColline = {StrategicRessource.Uranium, StrategicRessource.Or, StrategicRessource.Pétrole, StrategicRessource.Charbon, StrategicRessource.Plomb, StrategicRessource.Cuivre, StrategicRessource.Aluminium, StrategicRessource.Argent, StrategicRessource.Cacao, StrategicRessource.Coton, StrategicRessource.Epices, StrategicRessource.Diamants, StrategicRessource.Marbre, StrategicRessource.Soie, StrategicRessource.Sucre, StrategicRessource.Teinture, StrategicRessource.Vin};
	private static StrategicRessource[] ressourceMontagne = {StrategicRessource.Uranium, StrategicRessource.Or, StrategicRessource.Charbon, StrategicRessource.Plomb, StrategicRessource.Cuivre, StrategicRessource.Aluminium, StrategicRessource.Argent, StrategicRessource.Diamants};
	private static StrategicRessource[] ressourcePlage = {StrategicRessource.Perles, StrategicRessource.Sel};
	private static StrategicRessource[] ressourceOcean = {StrategicRessource.Perles, StrategicRessource.Sel};
	
	private int coordX;
	private int coordY;
	private Climat climat;
	private Relief relief;
	private Biome biome;
	private boolean crossable;
	private int nourriture;
	private ArrayList<StrategicRessource> ressourcesStrat;
	private ArrayList<Animaux> ressourcesAnimal;
	private Color couleur;
	private Civilisation owner;
	private int science;
	private int potentielAnimal;
	private double note;
	private boolean haveFreeFrontiere;
	private ArrayList<Civilisation> claims;
	private int nbSoldats;
	
	//variables temporaires (pour load notement)
	private ArrayList<Integer> tempClaimsInt;
	private int tempIdOwner;
	
	
	//builder
	public Case(Relief relief, Biome biome, int x, int y) {
		this.relief = relief;
		this.biome = biome;
		this.owner = null;
		coordX = x;
		coordY = y;
		ressourcesAnimal = new ArrayList<>();
		ressourcesStrat = new ArrayList<>();
		nourriture = 1;
		science = 0;
		generateClimat();
		generateCrossable();
		populate();        //installation des animaux
		generateCrossable();
		installRessource();
		mixColor();
		createStatsCase(); //installation de la nourriture, science
		note = noter();
		haveFreeFrontiere = true;
		claims = new ArrayList<Civilisation>();
		nbSoldats = 0;
		
	}
	
	
	//loader builder
	//Case laCase = new Case(x, y, idOwner, idRelief, idBiome, idClimat, potentielleAnimal, animaux, ressources, claims, nbSoldats);
	public Case(int x, int y, int idOwner, int idRelief, int idBiome, int idClimat,  ArrayList<Animaux> animaux, ArrayList<StrategicRessource> ressources, ArrayList<Integer> claims, int nbSoldats) {
		this.coordX = x;
		this.coordY = y;
		this.tempIdOwner = idOwner;
		this.relief = createIdRelief(idRelief);
		this.biome = createIdBiome(idBiome);
		this.climat = Climat.values()[idClimat];
		this.ressourcesAnimal = new ArrayList<Animaux>();
		this.ressourcesStrat = new ArrayList<StrategicRessource>();
		this.tempClaimsInt = new ArrayList<Integer>();
		ressourcesAnimal.addAll(animaux);
		ressourcesStrat.addAll(ressources);
		tempClaimsInt.addAll(claims);
		this.nbSoldats = nbSoldats;
		generateCrossable();
		mixColor();
		createStatsCase();
		note = noter();
	}
	
	
	//fonctions pour la génération de la case.
		//climat
		private void generateClimat() {
			Climat climat;
			switch (biome.getClass().getSimpleName()) {
			case "Marais":
				climat = climatMarais[(int)(Math.random()*climatMarais.length)];
				break;
			case "Desert":
				climat = climatDesert[(int)(Math.random()*climatDesert.length)];
				break;
			case "Jungle":
				climat = climatJungle[(int)(Math.random()*climatJungle.length)];
				break;
			case "Foret":
				climat = climatForet[(int)(Math.random()*climatForet.length)];
				break;
			case "Ocean":
				climat = climatOcean[(int)(Math.random()*climatOcean.length)];
				break;
			case "OceanProfond":
				climat = climatOcean[(int)(Math.random()*climatOcean.length)];
				break;
			case "Prairie":
				climat = climatPrairie[(int)(Math.random()*climatPrairie.length)];
				break;

			default:
				climat = Climat.Frais;
				break;
			}
			
			
			this.climat = climat;
		}
		
		//crossable
		private void generateCrossable() {
			crossable = (biome.getCrossable() == true && relief.getCrossable() == true) ? true : false;
			if(relief.getId() <= 4 || relief.getId() == 10) crossable = false;
			int trueTotal = 0;
			trueTotal += (biome.getCrossable()) ? 1 : 0;
			trueTotal += (relief.getCrossable()) ? 1 : 0;
			crossable = (trueTotal == 0) ? true : false;
		}
		
		//création des stats de la case (science + nourriture)
		private void createStatsCase() {
			//d'abord le climat
			//impactes avec : Nocif,Noséabond, Actif, Haride, Sec,Luxiuriante,Abondante,Abondant,Dense, Enneigé, Glacé
			switch (climat) {
				case Nocif:
					science += 2;
					potentielAnimal = 0;
					break;
				case Noséabond:
					science += 1;
					potentielAnimal = 1;
					break;
				case Actif:
					science += 5;
					potentielAnimal = 0;
					break;
				case Haride:
					science += 1;
					potentielAnimal = 2;
					break;
				case Sec:
					science += 1;
					potentielAnimal = 2;
					break;
				case Luxiuriante:
					science += 3;
					nourriture += 5;
					potentielAnimal = 4;
					break;
				case Abondante:
					science += 2;
					nourriture += 4;
					potentielAnimal = 3;
					break;
				case Abondant:
					science += 2;
					nourriture += 4;
					potentielAnimal = 5;
					break;
				case Dense:
					science += 1;
					potentielAnimal = 2;
					break;
				case Enneigé:
					science += 1;
					potentielAnimal = 1;
					break;
				case Glacé:
					science += 3;
					potentielAnimal = 1;
					break;
	
				default:
					potentielAnimal = 1;
					break;
			}
			
			//au tour du biome d'impacter
			//Marais,Desert,Jungle,Foret,Ocean,Plaine,Volcan
			switch (biome.getClass().getSimpleName()) {
				case "Marais":
					nourriture += 6;
					science += 2;
					break;
				case "Desert":
					nourriture += 3;
					science += 2;
					break;
				case "Jungle":
					nourriture += 10;
					science += 3;
					break;
				case "Foret":
					nourriture += 6;
					science += 2;
					break;
				case "Ocean":
					nourriture += 4;
					science += 2;
				case "OceanProfond":
					nourriture += 6;
					science += 3;
					break;
				case "Prairie":
					nourriture += 4;
					science += 1;
					break;
				case "Volcan":
					science += 5;
					break;
				case "Tundra":
					science += 2;
					break;
	
				default:
					break;
				}
			
			//au tour du relief d'impacter
			//OceanProfond,Ocean,Mer,Lac,Montagne,Plage
			switch (relief.getClass().getSimpleName()) {
				case "Marais":
					nourriture += 4;
					science += 2;
					break;
				case "Desert":
					nourriture += 0;
					science += 2;
					break;
				case "Jungle":
					nourriture += 4;
					science += 3;
					break;
				case "Foret":
					nourriture += 3;
					science += 2;
					break;
				case "Ocean":
					nourriture += 3;
					science += 2;
				case "OceanProfond":
					nourriture += 3;
					science += 3;
					break;
				case "Prairie":
					nourriture += 2;
					science += 1;
					break;
				case "Plage":
					nourriture += 1;
					science += 1;
					break;
	
				default:
					break;
				}
			
			
		}
		
		private void populate() {
			switch (biome.getClass().getSimpleName()) {
			case "Desert":
				for(int nb = 0; nb < potentielAnimal; nb++) {
					if(Math.random() > 0.33) {
						ressourcesAnimal.add(animauxDesert[(int)(Math.random()*animauxDesert.length)]);
						nourriture++;
					}
				}
				break;
			case "Jungle":
				for(int nb = 0; nb < potentielAnimal; nb++) {
					if(Math.random() > 0.33) {
						ressourcesAnimal.add(animauxJungle[(int)(Math.random()*animauxJungle.length)]);
						nourriture++;
					}
				}
				break;
			case "Foret":
				for(int nb = 0; nb < potentielAnimal; nb++) {
					if(Math.random() > 0.33) {
						ressourcesAnimal.add(animauxForet[(int)(Math.random()*animauxForet.length)]);
						nourriture++;
					}
				}
				break;
			case "Ocean":
				for(int nb = 0; nb < potentielAnimal; nb++) {
					if(Math.random() > 0.33) {
						ressourcesAnimal.add(animauxOcean[(int)(Math.random()*animauxOcean.length)]);
						nourriture++;
					}
				}
			case "OceanProfond":
				for(int nb = 0; nb < potentielAnimal; nb++) {
					if(Math.random() > 0.33) {
						ressourcesAnimal.add(animauxOceanProfond[(int)(Math.random()*animauxOceanProfond.length)]);
						nourriture++;
					}
				}
				break;
			case "Prairie":
				for(int nb = 0; nb < potentielAnimal; nb++) {
					if(Math.random() > 0.33) {
						ressourcesAnimal.add(animauxPrairie[(int)(Math.random()*animauxPrairie.length)]);
						nourriture++;
					}
				}
			default:
				break;
			}
		}
		
		//mélange de la couleur du relief et du biome (au choix)
		private void mixColor() {
			Color finalColor = new Color(0,0,0);
			if(relief.getId() != -1) {//si pas Neant
				finalColor = (biome.getId() == -1) ? relief.getColor() : biome.getColor(); //si biome == None, sinon
			}
			couleur = finalColor;
		}
		
		//notation de la case
		private double noter() {
			double noter = (double)(potentielAnimal)/4 + (double)(nourriture)/4 + (double)(science)/4; //max 5/10pts environ
			//impact du relief
				if(relief.getClass().getSimpleName() == "Plaine") noter += 2;
				if(relief.getClass().getSimpleName() == "Coline") noter += 1;
				if(relief.getClass().getSimpleName() == "Falaise") noter = 20;
				if(relief.getClass().getSimpleName() == "Plage") noter = 30;
			//climats méliorateurs : Abondant Abondante Tempéré Ensoleillé Chaud Frais Dense
				if(climat == Climat.Abondant || climat == Climat.Abondante || climat == Climat.Tempéré || climat == Climat.Ensoleillé) noter += 4;
				if(climat == Climat.Chaud || climat == Climat.Frais || climat == Climat.Dense) noter += 2;
			//impact du biome
				if(biome.getClass().getName() == "Prairie") noter += 2.5;
				if(biome.getClass().getName() == "Jungle") noter += 1;
				if(biome.getClass().getName() == "Foret") noter += 1.5;
				if(biome.getClass().getName() == "Lac") noter += 1;
			//impact des animaux
				if(howManyAnimal(Animaux.Chats)) noter += 0.75;
				if(howManyAnimal(Animaux.Chiens)) noter += 1;
				if(howManyAnimal(Animaux.Chevaux)) noter += 1;
				if(howManyAnimal(Animaux.Insectes)) noter -= 0.5;
				if(howManyAnimal(Animaux.Rongeurs)) noter -= 0.5;
				if(howManyAnimal(Animaux.Bétail)) noter += 1;
			noter += ressourcesStrat.size()/3;
			return noter;
		}
		
		//installation des ressources
		private void installRessource() {
			if(getStringRelief().equals("Plaine")) {
				for(int nb = 0; nb < 5; nb++) {
					if(Math.random() > 0.5) {
						ressourcesStrat.add(ressourcePlaine[(int)(Math.random()*ressourcePlaine.length)]);
						science++;
					}
				}
			}
			if(getStringRelief().equals("Colline")) {
				for(int nb = 0; nb < 3; nb++) {
					if(Math.random() > 0.5) {
						ressourcesStrat.add(ressourceColline[(int)(Math.random()*ressourceColline.length)]);
						science++;
					}
				}
			}
			if(getStringRelief().equals("Montagne")) {
				if(Math.random() > 0.5) {
					ressourcesStrat.add(ressourceMontagne[(int)(Math.random()*ressourceMontagne.length)]);
					science++;
				}
			}
			if(getStringRelief().equals("Plage")) {
				for(int nb = 0; nb < 2; nb++) {
					if(Math.random() > 0.5) {
						ressourcesStrat.add(ressourcePlage[(int)(Math.random()*ressourcePlage.length)]);
						science++;
					}
				}
			}
			if(getStringRelief().equals("Ocean") || getStringRelief().equals("OceanProfond")) {
				for(int nb = 0; nb < 2; nb++) {
					if(Math.random() > 0.5) {
						ressourcesStrat.add(ressourceOcean[(int)(Math.random()*ressourceOcean.length)]);
						science++;
					}
				}
			}
		}
	
	//fct
		public String toString() {
			//pas oublier pour le string.format, %d pour des int et %s pour ds String
			return String.format("%s avec %s %s. Cetta case apporte %d nourriture, %d science et %d ressources stratégiques, note : %g", relief.getClass().getSimpleName(), biome.getClass().getSimpleName(), getClimat(), nourriture, science, ressourcesStrat.size(), note);
		}
		public boolean howManyAnimal(Animaux comparaison) {
			for(Animaux bestiole : ressourcesAnimal) {
				if(bestiole == comparaison) {
					return true;
				}
			}
			return false;
		}
		public void updateStats() {
			createStatsCase();
			generateCrossable();
			generateClimat();
			mixColor();
			installRessource();
			populate();
			noter();
		}
		
		//determination des voisins
		public ArrayList<Case> getVoisines(){
			ArrayList<Case> voisines = new ArrayList<>();
			int x, y;
			x = coordX -1; y = coordY;
			if(coordX < 0) coordX = tailleX-1;
			if(x >= 0) {
				voisines.add(monde[x][y]);
			}
			x = coordX +1;
			if(coordX > tailleX) coordX = 0;
			if(x < tailleX) {
				voisines.add(monde[x][y]);
			}
			
			x = coordX; y = coordY -1;
			if(coordY < 0) coordY = tailleY-1;
			if(y >= 0) {
				voisines.add(monde[x][y]);
			}
			y = coordY +1;
			if(coordY > tailleY) coordY = 0;
			if(y < tailleY) {
				voisines.add(monde[x][y]);
			}
			return voisines;
		}
		public ArrayList<Case> getVoisinesLibres(){
			ArrayList<Case> voisines = new ArrayList<>();
			for(Case c : getVoisines()) if(c.caseLibre()) voisines.add(c);
			haveFreeFrontiere = (voisines.size() > 0) ? true : false;
			return voisines;
		}
		public boolean checkEncerclement(Civilisation civ) {
			for(Case c : getVoisines()) {
				if(c.getOwner() == null) {
					return false;
				}else if(c.getOwner().equals(civ) == false) return false;
			}
			return true;
		}
		public int tailleEncerclementTerrestre(Civilisation civ) {
			int nb = 0;
			if(getStringRelief().equals("Ocean") == false && getStringRelief().equals("OceanProfond") == false) {
				for(Case c : getVoisines()) {
					if(civ.equals(c.getOwner())) nb++;
				}
			}
			return nb;
		}
		
		public static void setMap(Case[][] map, int x, int y) {
			monde = map;
			tailleX = x;
			tailleY = y;
		}
		public static void setCivs(ArrayList<Civilisation> civs) {civilisations = civs;}
		
	//getters and setters
		//infos case
			public void alterTerrain(Relief nRelief, Biome nBiome) {
				relief = nRelief;
				biome = nBiome;
				generateCrossable();
				generateClimat();
			}
			public void alterTerrain(Relief nRelief, Biome nBiome, int nIdOwner) {
				relief = nRelief; 
				biome = nBiome; 
				owner = civilisations.get(nIdOwner); 
				civilisations.get(nIdOwner).addCase(this);
				generateCrossable();
				generateClimat();
			}
			
			public int getX() {return coordX;}
			public int getY() {return coordY;}
			public Color getColor() {return couleur;}
			public Civilisation getOwner() {return owner;}
			public String getStringRelief() {return relief.getClass().getSimpleName();}
			public String getStringBiome() {return biome.getClass().getSimpleName();}
			public Climat getClimat() {return climat;}
			public void setClimat(Climat cli) {climat = cli;}
			public int getFood() {return nourriture;}
			public int getScience() {return science;}
			public void setFood(int newFood) {nourriture = newFood;}
			public double note() {return note;}
			public boolean caseLibre() {return owner == null;}
			public boolean getCrossable() {return crossable;}
			public void changeOwnerTo(Civilisation newCiv) {
				if(owner != null) owner.removeCase(this);
				owner = newCiv;
				newCiv.addCase(this);
			}
			public void setOwnerTo(Civilisation newCiv) {owner = newCiv;}
			public void removeOwner() {owner = null;}
		//voisinnage
			public boolean haveFreeFrontiere() {
				haveFreeFrontiere = false;
				for(Case c : this.getVoisines()) {
					if(c.getOwner().equals(null)) haveFreeFrontiere = true;
				}
				return haveFreeFrontiere;
			}
			public void liberate() {
				for(Case c : getVoisines()) c.setFreeFrontiere();
				owner = null;
				nbSoldats = 0;
				
			}
			public void setFreeFrontiere() {haveFreeFrontiere = true;}
		//guerres
			public ArrayList<Civilisation> getClaims(){return claims;}
			public int getLocalArmies() {return nbSoldats;}
			public void addArmies(int nb) {nbSoldats += nb;}
			public void setArmies(int nb) {nbSoldats = nb;}
			public void decreaseArmies(int nb) {nbSoldats -= nb; if(nbSoldats < 0) nbSoldats = 0;}
			public double bonusArmee() {return relief.getArmeeBonus() * biome.getArmeeBonus();}
		//ressources
			public ArrayList<StrategicRessource> getRessources() {return ressourcesStrat;}
			public ArrayList<Animaux> getAnimaux() {return ressourcesAnimal;}
			
		//LOADING
			public Relief createIdRelief(int id) {
				Relief relief = null;
				switch (id) {
				case 0: relief = new OceanProfond();break;
				case 1: relief = new Ocean();break;
				case 2: relief = new Ocean_de_Glace();break;
				case 3: relief = new Mer();break;
				case 4: relief = new Litoral();break;
				case 5: relief = new Lac();break;
				case 6: relief = new Plage();break;
				case 7: relief = new Falaise();break;
				case 8: relief = new Plaine();break;
				case 9: relief = new Montagne();break;
				case 10: relief = new Montagne();break;

				default: relief = new Neant();break;
				}
				return relief;
			}
			public Biome createIdBiome(int id) {
				Biome biome = null;
				switch (id) {
				case 0: biome = new Prairie();break;
				case 1: biome = new Foret();break;
				case 2: biome = new Jungle();break;
				case 3: biome = new Tundra();break;
				case 4: biome = new Volcan();break;
				case 5: biome = new Marais();break;
				case 6: biome = new Desert();break;

				default:biome = new None();break;
				}
				return biome;
			}
			public void installCivs() {
				//owner = (tempIdOwner == -1) ? null : civilisations.get(tempIdOwner);
				if(tempIdOwner == -1) {
					owner = null;
				}else {
					owner = civilisations.get(tempIdOwner);
					civilisations.get(tempIdOwner).setCase(this);
				}
				for(Integer id : tempClaimsInt) claims.add(civilisations.get(id));
			}
		//SAVE
			public String[] save() {
				int taille = 4; //1 pour tt les infos, +1pour le nb d'animaux + animaux, +1 nb ressources + ressources, +1 nb claims + claims
				String[] saveCase = new String[taille];
				saveCase[0] = (coordX + " " + coordY + " " + ((owner != null) ? owner.getId() : -1) + " " + relief.getId() + " " + biome.getId() + " " + climat.ordinal() + " " + nbSoldats);
				saveCase[1] = String.valueOf(ressourcesAnimal.size());
				for(Animaux a : ressourcesAnimal) saveCase[1] += " " + a.ordinal();
				saveCase[2] = String.valueOf(ressourcesStrat.size());
				for(StrategicRessource r : ressourcesStrat) saveCase[2] += " " + r.ordinal();
				saveCase[3] = String.valueOf(claims.size());
				for(Civilisation civ : claims) saveCase[3] += " " + civ.getId();
				
				return saveCase;
			}
			
		
}