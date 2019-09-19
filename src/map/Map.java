package map;

import java.util.ArrayList;
import Civilisations.Civilisation;
import cases.*;
import gui.Frame;
//librairies externes

public class Map {
	
	private static ArrayList<Civilisation> civilisations = new ArrayList<Civilisation>();
	
	private int tailleX;
	private Frame frame;
	private int tailleY;
	public int[][] tempMonde;
	public int[][] tempBiome;
	private Case [][] monde;
	int nbOceans;
	int tailleOceans;
	int niveau_mer;
	double proba_volcan;
	int vitesse;
	
	
	//builder custom
		public Map(int vitesseReconnaded100, ArrayList<Civilisation> civs, Frame frame, int x, int y, int nbOceans, int tailleOcean, int niveau_mer, double proba_volcan) {
			this.vitesse = vitesseReconnaded100;
			this.civilisations = civs;
			this.frame = frame;
			tailleX = x;
			tailleY = y;
			tempMonde = new int[tailleX][tailleY];
			tempBiome = new int[tailleX][tailleY];
			monde = new Case[tailleX][tailleY];
			this.nbOceans = nbOceans;
			this.tailleOceans = tailleOcean;
			this.niveau_mer = niveau_mer;
			niveau_mer = (niveau_mer > 80) ? 80 : niveau_mer;
			niveau_mer = (niveau_mer < 30) ? 30 : niveau_mer;
			this.proba_volcan = proba_volcan;
			//init monde
			for(int i = 0; i < tailleX; i++) {
				for(int j = 0; j < tailleY; j++) {
					tempMonde[i][j] = niveau_mer;
					tempBiome[i][j] = 0;
				}
			}
			//initialisation des mondes
			frame.setReliefMonde(tempMonde, tailleX, tailleY);
			frame.setBiomeMonde(tempBiome, tailleX, tailleY);
			//génération
			generateOceans();
			generateHills();
			colorizeMap();
			mapTheMap();
			
		}
		
	//base builder
	public Map(int vitesseReconnaded100, ArrayList<Civilisation> civs, Frame frame) {
		this.vitesse = vitesseReconnaded100;
		this.civilisations = civs;
		this.frame = frame;
		tailleX = 50;
		tailleY = 30;
		tempMonde = new int[tailleX][tailleY];
		tempBiome = new int[tailleX][tailleY];
		monde = new Case[tailleX][tailleY];
		this.nbOceans = 7;
		this.tailleOceans = 8;
		this.niveau_mer = 64;
		niveau_mer = (niveau_mer > 80) ? 80 : niveau_mer;
		niveau_mer = (niveau_mer < 30) ? 30 : niveau_mer;
		this.proba_volcan = 0.075;
		//init monde
		for(int i = 0; i < tailleX; i++) {
			for(int j = 0; j < tailleY; j++) {
				tempMonde[i][j] = niveau_mer;
				tempBiome[i][j] = 0;
			}
		}
		//initialisation des mondes
		frame.setReliefMonde(tempMonde, tailleX, tailleY);
		frame.setBiomeMonde(tempBiome, tailleX, tailleY);
		//génération
		generateOceans();
		generateHills();
		colorizeMap();
		mapTheMap();
		
	}

	//empty builder
		public Map(Frame frame) {
			this.vitesse = 0;
			this.civilisations = new ArrayList<Civilisation>();
			this.frame = frame;
			tailleX = 50;
			tailleY = 30;
			tempMonde = new int[tailleX][tailleY];
			tempBiome = new int[tailleX][tailleY];
			monde = new Case[tailleX][tailleY];
			this.nbOceans = 7;
			this.tailleOceans = 8;
			this.niveau_mer = 64;
			niveau_mer = (niveau_mer > 80) ? 80 : niveau_mer;
			niveau_mer = (niveau_mer < 30) ? 30 : niveau_mer;
			this.proba_volcan = 0.075;
			//init monde
			for(int i = 0; i < tailleX; i++) {
				for(int j = 0; j < tailleY; j++) {
					tempMonde[i][j] = niveau_mer;
					tempBiome[i][j] = 0;
					monde[i][j] = new Case(new OceanProfond(), new None(), i, j);
				}
			}
			//initialisation des mondes
			frame.setReliefMonde(tempMonde, tailleX, tailleY);
			frame.setBiomeMonde(tempBiome, tailleX, tailleY);
		}
		
	//loading builder
	public Map(int tailleX, int tailleY, ArrayList<Civilisation> civs, Frame frame) {
		this.tailleX = tailleX;
		this.tailleY = tailleY;
		this.civilisations = civs;
		this.frame = frame;
		monde = new Case[tailleX][tailleX];
		for(int x = 0; x < tailleX; x++) {
			for(int y = 0; y < tailleY; y++) {
				monde[x][y] = null;
			}
		}
	}

	
	
	//TODO : fonctions principales de générations
		public void generateOceans() {
			//détermination du point de base
			for(int nb = 0; nb < nbOceans; nb++) {
				int Xbase = (int)(Math.random()*tailleX);
				int Ybase = (int)(Math.random()*tailleY);
				tempMonde[Xbase][Ybase] = 0;
			}
			for(int i = 0; i < tailleOceans; i++) {
				extendOceans();
				paintRelief();
				wait(vitesse);
				
			}
			generateCoasts();
		}
	
	//TODO : apelle des fct de création du relief
	//1.ocean_profond:<niveau/3 ; 2.ocean:<niveau-5 ; 3.Litoral:<niveau ; 4.lac:niveau-10 ; 5.plage:niveau+1 ; 6.falaise:niveau+2 ;
	//10.plaines:niveau ; 11.coline:niveau+15 -> niveau+20 ; 12.montagne:>niveau+45 ; 13.volcan:125
	//21.forets ; 22.jungle ; 23.marais ; 24.desert ; 25.prairies
	
	
	public void generateHills() {
		//point de départ des colines
			for(int x = 0; x < tailleX; x++) {
				for(int y = 0; y < tailleY; y++) {
					if(tempMonde[x][y] == niveau_mer && Math.random() > 0.98) {
						tempMonde[x][y] = niveau_mer + 15;
					}
				}
			}
			paintRelief();
			wait(vitesse);
		//extension collines
			for(int num = 0; num < 7; num++) {
				for(int niveau = niveau_mer+20; niveau >= niveau_mer+15; niveau--) {
					extendColines(niveau);
					paintRelief();
					wait(vitesse / 5);
				}
			}
		//point de départ des montagnes
			for(int x = 0; x < tailleX; x++) {
				for(int y = 0; y < tailleY; y++) {
					if(tempMonde[x][y] == niveau_mer+20 && Math.random() > 0.35) {
						tempMonde[x][y] += 15;
					}
				}
			}
		//extension montagnes
			for(int num = 0; num < 3; num++) {
				for(int niveau = niveau_mer+45; niveau >= niveau_mer+25; niveau--) {
					extendColines(niveau);
					paintRelief();
					wait(vitesse / 5);
				}
			}
		//installation de volcans et montagnes solitaires
			for(int x = 0; x < tailleX; x++) {
				for(int y = 0; y < tailleY; y++) {
					if(tempMonde[x][y] > niveau_mer+35-5 && Math.random() < proba_volcan) {
						tempMonde[x][y] = 125;
					}
					if(tempMonde[x][y] == niveau_mer && Math.random() < proba_volcan /4) {
						tempMonde[x][y] += 35;
					}
				}
			}
			paintRelief();
			wait(vitesse);
		//génération des lacs et point d'eau
			for(int num = 0; num < 3; num++) {
				generateLack();
				paintRelief();
				wait(vitesse);
			}
	}
	
	
	
	
	
	//TODO : apelle des fct de coloriage
	//1.ocean_profond:<niveau/3 ; 2.ocean:<niveau-5 ; 3.Litoral:<niveau ; 4.lac:niveau-10 ; 5.plage:niveau+1 ; 6.falaise:niveau+2 ;
	//10.plaines:niveau ; 11.coline:niveau+15 -> niveau+20 ; 12.montagne:>niveau+45 ; 13.volcan:127
	//21.forets ; 22.jungle ; 23.marais ; 24.desert ; 25.prairies
	
	//creation de la carte des biomes
		private void colorizeMap() {
			//difference large et little biome : hauteur sur laquelle un biome s'éttend
			colorizeLargeBiome(1, (int)(niveau_mer/3)); //num du biome puis hauteur max du biome
			colorizeLittleBiome(4, niveau_mer-10); //num du biome puis hauteur du biome
			colorizeLargeBiome(2, niveau_mer-1);
			colorizeLargeBiome(3, niveau_mer);
			colorizeLittleBiome(5, niveau_mer+1);
			colorizeLittleBiome(6, niveau_mer+2);
			
			colorizeLittleBiome(10, niveau_mer); //pas de biome plaine
			colorizeLargeBiome(11, niveau_mer+20); //pas de biome coline
			colorizeMountains(12, niveau_mer+20);
			colorizeLittleBiome(13, 127);
			
			createNewBiomes(21, 5, 4, 0.4); // forets //numBiome, nbBiome, tailleBiome, propagationBiome
			createNewBiomes(22, 3, 5, 0.45); // jungles
			createNewBiomes(23, 2, 4, 0.3); // marais
			createNewBiomes(24, 2, 3, 0.3); // desert
			creatLands();
			
			
		}
		
		
	//TODO generate Map
		private void mapTheMap() {
			for(int x = 0; x < tailleX; x++) {
				for(int y = 0; y < tailleY; y++) {
					Biome biome;   //si pas de biome, mettre None
					Relief relief; //si pas de relief, mettre Neant
					
					//1.ocean_profond ; 2.ocean ; 3.Litoral ; 4.lac ; 5.plage; 6.falaise ;
					//10.plaines ; 11.coline ; 12.montagne ; 13.volcan
					
					//determination du relief
					switch (tempBiome[x][y]) {
					case 1:
						relief = new OceanProfond();
						break;
					case 2:
						relief = new Ocean();
						break;
					case 3:
						relief = new Litoral();
						break;
					case 4:
						relief = new Lac();
						break;
					case 5:
						relief = new Plage();
						break;
					case 6:
						relief = new Falaise();
						break;
					case 12:
						relief = new Montagne();
						break;
					case 13:
						relief = new Montagne();
						break;
					default:
						relief = new Neant();
						break;
					}
					
						//colines et plaines sont remplacés dans tempBiomes donc on refait la recherche
					if(tempMonde[x][y] == niveau_mer) relief = new Plaine();
					if(tempMonde[x][y] >= niveau_mer+15 && tempMonde[x][y] <= niveau_mer+20) relief = new Coline();
					
					
					//determination du biome
					//1.ocean_profond ; 2.ocean ; 3.Litoral ; 4.lac ; 5.plage; 6.falaise ;
					//10.plaines ; 11.coline ; 12.montagne ; 13.volcan
					//21.forets ; 22.jungle ; 23.marais ; 24.desert ; 25.prairies
					switch (tempBiome[x][y]) {
					case 21:
						biome = new Foret();
						break;
					case 22:
						biome = new Jungle();
						break;
					case 23:
						biome = new Marais();
						break;
					case 24:
						biome = new Desert();
						break;
					case 25:
						biome = new Prairie();
						break;
					case 13:
						biome = new Volcan();
						break;
					case 6:
						biome = new Prairie();
						break;

					default:
						biome = new None();
						break;
					}
					monde[x][y] = new Case(relief, biome, x, y); // relief, biome, x, y
				}
			}
		}
	
	
	
	
	
	
	
	
	
	
	
	
	//TODO : Sous-fonctions de générations RELIEF
	//génération des océans, en mode tache qui s'étalle
	public void extendOceans() {
		for(int i = 0; i < tailleX; i++) {
			for(int j = 0; j < tailleY; j++) {
				if(tempMonde[i][j] < niveau_mer && tempMonde[i][j] >= 0) {
					//parcours des cases adjacentes
					for(int a = -1; a < 2; a++) {
						for(int b = -1; b < 2; b++) {
							if(i+a >= 0 && i+a < tailleX && j+b >= 0 && j+b < tailleY) {
								if(tempMonde[i+a][j+b] == niveau_mer && Math.random() < 0.25) {
									tempMonde[i+a][j+b] = (int) - (tempMonde[i+a][j+b] - (Math.floor((tempMonde[i+a][j+b]-tempMonde[i][j])/(1+Math.random()*0.25))));
									//nouveau niveau de la case :
									//exemple : 60 = (60 - ((int)((60-00)/1.25))) = 60-48 = 12
									//exemple : 60 = (60 - ((int)((60-12)/1.10))) = 60-44 = 16
									//exemple : 60 = (60 - ((int)((60-16)/1.00))) = 60-44 = 16
									//exemple : 60 = (60 - ((int)((60-16)/1.25))) = 60-13 = 47
								}
							}
						}
					}
				}
			}
		}
		for(int i = 0; i < tailleX; i++) {
			for(int j = 0; j < tailleY; j++) {
				if(tempMonde[i][j] < 0) {
					tempMonde[i][j] = -tempMonde[i][j];
				}
			}
		}
	}
	public void generateCoasts() {
		for(int x = 0; x < tailleX; x++) {
			for(int y = 0; y < tailleY; y++) {
				if(tempMonde[x][y] == niveau_mer) {
					//parcours des cases adjacentes
					int countCaseMerAside = 0;
					for(int a = -1; a < 2; a++) {
						for(int b = -1; b < 2; b++) {
							if(x+a >= 0 && x+a < tailleX && y+b >= 0 && y+b < tailleY) {
								if(tempMonde[x+a][y+b] < niveau_mer-1) {
									countCaseMerAside++;
								}
							}
						}
					}
					if(countCaseMerAside > 1) {
						if((Math.random() > 0.75)) {
							tempMonde[x][y] = niveau_mer + 2; //falaise (niveau_mer+2) si 60% et +2 caseOcean
						}else {
							tempMonde[x][y] = niveau_mer + 1; //plage (niveau_mer+1)
						}
					}
				}
			}
		}
	}
	
	//extension des colines, en mode tache qui s'étalle
		public void extendColines(int niveau) {
			for(int i = 0; i < tailleX; i++) {
				for(int j = 0; j < tailleY; j++) {
					if(tempMonde[i][j] == niveau) {
						//parcours des cases adjacentes
						for(int a = -1; a < 2; a++) {
							for(int b = -1; b < 2; b++) {
								if(i+a >= 0 && i+a < tailleX && j+b >= 0 && j+b < tailleY) {
									if((tempMonde[i+a][j+b] == niveau -1 || tempMonde[i+a][j+b] == niveau_mer/* || tempMonde[i+a][j+b] == niveau_mer + 35*/) && Math.random() < 0.40) {
										tempMonde[i+a][j+b] = -niveau;
									}
								}
							}
						}
					}
				}
			}
			//mise à niveau des valeurs
			for(int i = 0; i < tailleX; i++) {
				for(int j = 0; j < tailleY; j++) {
					if(tempMonde[i][j] < 0) {
						tempMonde[i][j] *= -1;
					}
				}
			}
			//cases pour le nouveau niveau
			for(int i = 0; i < tailleX; i++) {
				for(int j = 0; j < tailleY; j++) {
					if(tempMonde[i][j] == niveau && Math.random() < 0.25) {
						tempMonde[i][j]++;
					}
				}
			}
		}
		
		
	//générations des lacs et points d'eau
		public void generateLack() {
			for(int i = 0; i < tailleX; i++) {
				for(int j = 0; j < tailleY; j++) {
					/*if(tempMonde[i][j] == niveau_mer - 10) { //pour eviter de mettre des lacs au milie des oceans, on vire les cas potentiels...
						tempMonde[i][j]--;
					}*/
					if(tempMonde[i][j] == niveau_mer) {
						//parcours des cases adjacentes
						int count = 0;
						for(int a = -1; a < 2; a++) {
							for(int b = -1; b < 2; b++) {
								if(i+a >= 0 && i+a < tailleX && j+b >= 0 && j+b < tailleY) {
									if(tempMonde[i+a][j+b] >= niveau_mer + 15 || tempMonde[i+a][j+b] == niveau_mer - 10) {
										count++;
									}
								}
							}
						}
						if(count > 4) {
							tempMonde[i][j] = niveau_mer - 10;
						}
					}
				}
			}
			//mise à niveau des valeurs
			for(int i = 0; i < tailleX; i++) {
				for(int j = 0; j < tailleY; j++) {
					if(tempMonde[i][j] < 0) {
						tempMonde[i][j] *= -1;
					}
				}
			}
		}
	
	
	//TODO : Sous-fonctions de générations BIOME
	//coloriage
		private void colorizeLargeBiome(int num, int max) {
			for(int x = 0; x < tailleX; x++) {
				for(int y = 0; y < tailleY; y++) {
					//verification qu'on n'ait pas deja mis un biome ici
					if(tempBiome[x][y] == 0) {
						//condition pour le biome
						if(tempMonde[x][y] < max) {
							tempBiome[x][y] = num;
						}
					}
				}
			}
			paintBiome();
			wait(vitesse * 2);
		}
		//lacs
		private void colorizeLittleBiome(int num, int height) {
			for(int x = 0; x < tailleX; x++) {
				for(int y = 0; y < tailleY; y++) {
					//verification qu'on n'ait pas deja mis un biome ici
					if(tempBiome[x][y] == 0) {
						//condition pour le biome
						if(tempMonde[x][y] == height) {
							tempBiome[x][y] = num;
						}
					}
				}
			}
		}
		//montagnes
		private void colorizeMountains(int num, int minHeight) {
			for(int x = 0; x < tailleX; x++) {
				for(int y = 0; y < tailleY; y++) {
					//verification qu'on n'ait pas deja mis un biome ici
					if(tempBiome[x][y] == 0) {
						//condition pour le biome
						if(tempMonde[x][y] >= minHeight && tempMonde[x][y] < 127) {
							tempBiome[x][y] = num;
						}
					}
				}
			}
		}
		
		//installation des autres biomes
		private void createNewBiomes(int numBiome, int nbBiome, int tailleBiome, double propagationBiome) {
			//points de départ du biome
			for(int num = 0; num < nbBiome; num++) {
				int x = (int)(Math.random()*tailleX);
				int y = (int)(Math.random()*tailleY);
				if(tempBiome[x][y] == 10 || tempBiome[x][y] == 11) {
					tempBiome[x][y] = numBiome;
				}else {
					num--;
				}
					
			}
			for(int taille = 0; taille < tailleBiome; taille++) {
				for(int x = 0; x < tailleX; x++) {
					for(int y = 0; y < tailleY; y++) {
						if(tempBiome[x][y] == numBiome) {
							//parcours des cases adjacentes
							for(int a = -1; a < 2; a++) {
								for(int b = -1; b < 2; b++) {
									if(x+a >= 0 && x+a < tailleX && y+b >= 0 && y+b < tailleY) {
										if((tempBiome[x+a][y+b] == 10 || tempBiome[x+a][y+b] == 11) && Math.random() < propagationBiome) {
											tempBiome[x+a][y+b] = - numBiome;
										}
									}
								}
							}
						}
					}
				}
				//mise à niveau des valeurs
				for(int x = 0; x < tailleX; x++) {
					for(int y = 0; y < tailleY; y++) {
						if(tempBiome[x][y] < 0) {
							tempBiome[x][y] *= -1;
						}
					}
				}
				
				paintBiome();
				wait(vitesse);
			}
		}
		
		//remplissage avec des prairies
		private void creatLands() {
			for(int x = 0; x < tailleX; x++) {
				for(int y = 0; y < tailleY; y++) {
					if(tempBiome[x][y] == 10 || tempBiome[x][y] == 11) {
						tempBiome[x][y] = 25;
					}
				}
			}
			paintBiome();
		}
		
		
		
	

	
	
	
	
	
	
	
	//fct racourcis
	private void wait(int temps) { // delais
		try {
			Thread.sleep(temps);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void paintRelief() { //affichage carte relief
		frame.setReliefMonde(tempMonde, tailleX, tailleY);
		frame.repaint();
	}
	private void paintBiome() { //affichage carte relief
		frame.setBiomeMonde(tempBiome, tailleX, tailleY);
		frame.repaint();
	}
	
	
	
	
	//getters and setters
	public int getTailleX() {return tailleX;}
	public int getTailleY() {return tailleY;}
	public int[][] getTempMonde() {return tempMonde;}
	public int[][] getTempBiome() {return tempBiome;}
	public int getNbOceans() {return nbOceans;}
	public int getTailleOceans() {return tailleOceans;}
	public Case[][] getMonde(){return monde;}
	public void changeCarte(Case[][] carte) {monde = carte;}
	
	public void setCiv(ArrayList<Civilisation> civ) {civilisations = civ;}
	public ArrayList<Civilisation> getCiv() {return civilisations;}
	
	public void repaint() {frame.repaint();}
	
	//load
		public void setCaseInMap(int x, int y, Case laCase) {monde[x][y] = laCase; System.out.println(monde[x][y]);}
		public void installCivs(ArrayList<Civilisation> civs) {
			civilisations = civs;
			for(int x = 0; x < tailleX; x++) {
				for(int y = 0; y < tailleY; y++) {
					monde[x][y].installCivs();
				}
			}
			for(int x = 0; x < tailleX; x++) {
				for(int y = 0; y < tailleY; y++) {
					monde[x][y].getVoisinesLibres();
				}
			}
			System.out.println("nombre de Civilisations chargées : " + civilisations.size());
			for(Civilisation c : civilisations) c.checkCiv();
		}
}
