package Civilisations;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;

import Guerre.AgressionWar;
import Guerre.AnnihilationWar;
import Guerre.Guerre;
import cases.Animaux;
import cases.Case;
import cases.StrategicRessource;
import cultures.Basique;
import cultures.Culture;
import cultures.Germanique;
import politique.PT_Aggressifs;
import politique.PT_Lents;
import politique.PT_Necessitaires;
import politique.PT_Normaux;
import politique.PT_Rapides;
import politique.PolTerritoriale;
import politique.R_Anarchistes;
import politique.R_Autocratie;
import politique.R_Democratie;
import politique.R_Dictate;
import politique.R_Etat_Militaire;
import politique.R_Etat_Policier;
import politique.R_Monarchie;
import politique.R_Republiquains;
import politique.R_Tyranie;
import politique.Regime;

public class Civilisation {
	
	private static String[] noms = {"la Russie", "le Japon", "l'Allemagne", "la Moldavie", "l'Algérie", "la Bosnie", "la Hongrie", "la Chine", "la Corée", "le Luxembourg", "l'Argentine", "l'Espagne", "l'Italie", "la Pologne"};
	private static Case[][] monde;
	private static ArrayList<Civilisation> civilisations = new ArrayList<Civilisation>();
	private static int tailleX;
	private static int tailleY;
	
	private static int tailleArmee = 5;
	private static int nourritureParArmee = 7;
	
	private int id;
	private String nom;
	private Color couleur;
	private int population;
	private int populationColoniale;
	private int production;
	private int economie; //valeur representant l'argent en stock
	private int influence; //efficacite de la converssion des voisins
	private int bonheur;
	private int stabilite; // de 0 - 100, pouvant entrainer un changement de régime (La direction n'est pas responsable de toute gêne occasionée)
	private Culture culture;
	private int armee;
	private int scienceLvl; //valeur (abstraite) du niveau de dev scientifique
	private int science;  //nb de science avant le prochain niveau
	private int nourriture;
	private Ideologie ideologie;
	private Regime regimePolitique;
	private PolTerritoriale politiqueTerritoriale;
	private double noteMinimale; //note minimal qu'une case doit avoir pour que cette civ puisse y résider, Desertion si note <note-2
	private ArrayList<Case> caseOwned;
	private ArrayList<StrategicRessource> ressourcesStrat;
	private ArrayList<Animaux> ressourcesAnimal;
	private ArrayList<Guerre> aggresiveWar;
	private ArrayList<Guerre> defensiveWar;
	private HashSet<Civilisation> voisins;
	private boolean enFamine;
	private double efficaciteArmee;
	private double tauxEducation;
	private double tauxCroissance;
	private double tauxAccroissementScience;
	private double tauxArmeeAttendu;
	private double baseEfficaciteArmee;
	private int baseScience;
	private int baseBonheur;
	private boolean alive = true;
	
	
	
	//base builder
	public Civilisation(int id, String nameNullForRandom, Case firstCase, Color couleur, Culture culture, Ideologie ideologie, Regime regime, PolTerritoriale polT) {
		
		this.id = id;
		this.culture = culture;
		this.ideologie = ideologie;
		this.regimePolitique = regime;
		this.politiqueTerritoriale = polT;
		this.couleur = new Color(couleur.getRed(), couleur.getGreen(), couleur.getBlue(), 150);
		nom = (nameNullForRandom != null) ? nameNullForRandom : noms[(int)(Math.random()*noms.length)];
		population = 10;
		populationColoniale = 0;
		production = 0;
		economie = 0;
		influence = 0;
		armee = 0;		//1armée = 15pop
		scienceLvl = 1;
		science = 10; //decrementation de la science pour passer au lvl supp
		nourriture = 50;
		caseOwned = new ArrayList<>();
		ressourcesStrat = new ArrayList<>();
		ressourcesAnimal = new ArrayList<>();
		aggresiveWar = new ArrayList<>();
		defensiveWar = new ArrayList<>();
		voisins = checkVoisins();
		enFamine = false;
		tauxEducation = 0;
		tauxCroissance = 0.05; // =2.5x taux mondiale IRL
		tauxAccroissementScience = 1.15;
		baseScience = 10;
		baseBonheur = 10;
		baseEfficaciteArmee = 1;
		noteMinimale = firstCase.note();
		addCase(firstCase);
		for(Case c : firstCase.getVoisinesLibres()) addCase(c);
		impactRegime();
		calculerBonheur();
		calculerEffiArmee();
		
	}

	//TODO remove this testing builder
	public Civilisation(int id, String nameNullForRandom, Case firstCase, Color couleur, Ideologie ideologie, Regime regime, PolTerritoriale polT) {
		
		this.id = id;
		this.culture = new Basique();
		this.ideologie = ideologie;
		this.regimePolitique = regime;
		this.couleur = new Color(couleur.getRed(), couleur.getGreen(), couleur.getBlue(), 150);
		//this.couleur = new Color(couleur.getRed(), couleur.getGreen(), couleur.getBlue());
		nom = (nameNullForRandom != null) ? nameNullForRandom : noms[(int)(Math.random()*noms.length)];
		population = 10;
		populationColoniale = 0;
		production = 0;
		economie = 0;
		influence = 0;
		armee = 0;
		scienceLvl = 1;
		science = 10; //decrementation de la science pour passer au lvl supp
		nourriture = 50;
		caseOwned = new ArrayList<>();
		ressourcesStrat = new ArrayList<>();
		ressourcesAnimal = new ArrayList<>();
		aggresiveWar = new ArrayList<>();
		defensiveWar = new ArrayList<>();
		voisins = checkVoisins();
		enFamine = false;
		tauxEducation = 0;
		tauxCroissance = 0.02; // =1x taux mondiale IRL
		tauxAccroissementScience = 1.2;
		baseScience = 10;
		baseBonheur = 10;
		baseEfficaciteArmee = 1;
		noteMinimale = firstCase.note();
		addCase(firstCase);
		for(Case c : firstCase.getVoisinesLibres()) addCase(c);
		impactRegime();
		this.politiqueTerritoriale = polT;
		calculerBonheur();
		calculerEffiArmee();
		
	}
	
	//loading builder
		public Civilisation(int id, boolean alive, String nom, int couleurR, int couleurG, int couleurB, int couleurA, int cultureId, int  ideologie, int regime, int polT,
				int population, int popColo, int production, int economie, int influence, int armee, int scienceLvl, int science, int nourriture, int bonheur, int stabilite,
				double efficaciteArmee, double tauxArmeeAttendu, double tauxEducation, double tauxCroissance, double tauxAccroissementScience, int baseBonheur,
				int baseScience, double baseEfficaciteArmee, double noteMinimale) {
			
			StringBuffer buf = new StringBuffer(nom);
			for(int pos = 0; pos < nom.length(); pos++) {
				if(nom.charAt(pos) == '_') buf.setCharAt(pos, ' ');
			}
			nom = buf.toString();
			
			this.id = id;
			this.alive = alive;
			this.nom = nom;
			couleur = new Color(couleurR, couleurB, couleurB, couleurA);
			this.population = population;
			this.populationColoniale = popColo;
			this.production = production;
			this.economie = economie;
			this.influence = influence;
			this.bonheur = bonheur;
			this.stabilite = stabilite;
			//TODO cultureID
			this.culture = culture;
			this.armee = armee;
			this.scienceLvl = scienceLvl;
			this.science = science;
			this.nourriture = nourriture;
			this.ideologie = Ideologie.values()[ideologie];
			this.regimePolitique = createRegimePerId(regime);
			this.politiqueTerritoriale = createPolTPerId(polT);
			this.noteMinimale = noteMinimale;
			ressourcesStrat = new ArrayList<StrategicRessource>();
			ressourcesAnimal = new ArrayList<Animaux>();
			aggresiveWar = new ArrayList<Guerre>();
			defensiveWar = new ArrayList<Guerre>();
			caseOwned = new ArrayList<Case>();
			voisins = new HashSet<Civilisation>();
			this.efficaciteArmee = efficaciteArmee;
			this.tauxEducation = tauxEducation;
			this.tauxCroissance = tauxCroissance;
			this.tauxAccroissementScience = tauxAccroissementScience;
			this.tauxArmeeAttendu = tauxArmeeAttendu;
			this.baseEfficaciteArmee = baseEfficaciteArmee;
			this.baseScience = baseScience;
			this.baseBonheur = baseBonheur;
			
		}
	//EarthMap builder
	public Civilisation(int id, boolean alive, String nom, int couleurR, int couleurG, int couleurB, int couleurA, int cultureId, int  ideologie, int regime, int polT,
			int population, int popColo,  int production, int economie, int scienceLvl, int nourriture, int bonheur, int stabilite) {
		
		this.id = id;
		this.alive = alive;
		this.nom = nom;
		couleur = new Color(couleurR, couleurG, couleurB, couleurA);
		this.population = population;
		this.populationColoniale = popColo;
		this.production = production;
		this.economie = economie;
		this.influence = 0;
		this.bonheur = bonheur;
		this.baseBonheur = bonheur;
		this.stabilite = stabilite;
		//TODO cultureID
		this.culture = createCulturePerId(cultureId);
		this.armee = 0;
		this.scienceLvl = scienceLvl;
		this.science = 0;
		this.nourriture = nourriture;
		this.ideologie = Ideologie.values()[ideologie];
		this.regimePolitique = createRegimePerId(regime);
		this.politiqueTerritoriale = createPolTPerId(polT);
		this.noteMinimale = 3.0;
		ressourcesStrat = new ArrayList<StrategicRessource>();
		ressourcesAnimal = new ArrayList<Animaux>();
		aggresiveWar = new ArrayList<Guerre>();
		defensiveWar = new ArrayList<Guerre>();
		caseOwned = new ArrayList<Case>();
		voisins = new HashSet<Civilisation>();
		this.efficaciteArmee = regimePolitique.getEfficaciteArmee();
		this.tauxEducation = 0.75;
		this.tauxCroissance = 0.05;
		this.tauxAccroissementScience = 1.15;
		this.tauxArmeeAttendu = regimePolitique.getTauxArmee();
		this.baseEfficaciteArmee = 1;
		this.baseScience = 10;
		this.baseBonheur = baseBonheur;
		
	}
	
	public void updateCivilisation() {
		if(alive) {
			calculerBonheur();
			calculerEffiArmee();
			jouerAnarchie();
			recolteNourriture();
			nourrireLePeuple();
			croissancePopulation();
			reglerFamine();
			verifDecesCivilisation();
			updateScience();
			applicationRessourcesStrat();
			calculerClimatHabitable();
			expansion();
			gestionArmee();
			gestionGuerres();
			/*for(Civilisation civ : voisins) {
				String wish = "false";
				if(dejaEnGuerreContre(civ.getId()) == false) wish = wishDeclareWarTo(civ);
				if(wish != "false") declareWarTo(civ, wish);
			}*/
			
			//if(nom.contains("Allemagne")) { for(String str : saveStats()) System.out.println(str);}
			
			
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//TODO fct de gestion de la civ
		private void recolteNourriture() {
			//1pop recolte toute la case
			int tempNourr = 0;
			int totalPop = population + populationColoniale;
			int limite = (totalPop < caseOwned.size()) ? totalPop : caseOwned.size();
			for(int id = 0; id < limite; id++) {
				tempNourr += caseOwned.get(id).getFood();
			}
			//animaux
			tempNourr += howManyAnimal(Animaux.Bétail) * 10;    //Ca mange de l'herbe, ça se tente.
			tempNourr += howManyAnimal(Animaux.Cervidés) * 7.5; //CARIBOUUUUUUUUU !!
			tempNourr += howManyAnimal(Animaux.Crabe) * 2;      //ca pique les pied, a mort!!
			tempNourr += howManyAnimal(Animaux.Elephants) * 30; //polution sonore
			tempNourr += howManyAnimal(Animaux.Moutons) * 5;    //kébab landais
			tempNourr += howManyAnimal(Animaux.Poissons) * 5;   //croustibat
			tempNourr += howManyAnimal(Animaux.Chevaux) * 7;    //lasagnes
			tempNourr += howManyAnimal(Animaux.Baleines) * 50;  //*bruit de baleine virile*
			//aplication des bonus
			tempNourr *= (1 + (0.15 *  scienceLvl) + (0.05 * (bonheur - baseBonheur))); // +15% par scienceLvl +5% par bonheur excedentaire, -5% par bonheur négatif
			
			nourriture += tempNourr;
			//bonus de conservation grâce au sel
			if(howManyRessource(StrategicRessource.Sel) > 0 && nourriture > 0) nourriture *= 1.10;
			//limitation de stockage
			int stockageMax = caseOwned.size() * 100 * scienceLvl;
			if(nourriture > stockageMax) nourriture = stockageMax;
		}
		
		private void nourrireLePeuple() {
			//1pop mange 1nourriture et 1armee mange 7nourriture
			int totalDepense = (int)((population * 1) + (populationColoniale * 0.8) + ((armee + armeeDeployee()) * nourritureParArmee));
			nourriture -= totalDepense;
			enFamine = (nourriture > 0) ? false : true;
		}

		private void croissancePopulation() {
			if(nourriture > 0 && population + populationColoniale > 0) {
				//System.out.println("sci:" + scienceLvl + " bonheur:" + bonheur + " baseBonheur:" + baseBonheur);
				int totalPop = population + populationColoniale + armee*tailleArmee; //les soldats qui ne sont pas partis participent aussi
				double tauxBonus = (0.00001 * scienceLvl) + (0.00025 * (bonheur - baseBonheur)); //0.05 +0.001 par scienceLvl; +0.025 par bonheur
				int totalPopPrevu = (int) Math.ceil(totalPop * (tauxCroissance + tauxBonus) / 12); //croissance de la population par mois 
				double ratioPop = populationColoniale / (population + populationColoniale);
				for(int num = 0; num < totalPopPrevu; num++) {
					if(Math.random() < ratioPop) populationColoniale++; else population++;
				}
			}
			if(population < 0) population = 0;
			if(populationColoniale < 0) populationColoniale = 0;
		}
		
		private void reglerFamine() {
			if(enFamine && population + populationColoniale > 0) {
				System.out.println(nom + " crie famine!");
				int totalPop = population + populationColoniale;
				int mortsMax = (int) (totalPop * 0.33); //seul 33% de la population est autorisée a mourrir chaque année
				double ratioPop = populationColoniale / (population + populationColoniale);
				while(nourriture <= 0 && mortsMax > 0) {
					 if(Math.random() > 0.75 && armee > 0) {
						nourriture += nourritureParArmee*2;
						armee--;
						mortsMax -= tailleArmee;
					}else {
					 	nourriture += 2;
					 		if(Math.random() < 0.1 || populationColoniale <= 0) population--; else populationColoniale--;
					 	mortsMax--;
					}
				}
			}
			if(population < 0 && populationColoniale > 0) {
				if(-2*population <= populationColoniale) { populationColoniale += 2*population; population *= -1; }
				if(-population <= populationColoniale) { populationColoniale += population; population *= -1; }
			}
			if(population < 0) population = 0;
			if(populationColoniale < 0) populationColoniale = 0;
			
		}
		
		private void verifDecesCivilisation() {
			if((population + populationColoniale) <= 0 || caseOwned.size() <= 0) this.kill();
		}
		
		private void updateScience() {
			int limite = (population < caseOwned.size()) ? population : caseOwned.size();
			int recherche = 0;
			double bonus = 1;
			bonus += (howManyRessource(StrategicRessource.Uranium) > 0) ? 0.05 : 0;
			bonus += (howManyRessource(StrategicRessource.Aluminium) > 0) ? 0.01 : 0;
			bonus += (howManyRessource(StrategicRessource.Cuivre) > 0) ? 0.025 : 0; //éléctricité
			bonus += (howManyRessource(StrategicRessource.Or) > 0) ? 0.025 : 0; // éléctronique
			bonus += (aggresiveWar.size() + defensiveWar.size()) * 0.15; //15% de bonus par guerre en cours
			if(regimePolitique.getNom().equals("les Anarchistes") == false) bonus /= 100;
			for(int id = 0; id < limite; id++) {
				recherche += caseOwned.get(id).getScience();
			}
			science -= (recherche * bonus); //rapelle, science est le decompte pour un nouveau lvl
			while(science <= 0) { 
				scienceLvl++;
				science += Math.floor(baseScience*Math.pow(tauxAccroissementScience, scienceLvl -1));
			}
		}
		
		//TODO application ressources
		private void applicationRessourcesStrat() {
			//ressources indiqué d'un * n'ont pas encore les effets en place (sans compter science)
			//effet customs : Uranium*,Or*,Fer,Sel,Coton,
			//effet impossibls pour le moment : Pétrole*,Charbon*,Plomb*,Cuivre*,Aluminium*,Marbre*,
			//bonheur uniquement : Argent,Cacao,Epices,Sucre,Teinture,Vin,Soie,Perles,Diamants,
			
			
			
			
		}
		
		public void calculerBonheur() {
			bonheur = baseBonheur;
			//ressources : Argent,Cacao,Coton,Epices,Sucre,Teinture,Vin,Soie,Perles,Diamants
			bonheur += howManyRessource(StrategicRessource.Argent) + howManyRessource(StrategicRessource.Cacao) + howManyRessource(StrategicRessource.Coton) + howManyRessource(StrategicRessource.Epices) + howManyRessource(StrategicRessource.Sucre) + howManyRessource(StrategicRessource.Teinture) + howManyRessource(StrategicRessource.Vin) + howManyRessource(StrategicRessource.Soie) + howManyRessource(StrategicRessource.Perles) + howManyRessource(StrategicRessource.Diamants);
			if(enFamine) bonheur -= 30;
			bonheur -= populationColoniale / 50;
		
			
		}
		
		public void calculerEffiArmee() {
			efficaciteArmee = baseEfficaciteArmee;
			//ressources : Fer,Teinture
			efficaciteArmee += (howManyRessource(StrategicRessource.Teinture) > 0) ? 1 : 0; //camouflage
			efficaciteArmee += (howManyRessource(StrategicRessource.Fer) > 0) ? 3 : 0; //lames
			efficaciteArmee += (howManyRessource(StrategicRessource.Pétrole) > 0) ? 5 : 0; //motorisation
			efficaciteArmee += scienceLvl * 0.25;
			efficaciteArmee += (double)(bonheur)/5;
			if(enFamine) efficaciteArmee /= 1.5;
			if(populationColoniale > population) efficaciteArmee *= 0.8; //les armée coloniales sont moins bien entrainées, entrainant un malus.
		}
		
		public double calculerClimatHabitable() {
			noteMinimale = 7.5; //valeur de base
			noteMinimale -= scienceLvl/10;
			if(howManyRessource(StrategicRessource.Coton) > 0) noteMinimale -= 0.5;
			if(howManyAnimal(Animaux.Moutons) > 0) noteMinimale -= 1;
			return noteMinimale;
		}
		
		public void expansion() {
			////si nécessitaire
			ArrayList<Case> frontiere = getFreeFrontiere();
			if(frontiere.isEmpty() == false) {
				if(politiqueTerritoriale.getNom() == "nécessitaires") {
					//recuperer une case en fonction des besoins (ils peuvent donc coloniser pls cases par tours
					//si manque de nourriture pour les 1 prochaines années
					int nbMax = 5;
					if(populationColoniale > 0) nbMax += populationColoniale / 100;
					while(frontiere.isEmpty() == false && nbMax > 0 && (nourriture + caseOwned.size() * 3) - (1*(population + armee*nourritureParArmee)) < 0){
						//System.out.println("nourr:" + nourriture + " population:" + population + " armee:" + armee);
						Case nouvelle = frontiere.get(0);
						for(Case c : frontiere) {
							if(c.getFood() > nouvelle.getFood() && c.note() >= noteMinimale) nouvelle = c;
						}
						addCase(nouvelle);
						frontiere.remove(nouvelle);
						nbMax--;
					}
					//si les réserves diminuent
					/*if(nourriture - ancienNourriture <= 0 && frontiere.isEmpty() == false) {
						Case nouvelle = frontiere.get(0);
						for(Case c : frontiere) {
							if(c.getFood() > nouvelle.getFood()) nouvelle = c;
						}
						addCase(nouvelle);
						frontiere.remove(nouvelle);
					}*/
					//si science trop lente
					if(science / (caseOwned.size()) > 40) {
						Case nouvelle = frontiere.get(0);
						for(Case c : frontiere) {
							if(c.getScience() > nouvelle.getScience() && c.note() >= noteMinimale) nouvelle = c;
						}
						addCase(nouvelle);
						while(frontiere.contains(nouvelle)) frontiere.remove(nouvelle);
					}
				}else {
				////ou pas
					double nbProbable = politiqueTerritoriale.getNbNewCase();
					int nbNewCase = ((double)(nbProbable - (int)(nbProbable)) == 0) ? (int)(nbProbable) : (int)(Math.round(nbProbable + (Math.random() -0.5))); //si c'est pas un multiple de 1 c'est (exemple) 1.5 donc random pour savoir si 0 ou 1
					if(populationColoniale > 0) nbNewCase += populationColoniale / 100;
					for(int num = 0; num < nbNewCase; num++) {
						if(frontiere.isEmpty() == false) { //on retire des case, verifier qu'il en reste encore
							Case nouvelle = frontiere.get(0);
							for(Case c : frontiere) {
								if(c.note() + 2*(c.getVoisinesLibres().size() ) > nouvelle.note() + 2*(nouvelle.getVoisinesLibres().size()) && c.note() >= noteMinimale) nouvelle = c; //valorisation des cases encerclées
							}
							addCase(nouvelle);
							while(frontiere.contains(nouvelle)) frontiere.remove(nouvelle);
						}
					}
					if(frontiere.isEmpty() == false) {
						for(Case c : frontiere) {
							if(c.checkEncerclement(this)) addCase(c);
							if(c.tailleEncerclementTerrestre(this) > 3) addCase(c);
						}
					}
				}
			}
		}
		
		public AnarchieEvent jouerAnarchie() {
			if(regimePolitique.getNom() == "les Anarchistes") {
				bonheur = -10;
				efficaciteArmee = 1; //on considere que l'armée saura tenter de defendre le pays malgré l'anarchie environnante.
				AnarchieEvent event = new AnarchieEvent();
				//exemple :
					//population *= 0.75; //les émeutes aident à la séléction 'naturelle' : 25% des gens meurent
					//nourriture brulée
				return event;
			}
			return null;
		}
		
		public void gestionArmee() {
			if(population + populationColoniale > 0) {
				double totalArmee = (armee + armeeDeployee())*tailleArmee;
				double totalPop = population + populationColoniale;
				double ratioPop = populationColoniale / (population + populationColoniale);
				//recrutement
				if(totalArmee / (totalPop + totalArmee) < tauxArmeeAttendu) {
					int nbNewArmees = 0;
					while((totalArmee / (totalPop + totalArmee) < tauxArmeeAttendu) && (nbNewArmees < regimePolitique.getEfficaciteArmee()*5) && population - tailleArmee > 0){
						nbNewArmees++;
						armee++;
						totalArmee += tailleArmee;
						if(Math.random() < ratioPop) { //on enrole soit une armée de soldtas originaires du pays soit des colonies (en fct du ratio)
							populationColoniale -= tailleArmee;
							totalPop -= tailleArmee;
						}else {
							population -= tailleArmee;
							totalPop -= tailleArmee;
						}
					}
				}else {
					//démobillisation
					while((totalArmee-tailleArmee) / (totalPop + totalArmee-tailleArmee) > tauxArmeeAttendu) {
						armee--;
						totalArmee -= tailleArmee;
						if(Math.random() < ratioPop) { //on demobilise soit une armée de soldtas originaires du pays soit des colonies (en fct du ratio)
							populationColoniale += tailleArmee;
						}else {
							population += tailleArmee;
						}
					}
				}
			}
		}
		
		public void gestionGuerres() {
			try {
				for(Guerre war : aggresiveWar) {
					//on passe en paramètre les soldats répartits, pour chaque guerres
					war.warAdvancement((int) Math.floor(armee / (defensiveWar.size() + aggresiveWar.size())), (int) Math.floor(war.getDefenseur().getArmee() / (war.getDefenseur().getDefensiveWars().size() + war.getDefenseur().getAggresiveWars().size())));
				}
			}catch(Exception e) {}
		}
		
		
		public String wishDeclareWarTo(Civilisation victime) {
			String declaration = "false";
			//TODO inclure les relations diplomatiques
			//simulation des relations, 10chance /10000 de déclarer la guerre
			int relation = (int) Math.random()*1000;
			
			if(relation < 10) {
				//jalousie
				if(canDeclareWar() && economie < victime.getEconomie() && regimePolitique.canDeclareWar()) {
					if(armee > victime.getArmee()) { //ne prend pas en compte l'efficacite de l'armée, laisse la possibilitée de se faire éclater
						//declaration = "Agression";
						declaration = "Annihilation";
					}
				}
				
				//supperiorité
				if(canDeclareWar() && regimePolitique.getNom().equals("Etat Policer") || regimePolitique.getNom().equals("Etat Militaire") || regimePolitique.getNom().equals("Dictate")) {
					if(scienceLvl > 5 && armee >= victime.getArmee() * 0.15) {
						declaration = "Annihilation";
					}
				}
			}
			return declaration;
		}
		
		public void declareWarTo(Civilisation defenseur, String typeGuerre) {
			switch (typeGuerre) {
			case "Annihilation": //combat jusqu'a la mort
				aggresiveWar.add(new AnnihilationWar(this, defenseur));
				break;
			case "Aggression": //on le tape pour le plaisir, si on peux lui piquer des trucs en plus c'est bien
				aggresiveWar.add(new AgressionWar(this, defenseur));
				break;
			case "Conquette": //combat pour le controle d'une partie ou tout le territoire
				aggresiveWar.add(new AnnihilationWar(this, defenseur));
				break;
			case "Reconquette": // recuperer un territoire perdu
				aggresiveWar.add(new AnnihilationWar(this, defenseur));
				break;

			default:
				System.out.println("Type de guerre non reconnue, déclaration annulée");
				break;
			}
			//degraderRelations();
		}
		
		
		
		
		
	//fct
		public void impactRegime() {
			//régime politique
				baseBonheur = regimePolitique.getBaseBonheur();
				baseEfficaciteArmee = regimePolitique.getBaseBonheur();
				tauxArmeeAttendu = regimePolitique.getTauxArmee();
				stabilite = regimePolitique.getStabilite();
			//séléction de la politique d'expansion
				politiqueTerritoriale = regimePolitique.getPolTerritoriale();
		}
		public Regime createRegimePerId(int id) {
			Regime regime = null;
			switch (id) {
			case 1: regime = new R_Autocratie();break;
			case 2: regime = new R_Democratie();break;
			case 3: regime = new R_Republiquains();break;
			case 4: regime = new R_Dictate();break;
			case 5: regime = new R_Etat_Policier();break;
			case 6: regime = new R_Etat_Militaire();break;
			case 7: regime = new R_Monarchie();break;
			case 8: regime = new R_Tyranie();break;

			default: regime = new R_Anarchistes();break;
			}
			return regime;
		}
		public PolTerritoriale createPolTPerId(int id) {
			PolTerritoriale polT = null;
			switch (id) {
			case 0: polT = new PT_Necessitaires();
			case 1: polT = new PT_Lents();
			case 3: polT = new PT_Rapides();
			case 4: polT = new PT_Aggressifs();

			default: polT = new PT_Normaux();
			}
			return polT;
		}
		public Culture createCulturePerId(int id) {
			Culture culture = null;
			switch (id) {
			case 1: culture = new Germanique();

			default: culture = new Basique();
			}
			return culture;
		}
	
	//TODO Geters and Setters
			public void cheat() {
				nourriture += population*10;
				armee += population/2;
				population += armee*tailleArmee;
			}
		
			public void die() {alive = false;}
			public int getId() {return id;}
			public int getArmee() {return armee;}
			public void setArmee(int nb) {armee = nb;}
			public int getNourriture() {return nourriture;}
			public int getPopulation() {return population;}
			public int getBonheur() {return bonheur;}
			public int getEconomie() {return economie;}
			public String getNom() {return nom;}
			public String toString() {
				return (nom + ", " + regimePolitique + " " + ideologie + ", expansionnistes " + politiqueTerritoriale + ", contrôleur de " + caseOwned.size() + " cases");
			}
		//territoire
			public int getNbCase() {return caseOwned.size();}
			public void addCase(Case newCase) {
				population++;
				caseOwned.add(newCase);
				ressourcesAnimal.addAll(newCase.getAnimaux());
				ressourcesStrat.addAll(newCase.getRessources());
				newCase.setOwnerTo(this);
				//bonus, on a conquis on a tout recup
				nourriture += newCase.getFood()*2;
				science -= newCase.getScience()*1;
				if(newCase.getStringRelief().equals("Falaise") || newCase.getStringRelief().equals("Plage")) {
					for(Case c : newCase.getVoisinesLibres()) {
						if(c.getStringBiome().equals("None")) addCase(c);
						//if(c.getStringRelief().equals("Litoral") || c.getStringRelief().equals("Mer")  || c.getStringRelief().equals("Lac") || c.getStringRelief().equals("Ocean_de_Glce") || c.getStringRelief().equals("Ocean") || c.getStringRelief().equals("OceanProfond")) addCase(c);
					}
				}
				checkVoisins();
			}
			public void setCase(Case newCase) {
				caseOwned.add(newCase);
				ressourcesAnimal.addAll(newCase.getAnimaux());
				ressourcesStrat.addAll(newCase.getRessources());
				newCase.setOwnerTo(this);
			}
			public void addAllCase(HashSet<Case> newCases) {
				for(Case newCase : newCases) addCase(newCase);
			}
			public void removeCase(Case lostCase) {
				while(caseOwned.contains(lostCase)) {
					caseOwned.remove(lostCase);
					ressourcesAnimal.removeAll(lostCase.getAnimaux());
					ressourcesStrat.removeAll(lostCase.getRessources());
				}
				checkVoisins();
			}
			public void removeAllCase(HashSet<Case> casesPerdues) {
				for(Case lostCase : casesPerdues) removeCase(lostCase);
			}
			public ArrayList<Case> getCase() {return caseOwned;}
			public ArrayList<Case> getFreeFrontiere(){
				ArrayList<Case> frontiere = new ArrayList<>();
				for(Case c : caseOwned) {
					//parcours des cases voisines directes
					for(Case voisin : c.getVoisinesLibres()) {
						if(voisin.getCrossable() == true || voisin.getStringRelief().equals("Lac") || voisin.getStringRelief().equals("Montagne")) frontiere.add(voisin);
					}
				}
				return frontiere;
			}
		//relations étrangères
			public boolean alive() {return alive;}
			public void setPolitiqueTerritoriale(PolTerritoriale polT) {politiqueTerritoriale = polT;}
			public void setIdeologie (Ideologie ideo) {ideologie = ideo;}
			public void removeAggresiveWar(Guerre g) {aggresiveWar.remove(g);}
			public void removeDefensiveWar(Guerre g) {defensiveWar.remove(g);}
			public void addAggresiveWar(Guerre g) {aggresiveWar.add(g);}
			public void addDefensiveWar(Guerre g) {defensiveWar.add(g);}
			public HashSet<Civilisation> getVoisins(){return voisins;}
			public HashSet<Civilisation> checkVoisins(){
				voisins = new HashSet<>(); voisins.clear();
				for(Case c : caseOwned) {
					for(Case frotntiere : c.getVoisines()) {
						Civilisation owner = frotntiere.getOwner();
						if(owner != null) if(this.equals(owner) == false && voisins.contains(owner) == false) voisins.add(owner);
					}
				}
				return voisins;
			}
		//guerres
			public boolean canDeclareWar() {
				if(population + populationColoniale > 0) {
					return (((armee + armeeDeployee()) * tailleArmee / (population + populationColoniale) > tauxArmeeAttendu/4) && stabilite >= 40 && regimePolitique.getSoutienDeGuerre() > 0.25);
				}else return false;
			}
			public boolean dejaEnGuerreContre(int id) {
				for(Guerre war : aggresiveWar) {
					if(war.getDefenseur().getId() == id) return true;
				}
				return false;
			}
			public double getEfficaciteArmee() {return efficaciteArmee;}
			public void decreaseArmee(int quantite) {armee = (armee - quantite < 0) ? 0 : armee - quantite;}
			public void decreaseBonheur(int quantite) { bonheur -= quantite;}
			public ArrayList<Case> getFrontWith(Civilisation ennemie){
				ArrayList<Case> front = new ArrayList<Case>();
				for(Case c : caseOwned) {
					int totalCaseFrontiereEnnemie = 0;
					for(Case voisine : c.getVoisines()) {
						if(ennemie.equals(voisine.getOwner())) totalCaseFrontiereEnnemie++;
					}
					
					if(totalCaseFrontiereEnnemie > 0) {
						front.add(c);
					}else {
						armee += c.getLocalArmies();
						c.setArmies(0);
					}
					
				}
				return front;
			}
			public ArrayList<Guerre> getAggresiveWars() {return aggresiveWar;}
			public ArrayList<Guerre> getDefensiveWars() {return defensiveWar;}
			public Regime getRegime() {return regimePolitique;}
			public void addPopulation(int nbPop) {population += nbPop;}
			
			
			public void addArmies(int nb) {armee += nb;}
			public int armeeDeployee() {
				int total = 0;
				for(Case c : caseOwned) total += c.getLocalArmies();
				return total;
			}
		//ressources
			public int howManyRessource(StrategicRessource toFound) {
				int nb = 0;
				if(scienceLvl +3 >= toFound.ordinal()) { //position de la ressource(dans l'enum) < au lvlScientifique, +3 (lvl1 de base et [0]) pour que les 5premieres soit dispo dès le debut
					for(StrategicRessource ressource : ressourcesStrat) {
						if(ressource == toFound) nb++;
					}
				}
				return nb;
			}
			public int howManyAnimal(Animaux toFound) {
				int nb = 0;
				if(scienceLvl +3 >= toFound.ordinal()) {
					for(Animaux animal : ressourcesAnimal) {
						if(animal == toFound) nb++;
					}
				}
				return nb;
			}
		//map
		public static void setMap(Case[][] map, int x, int y) {
			monde = map;
			tailleX = x;
			tailleY = y;
		}
		public static void setCiv(ArrayList<Civilisation> civ) {civilisations = civ;}
		
			public Color getColor() {return couleur;}
			
			
		public boolean equals(Civilisation civ) {
			if(civ == null) return false;
			return (this.toString().equals(civ.toString()));
		}
		//SAVE
		public String[] saveStats() {
			String[] saveCiv = new String[3];
			//int id, boolean alive, String nom, int couleurR, int couleurG, int couleurB, int couleurA, int cultureId, int  ideologie, int regime, int polT,
			//int population, int production, int economie, int influence, int armee, int scienceLvl, int science, int nourriture, int bonheur, int stabilite,
			//double efficaciteArmee, double tauxArmeeAttendu, double tauxEducation, double tauxCroissance, double tauxAccroissementScience, int baseBonheur, int baseScience, double baseEfficaciteArmee, double noteMinimale
			
			StringBuffer buf = new StringBuffer(nom);
			for(int pos = 0; pos < nom.length(); pos++) {
				if(nom.charAt(pos) == ' ') buf.setCharAt(pos, '_');
			}
			String tempNom = buf.toString();
			
			saveCiv[0] = id + " " + alive + " " + tempNom + " " + couleur.getRed() + " " + couleur.getGreen() + " " + couleur.getBlue() + " " +  couleur.getAlpha() + " " + culture.getId() + " " + ideologie.ordinal() + " " + regimePolitique.getId() + " " + politiqueTerritoriale.getId();
			saveCiv[1] = population + " " + populationColoniale + " " + production + " " + economie + " " + influence + " " + armee + " " + scienceLvl + " " + science + " " + nourriture + " " + bonheur + " " + stabilite;
			saveCiv[2] = efficaciteArmee + " " + tauxArmeeAttendu + " " + tauxEducation + " " + tauxCroissance + " " + tauxAccroissementScience + " " + baseBonheur + " " + baseScience + " " + baseEfficaciteArmee + " " + noteMinimale;
			return saveCiv;
		}
		public String[] saveWars() {
			String wars[] = new String[aggresiveWar.size() +1];
			wars[0] = String.valueOf(aggresiveWar.size());
			for(int count = 0; count < aggresiveWar.size(); count++) wars[count+1] = aggresiveWar.get(count).save();
			return wars;
		}
	//LOAD
		public void checkCiv() {
			voisins = checkVoisins();
		}
		
	//TODO extinction de la civilisation
		public void kill() {
			for(Guerre war : aggresiveWar) {
				war.finalize();
			}
			for(Guerre war : defensiveWar) {
				war.finalize();
			}
			for(Case c : caseOwned) {
				c.liberate();
			}

			System.out.println(nom + " n'a pas su s'adapter aux nombreux dangers environnants, l'Histoire oublie les faibles.");
			
			for(String str : saveStats()) System.out.println(str);
			//finalisation de la mort
			this.die();
			System.out.println("Une civilisation c'est éteinte...");
			//finalize(); //bloque la sauvegarde et case les relations avec les autres (du à la recherche de la civ par id dans l'arraylist
		}
		public void finalize() {}
}
