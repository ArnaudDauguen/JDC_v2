package tests;

//librairies externes
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.sun.xml.internal.messaging.saaj.util.SAAJUtil;

import Civilisations.Civilisation;
import Civilisations.Ideologie;
import Guerre.Guerre;
import cases.*;
import gui.Frame;
import map.EarthMap;
import map.Map;
import politique.PT_Aggressifs;
import politique.PT_Lents;
import politique.PT_Necessitaires;
import politique.PT_Normaux;
import politique.R_Democratie;
import politique.R_Etat_Militaire;
import politique.R_Etat_Policier;
import politique.R_Monarchie;
import politique.R_Tyranie;

public class GenerateRadomMap {
	
	private static Frame frame;
	private static Map map;
	private static ArrayList<Civilisation> civilisations = new ArrayList<Civilisation>();
	private static int date = 0;
	private static int vitesse = (int) (1000/4); // = 1000/nb mois par sec
	private static String nameSave = "";
	
	private static String[] months = {"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octore", "Novembre", "Decembre"};
	
	private static boolean play = true;
	
	
	public static void main(String[] args) {
		
		frame = new Frame();
		
		//load("terre1936.txt");
		//generateNewRandomMap();
		creatEarth();
		
		
		
		//gestation du monde
		wait(5000);
		while (civilisations.size() > 0 || play) {
			wait(vitesse);
			//date
			System.out.println(months[date%12] + " " + (1936 + date/12));
			for(Civilisation civ : civilisations) {
				if(civ != null) civ.updateCivilisation();
				// Déclarations de guerres forcée, semi historiques
				//japon
				if(date == 12+9) if(civ.getId() == 7) { civ.declareWarTo(civilisations.get(12), "Annihilation");}
				if(date == 12+12) if(civ.getId() == 7) { civ.declareWarTo(civilisations.get(11), "Annihilation");}
				if(date == 12+12+12+12+12+12+12) if(civ.getId() == 7) { civ.declareWarTo(civilisations.get(1), "Annihilation");}
				//allemagne
				if(date == 12+12+12+4) if(civ.getId() == 5) { civ.declareWarTo(civilisations.get(8), "Annihilation");}
				if(date == 12+12+12+4) if(civ.getId() == 4) { civ.declareWarTo(civilisations.get(8), "Annihilation");}
				if(date == 12+12+12+11) if(civ.getId() == 4) { civ.declareWarTo(civilisations.get(9), "Annihilation");}
				if(date == 12+12+12+12+2) if(civ.getId() == 4) { civ.declareWarTo(civilisations.get(10), "Annihilation");}
				if(date == 12+12+12+12+7) if(civ.getId() == 4) { civ.declareWarTo(civilisations.get(3), "Annihilation");}
				if(date == 12+12+12+12+7) if(civ.getId() == 5) { civ.declareWarTo(civilisations.get(3), "Annihilation");}
				if(date == 12+12+12+12+7) if(civ.getId() == 6) { civ.declareWarTo(civilisations.get(3), "Annihilation");}
				
				if(date == 12+12+12+12+12+7) if(civ.getId() == 4) { civ.declareWarTo(civilisations.get(2), "Annihilation");}
				if(date == 12+12+12+12+12+12+12) if(civ.getId() == 4) { civ.declareWarTo(civilisations.get(1), "Annihilation");}
				//russie
				if(date == 12+12+12+12+8) if(civ.getId() == 1) { civ.declareWarTo(civilisations.get(18), "Annihilation");}
				if(date == 12+12+12+12+12+12+5) if(civ.getId() == 1) { civ.declareWarTo(civilisations.get(4), "Annihilation");}
				if(date == 12+12+12+12+12+12+5) if(civ.getId() == 1) { civ.declareWarTo(civilisations.get(5), "Annihilation");}
				if(date == 12+12+12+12+12+12+12+1) if(civ.getId() == 1) { civ.declareWarTo(civilisations.get(11), "Annihilation");}
				//chine
				if(date == 12+12+8) if(civ.getId() == 12) { civ.declareWarTo(civilisations.get(11), "Annihilation");}
				//mexique
				if(date == 12+12+12+12+12+12+12+3) if(civ.getId() == 13) { civ.declareWarTo(civilisations.get(0), "Annihilation");}
				
				
			}
			map.repaint();
			//if(date == 0) save("save");
			/*if(date == 0) {
				load("terre1936.txt");
				vitesse = 100;
				map.repaint();
			}*/
			//if(date %12 == 0) armageddon();
			
			
			date++; //compteur de mois
		}
			
			
		
		

	}
	//create new map
	public static void generateNewRandomMap() {
		
		
		map = new Map(0, new ArrayList<Civilisation>(), frame); //Génération avec les paramètres de base SINON : frame, tailleX, tailleY, nbOceans, tailleOceans, niveau_mer

		//init pour les autres classes
		civilisations = map.getCiv();
		frame.setCiv(civilisations);
		Civilisation.setCiv(civilisations);
		Civilisation.setMap(map.getMonde(), map.getTailleX(), map.getTailleY());
		Case.setMap(map.getMonde(), map.getTailleX(), map.getTailleY());
		
		int[] coord = getFreeRandomLocation();
		Civilisation civ1 = new Civilisation(0, null, map.getMonde()[coord[0]][coord[1]], new Color(0, 250, 250), Ideologie.Royalistes, new R_Etat_Militaire(), new PT_Lents());
		coord = getFreeRandomLocation();
		Civilisation civ2 = new Civilisation(1, null, map.getMonde()[coord[0]][coord[1]], new Color(250, 250, 0), Ideologie.Royalistes, new R_Etat_Militaire(), new PT_Lents());
		coord = getFreeRandomLocation();
		Civilisation civ3 = new Civilisation(2, null, map.getMonde()[coord[0]][coord[1]], new Color(250, 0, 250), Ideologie.Royalistes, new R_Democratie(), new PT_Lents());
		coord = getFreeRandomLocation();
		Civilisation civ4 = new Civilisation(3, null, map.getMonde()[coord[0]][coord[1]], new Color(250, 250, 250), Ideologie.Royalistes, new R_Etat_Militaire(), new PT_Lents());
		coord = getFreeRandomLocation();
		Civilisation civ5 = new Civilisation(4, null, map.getMonde()[coord[0]][coord[1]], new Color(120, 120, 120), Ideologie.Communistes, new R_Etat_Militaire(), new PT_Aggressifs());
		coord = getFreeRandomLocation();
		Civilisation civ6 = new Civilisation(5, null, map.getMonde()[coord[0]][coord[1]], new Color(30, 30, 30), Ideologie.Communistes, new R_Etat_Militaire(), new PT_Aggressifs());
		
		civilisations.add(civ1);
		civilisations.add(civ2);
		civilisations.add(civ3);
		civilisations.add(civ4);
		civilisations.add(civ5);
		civilisations.add(civ6);
		
	}
	public static void creatEarth() {
		
		map = new Map(frame);

		//init pour les autres classes
		civilisations = map.getCiv();
		frame.setCiv(civilisations);
		Civilisation.setCiv(civilisations);
		Civilisation.setMap(map.getMonde(), map.getTailleX(), map.getTailleY());
		Case.setMap(map.getMonde(), map.getTailleX(), map.getTailleY());
		
		int tailleX = map.getTailleX();
		int tailleY = map.getTailleY();
		Case[][] carte = new Case[tailleX][tailleY];
		//initialisation full ocean
		for(int x = 0; x < tailleX; x++) {
			for(int y = 0; y < tailleY; y++) {
				carte[x][y] = new Case(new OceanProfond(), new None(), x, y);
			}
		}
		
		
		
		
		EarthMap em = new EarthMap(tailleX, tailleY);
		//update
			carte = em.getMap();
			civilisations = em.getCivs();
			frame.setCiv(civilisations);
			Civilisation.setCiv(civilisations);
			Civilisation.setMap(em.getMap(), tailleX, tailleY);
			Case.setMap(em.getMap(), tailleX, tailleY);
		//update de la map
		for(int x = 0; x < tailleX; x++) {
			for(int y = 0; y < tailleY; y++) {
				carte[x][y].updateStats();
			}
		}
		map.changeCarte(carte);
		frame.setTrueMap(carte, tailleX, tailleY);
		Civilisation.setMap(carte, tailleX, tailleY);
		Case.setMap(carte, tailleX, tailleY);
		/*
		int[] coord = getFreeRandomLocation();
		Civilisation civ1 = new Civilisation(0, null, map.getMonde()[coord[0]][coord[1]], new Color(0, 250, 250), Ideologie.Royalistes, new R_Etat_Militaire(), new PT_Lents());
		coord = getFreeRandomLocation();
		Civilisation civ2 = new Civilisation(1, null, map.getMonde()[coord[0]][coord[1]], new Color(250, 250, 0), Ideologie.Royalistes, new R_Etat_Militaire(), new PT_Lents());
		coord = getFreeRandomLocation();
		Civilisation civ3 = new Civilisation(2, null, map.getMonde()[coord[0]][coord[1]], new Color(250, 0, 250), Ideologie.Royalistes, new R_Democratie(), new PT_Lents());
		coord = getFreeRandomLocation();
		Civilisation civ4 = new Civilisation(3, null, map.getMonde()[coord[0]][coord[1]], new Color(250, 250, 250), Ideologie.Royalistes, new R_Etat_Militaire(), new PT_Lents());
		coord = getFreeRandomLocation();
		Civilisation civ5 = new Civilisation(4, null, map.getMonde()[coord[0]][coord[1]], new Color(120, 120, 120), Ideologie.Communistes, new R_Etat_Militaire(), new PT_Aggressifs());
		coord = getFreeRandomLocation();
		Civilisation civ6 = new Civilisation(5, null, map.getMonde()[coord[0]][coord[1]], new Color(30, 30, 30), Ideologie.Communistes, new R_Etat_Militaire(), new PT_Aggressifs());
		
		civilisations.add(civ1);
		civilisations.add(civ2);
		civilisations.add(civ3);
		civilisations.add(civ4);
		civilisations.add(civ5);
		civilisations.add(civ6);
		*/
	}
	//SAVE & LOAD
	public static void save(String type) {
		File file;
		FileWriter fw;
		Calendar calendrier = Calendar.getInstance();
		try {
			String name = type + " " + calendrier.get(Calendar.HOUR_OF_DAY) + "h" + calendrier.get(Calendar.MINUTE) + " - " + calendrier.get(Calendar.DATE) + "-" + calendrier.get(Calendar.MONTH) + "-" + calendrier.get(Calendar.YEAR) + ".txt";
			System.out.println(name);
			nameSave = name;
			file = new File(name);
			fw = new FileWriter(file);
			String retour = System.getProperty("line.separator");
			
			//écriture du nombre de casesX, Y, civs et date:
	        fw.write(map.getTailleX() + " " + map.getTailleY() + " " + civilisations.size() + " " + date + retour);
			
	        //écriture des civilisations
	        for(Civilisation civ : civilisations) {
	        	String[] values = civ.saveStats();
        		for(String str : values) fw.write(str + retour);
        		fw.write(retour);
	        }
	        
	        //écriture des cases
	        for(int y = 0; y < map.getTailleY(); y++) {
	        	for(int x = 0; x < map.getTailleX(); x++) {
	        		String[] values = map.getMonde()[x][y].save();
	        		for(String str : values) fw.write(str + retour);
	        		fw.write(retour);
	        	}
	        }
	        
	        
	        //écriture des guerres
	        for(Civilisation civ : civilisations) {
	        	String[] guerres = civ.saveWars();
	        	for(String str : guerres) fw.write(str + retour);
	        	fw.write(retour);
	        }
	        
	        
	        
	        
	        fw.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void load(String nom) {
		File file;
		try{
			vitesse = 500;
			play = true;
			file = new File(nom);
			Scanner scanner = new Scanner(file);
			//lecture du caseX, Y, nbCivs et date:
			int tailleX = scanner.nextInt();
			int tailleY = scanner.nextInt();
			int nbCivs = scanner.nextInt();
			map = new Map(tailleX, tailleY, civilisations, frame);
			date = scanner.nextInt();

			//init pour les autres classes
			civilisations = map.getCiv();
			Civilisation.setCiv(civilisations);
			Civilisation.setMap(map.getMonde(), map.getTailleX(), map.getTailleY());
			Case.setMap(map.getMonde(), map.getTailleX(), map.getTailleY());
	        Case.setCivs(civilisations);
			frame.setCiv(civilisations);
			
			//lecture des civilisations
	        civilisations = new ArrayList<Civilisation>();
	        for(int nb = 0 ; nb < nbCivs; nb++){
	        	System.out.println("Start loading civ" + nb);
	        	int id = scanner.nextInt();
	        	boolean alive = scanner.nextBoolean();
	        	String name = scanner.next();
	        	int couleurR = scanner.nextInt();
	        	int couleurG = scanner.nextInt();
	        	int couleurB = scanner.nextInt();
	        	int couleurA = scanner.nextInt();
	        	int culture = scanner.nextInt();
    			int ideologie = scanner.nextInt();
    			int regime = scanner.nextInt();
    			int polT = scanner.nextInt();
    			
    			int population = scanner.nextInt();
    			int popColo = scanner.nextInt();
    			int production = scanner.nextInt();
    			int economie = scanner.nextInt();
    			int influence = scanner.nextInt();
    			int armee = scanner.nextInt();
    			int scienceLvl = scanner.nextInt();
    			int science = scanner.nextInt();
    			int nourriture = scanner.nextInt();
    			int bonheur = scanner.nextInt();
    			int stabilite = scanner.nextInt();
    			
    			double efficaciteArmee = Double.parseDouble(scanner.next());
    			double tauxArmeeAttendu = Double.parseDouble(scanner.next());
    			double tauxEducation = Double.parseDouble(scanner.next());
    			double tauxCroissance = Double.parseDouble(scanner.next());
    			double tauxAccroissementScience = Double.parseDouble(scanner.next());
    			
    			int baseBonheur = scanner.nextInt();
    			int baseScience = scanner.nextInt();
    			double baseEfficaciteArmee = Double.parseDouble(scanner.next());
    			double noteMinimale = Double.parseDouble(scanner.next());
	        	
	        	civilisations.add(new Civilisation(id, alive, name, couleurR, couleurG, couleurB, couleurA,
	        			culture, ideologie, regime, polT,
	        			population, popColo, production, economie, influence, armee, scienceLvl, science, nourriture, bonheur, stabilite,
	        			efficaciteArmee, tauxArmeeAttendu, tauxEducation, tauxCroissance, tauxAccroissementScience,
	        			baseBonheur, baseScience, baseEfficaciteArmee, noteMinimale));
	        }

			
			//lecture du monde:
	        for(int nbCase = 0; nbCase < tailleX*tailleY; nbCase++){
				int x = scanner.nextInt();
				int y = scanner.nextInt();
				int idOwner = scanner.nextInt();
				int idRelief = scanner.nextInt();
				int idBiome = scanner.nextInt();
				int idClimat = scanner.nextInt();
				int nbSoldats = scanner.nextInt();
				int nbAnimaux = scanner.nextInt();
				ArrayList<Animaux> animaux = new ArrayList<>();
				if(nbAnimaux > 0) for(int nb = 0; nb < nbAnimaux; nb++) animaux.add(Animaux.values()[scanner.nextInt()]);
				int nbRessources = scanner.nextInt();
				ArrayList<StrategicRessource> ressources = new ArrayList<>();
				for(int nb = 0; nb < nbRessources; nb++) ressources.add(StrategicRessource.values()[scanner.nextInt()]);
				int nbClaims = scanner.nextInt();
				ArrayList<Integer> claims = new ArrayList<>();
				for(int nb = 0; nb < nbClaims; nb++) claims.add(scanner.nextInt());
				Case laCase = new Case(x, y, idOwner, idRelief, idBiome, idClimat, animaux, ressources, claims, nbSoldats);
				map.setCaseInMap(x, y, laCase);
	        }
	        

	        Case.setCivs(civilisations);
	        map.installCivs(civilisations);
	        frame.setTrueMap(map.getMonde(), tailleX, tailleY);
	        frame.setCiv(civilisations);
	        
	        //TODO lecture des guerres
	        /*for(int numCiv = 0; numCiv < nbCivs; numCiv++) {
	        	for(int nbGuerre = 0; nbGuerre < scanner.nextInt(); nbGuerre++) {
	        		int idTypeGuerre = scanner.nextInt();
	        		int idDefenseur = scanner.nextInt();
	        		
	        	}
	        }*/
	        System.out.println("Loading complete");
			scanner.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERREUR FICHIER INEXISTANT :(", "ERREUR CHARGEMENT", JOptionPane.ERROR_MESSAGE);
		}catch(NoSuchElementException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERREUR FICHIER NON VALIDE :(", "ERREUR CHARGEMENT", JOptionPane.ERROR_MESSAGE);
		}
	}

	//fct raccourcis
	public static void wait(int temps) {
		try {
			Thread.sleep(temps);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static int[] getFreeRandomLocation() {
		int x, y;
		do {
			x = (int) (Math.random() * map.getTailleX());
			y = (int) (Math.random() * map.getTailleY());
		}while(map.getMonde()[x][y].getCrossable() == false || map.getMonde()[x][y].caseLibre() == false);
		int[] liste = new int[2];
		liste[0] = x; liste[1] = y;
		return liste;
	}
	public static void armageddon() {
		for(int posCivAtt = 0; posCivAtt < civilisations.size()-1; posCivAtt++) {
			Civilisation att = civilisations.get(posCivAtt);
			for(int posCivDef = posCivAtt+1; posCivDef < civilisations.size(); posCivDef++) att.declareWarTo(civilisations.get(posCivDef), "Annihilation");
		}
	}
	
}
