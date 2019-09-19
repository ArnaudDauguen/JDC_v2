package map;

import java.util.ArrayList;

import Civilisations.Civilisation;
import cases.*;
public class EarthMap {
	
	private int tailleX;
	private int tailleY;
	private Case[][] carte;
	private Case[][] map;
	private ArrayList<Civilisation> civs;
	
	public EarthMap(int tailX, int tailY){
		int tailleX = tailX;
		int tailleY = tailY;
		Case[][] carte = new Case[tailleX][tailleY];
		civs = new ArrayList<Civilisation>();
		
		//TODO culture id
		int cultId = 0, bonheur = 60, stab = 70;
		
		//Civilisations
		//new Civilisation(id, alive, nom, R, G, B, A, cultId, ideologie, regime, polT, pop, popColo, prod, eco, sciLvl, nour, bonheur, stabilite)
		civs.add(new Civilisation(0, true, "les Etats-Unis d'Amerique", 50, 100, 255, 200, cultId, 2, 2, 0, 213, 0, 500, 300, 15, 2130, 35, 25));
		civs.add(new Civilisation(1, true, "l'Union Sovietique", 240, 0, 10, 200, cultId, 1, 1, 2, 192, 0, 450, 350, 13, 1920, 50, 35));
		civs.add(new Civilisation(2, true, "le Royaume-Uni", 255, 128, 255, 200, cultId, 2, 2, 3, 238, 500, 200, 200, 12, 7380, bonheur, 70));
		civs.add(new Civilisation(3, true, "la France", 0, 110, 230, 200, cultId, 2, 2, 3, 164, 200, 100, 200, 12, 3640, 25, 20));
		civs.add(new Civilisation(4, true, "l'Allemagne Nazi", 120, 120, 120, 200, cultId, 0, 5, 3, 42, 0, 250, 100, 22, 420, 75, 90));
		civs.add(new Civilisation(5, true, "l'Italie", 0, 128, 64, 200, cultId, 0, 8, 1, 80, 10, 200, 100, 15, 800, 70, 80));
		civs.add(new Civilisation(6, true, "l'Espagne", 210, 110, 0, 200, cultId, 0, 8, 2, 28, 2, 150, 250, 12, 300, 30, 40));
		civs.add(new Civilisation(7, true, "le Japon", 255, 220, 255, 200, cultId, 0, 6, 0, 85, 170, 300, 500, 15, 2250, 100, 100));
		civs.add(new Civilisation(8, true, "l'Europe de l'Est", 255, 50, 128, 200, cultId, 3, 1, 1, 54, 0, 200, 300, 8, 640, 40, 30));
		civs.add(new Civilisation(9, true, "le Benelux", 255, 255, 128, 200, cultId, 2, 2, 2, 45, 173, 150, 300, 12, 0, bonheur, stab));
		civs.add(new Civilisation(10, true, "la Scandinavie", 255, 255, 255, 200, cultId, 2, 2, 0, 30, 0, 100, 50, 10, 300, bonheur, stab));
		civs.add(new Civilisation(11, true, "la Chine", 255, 255, 100, 200, cultId, 3, 8, 1, 325, 0, 100, 30, 5, 3250, 20, 25));
		civs.add(new Civilisation(12, true, "la Chine Communiste", 250, 100, 150, 200, cultId, 1, 8, 1, 125, 0, 75, 20, 6, 1250, 20, 40));
		civs.add(new Civilisation(13, true, "le Mexique", 0, 128, 0, 200, cultId, 3, 1, 0, 75, 0, 50, 75, 8, 750, bonheur, 60));
		civs.add(new Civilisation(14, true, "le Venezuela", 170, 210, 140, 200, cultId, 0, 8, 2, 47, 0, 50, 150, 8, 470, 70, stab));
		civs.add(new Civilisation(15, true, "le Perou", 200, 200, 200, 200, cultId, 0, 8, 2, 20, 0, 50, 100, 7, 200, bonheur, stab));
		civs.add(new Civilisation(16, true, "le Brezil", 20, 190, 30, 200, cultId, 2, 1, 2, 120, 0, 50, 100, 7, 1200, 70, stab));
		civs.add(new Civilisation(17, true, "l'Argentine", 128, 128, 190, 200, cultId, 3, 1, 2, 20, 0, 50, 50, 7, 200, 70, 60));
		civs.add(new Civilisation(18, true, "le Turquie", 190, 190, 80, 200, cultId, 3, 1, 2, 130, 0, 35, 50, 7, 1300, bonheur, stab));
		civs.add(new Civilisation(19, true, "le Danemark", 150, 120, 90, 200, cultId, 2, 2, 3, 10, 0, 35, 50, 10, 100, 90, 90));
		civs.add(new Civilisation(20, true, "Cuba", 128, 0, 128, 200, cultId, 1, 1, 0, 8, 0, 25, 10, 5, 80, 40, 60));
		
		Civilisation.setCiv(civs);
		Case.setCivs(civs);
		
		//initialisation full ocean
		for(int x = 0; x < tailleX; x++) {
			for(int y = 0; y < tailleY; y++) {
				carte[x][y] = new Case(new OceanProfond(), new None(), x, y);
			}
		}
		
		//Groënland
		carte[22][2].alterTerrain(new Plage(), new Tundra(), 19);

		carte[21][2].alterTerrain(new Plage(), new Tundra(), 19);
		carte[21][3].alterTerrain(new Plaine(), new Tundra(), 19);
		carte[21][4].alterTerrain(new Plage(), new Tundra(), 19);

		carte[20][2].alterTerrain(new Plage(), new Tundra(), 19);
		carte[20][3].alterTerrain(new Plaine(), new Tundra(), 19);
		carte[20][4].alterTerrain(new Plaine(), new Tundra(), 19);
		carte[20][5].alterTerrain(new Plage(), new Tundra(), 19);

		carte[19][2].alterTerrain(new Plage(), new Tundra(), 19);
		carte[19][3].alterTerrain(new Plaine(), new Tundra(), 19);
		carte[19][4].alterTerrain(new Plaine(), new Tundra(), 19);
		carte[19][5].alterTerrain(new Plaine(), new Tundra(), 19);
		carte[19][6].alterTerrain(new Plaine(), new Tundra(), 19);
		carte[19][7].alterTerrain(new Plage(), new Tundra(), 19);

		carte[18][3].alterTerrain(new Plage(), new Tundra(), 19);
		carte[18][4].alterTerrain(new Plaine(), new Tundra(), 19);
		carte[18][5].alterTerrain(new Plaine(), new Tundra(), 19);
		carte[18][6].alterTerrain(new Plage(), new Tundra(), 19);

		carte[17][3].alterTerrain(new Plage(), new Tundra(), 19);
		
		//Canada
		carte[17][9].alterTerrain(new Plaine(), new Prairie(), 2);

		carte[16][5].alterTerrain(new Plaine(), new Tundra(), 2);
		carte[16][6].alterTerrain(new Litoral(), new None());
		carte[16][7].alterTerrain(new Litoral(), new None());
		carte[16][8].alterTerrain(new Plaine(), new Prairie(), 2);
		carte[16][9].alterTerrain(new Plaine(), new Prairie(), 2);

		carte[15][4].alterTerrain(new Plaine(), new Tundra(), 2);
		carte[15][5].alterTerrain(new Plaine(), new Tundra(), 2);
		carte[15][6].alterTerrain(new Litoral(), new None());
		carte[15][7].alterTerrain(new Plaine(), new Prairie(), 2);
		carte[15][8].alterTerrain(new Plaine(), new Prairie(), 2);
		carte[15][9].alterTerrain(new Plaine(), new Prairie(), 2);

		carte[14][4].alterTerrain(new Plaine(), new Tundra(), 2);
		carte[14][5].alterTerrain(new Litoral(), new None());
		carte[14][6].alterTerrain(new Litoral(), new None());
		carte[14][7].alterTerrain(new Litoral(), new None());
		carte[14][8].alterTerrain(new Litoral(), new None());
		carte[14][9].alterTerrain(new Plaine(), new Prairie(), 2);

		carte[13][4].alterTerrain(new Plaine(), new Tundra(), 2);
		carte[13][5].alterTerrain(new Plaine(), new Tundra(), 2);
		carte[13][6].alterTerrain(new Litoral(), new None());
		carte[13][7].alterTerrain(new Litoral(), new None());
		carte[13][8].alterTerrain(new Plaine(), new Prairie(), 2);
		carte[13][9].alterTerrain(new Plaine(), new Prairie(), 2);

		carte[12][5].alterTerrain(new Plaine(), new Tundra(), 2);
		carte[12][6].alterTerrain(new Plaine(), new Tundra(), 2);
		carte[12][7].alterTerrain(new Litoral(), new None());
		carte[12][8].alterTerrain(new Plaine(), new Prairie(), 2);
		carte[12][9].alterTerrain(new Plaine(), new Prairie(), 2);

		carte[11][5].alterTerrain(new Plaine(), new Tundra(), 2);
		carte[11][6].alterTerrain(new Plaine(), new Foret(), 2);
		carte[11][7].alterTerrain(new Plaine(), new Foret(), 2);
		carte[11][8].alterTerrain(new Plaine(), new Prairie(), 2);
		carte[11][9].alterTerrain(new Plaine(), new Prairie(), 2);

		carte[10][4].alterTerrain(new Plaine(), new Tundra(), 2);
		carte[10][5].alterTerrain(new Plaine(), new Tundra(), 2);
		carte[10][6].alterTerrain(new Plaine(), new Foret(), 2);
		carte[10][7].alterTerrain(new Plaine(), new Foret(), 2);
		carte[10][8].alterTerrain(new Plaine(), new Foret(), 2);
		carte[10][9].alterTerrain(new Coline(), new Prairie(), 2);

		carte[9][4].alterTerrain(new Plaine(), new Tundra(), 2);
		carte[9][5].alterTerrain(new Plaine(), new Tundra(), 2);
		carte[9][6].alterTerrain(new Plaine(), new Foret(), 2);
		carte[9][7].alterTerrain(new Coline(), new Foret(), 2);
		carte[9][8].alterTerrain(new Coline(), new Prairie(), 2);
		carte[9][9].alterTerrain(new Coline(), new Prairie(), 2);

		carte[8][4].alterTerrain(new Plaine(), new Tundra(), 2);
		carte[8][5].alterTerrain(new Plaine(), new Foret(), 2);
		carte[8][6].alterTerrain(new Plaine(), new Foret(), 2);
		carte[8][7].alterTerrain(new Coline(), new Foret(), 2);
		carte[8][8].alterTerrain(new Coline(), new Foret(), 2);
		carte[8][9].alterTerrain(new Montagne(), new None(), 2);

		carte[7][5].alterTerrain(new Plaine(), new Tundra(), 2);
		carte[7][6].alterTerrain(new Plaine(), new Foret(), 2);
		carte[7][7].alterTerrain(new Coline(), new Foret(), 2);
		carte[7][8].alterTerrain(new Montagne(), new None(), 2);
		carte[7][9].alterTerrain(new Litoral(), new None(), 2);

		carte[6][5].alterTerrain(new Coline(), new Tundra(), 2);
		carte[6][6].alterTerrain(new Coline(), new Tundra(), 2);
		carte[6][7].alterTerrain(new Coline(), new Foret(), 2);
		carte[6][8].alterTerrain(new Litoral(), new None());

		carte[5][5].alterTerrain(new Coline(), new Tundra(), 2);
		carte[5][6].alterTerrain(new Coline(), new Tundra(), 2);
		carte[5][7].alterTerrain(new Litoral(), new None());

		carte[4][5].alterTerrain(new Plaine(), new Tundra(), 0);
		carte[4][6].alterTerrain(new Plaine(), new Tundra(), 0);
		carte[4][7].alterTerrain(new Litoral(), new None());

		carte[3][4].alterTerrain(new Plaine(), new Tundra(), 0);
		carte[3][5].alterTerrain(new Plaine(), new Tundra(), 0);
		carte[3][6].alterTerrain(new Plaine(), new Tundra(), 0);
		carte[3][7].alterTerrain(new Plaine(), new Prairie(), 0);

		carte[2][5].alterTerrain(new Plaine(), new Tundra(), 0);
		carte[2][6].alterTerrain(new Plaine(), new Tundra(), 0);
		carte[2][7].alterTerrain(new Plaine(), new Tundra(), 0);
		
		//USA Amerique Centrale
		carte[17][10].alterTerrain(new Plaine(), new Prairie(), 0);

		carte[16][10].alterTerrain(new Plaine(), new Prairie(), 0);
		carte[16][15].alterTerrain(new Litoral(), new None());
		carte[16][16].alterTerrain(new Litoral(), new None());

		carte[15][10].alterTerrain(new Plaine(), new Prairie(), 0);
		carte[15][11].alterTerrain(new Plaine(), new Prairie(), 0);
		carte[15][12].alterTerrain(new Litoral(), new None());
		carte[15][13].alterTerrain(new Litoral(), new None());
		carte[15][14].alterTerrain(new Litoral(), new None());
		carte[15][15].alterTerrain(new Coline(), new Prairie(), 20);
		carte[15][16].alterTerrain(new Litoral(), new None());

		carte[14][10].alterTerrain(new Plaine(), new Prairie(), 0);
		carte[14][11].alterTerrain(new Plaine(), new Prairie(), 0);
		carte[14][12].alterTerrain(new Plaine(), new Prairie(), 0);
		carte[14][13].alterTerrain(new Plaine(), new Prairie(), 0);
		carte[14][14].alterTerrain(new Plage(), new Marais(), 0);
		carte[14][15].alterTerrain(new Litoral(), new None());
		carte[14][16].alterTerrain(new Litoral(), new None());

		carte[13][10].alterTerrain(new Plaine(), new Prairie(), 0);
		carte[13][11].alterTerrain(new Plaine(), new Prairie(), 0);
		carte[13][12].alterTerrain(new Plaine(), new Prairie(), 0);
		carte[13][13].alterTerrain(new Plage(), new None(), 0);
		carte[13][14].alterTerrain(new Litoral(), new None());
		carte[13][15].alterTerrain(new Litoral(), new None());
		carte[13][16].alterTerrain(new Plaine(), new Foret(), 0);

		carte[12][10].alterTerrain(new Plaine(), new Prairie(), 0);
		carte[12][11].alterTerrain(new Plaine(), new Prairie(), 0);
		carte[12][12].alterTerrain(new Plaine(), new Prairie(), 0);
		carte[12][13].alterTerrain(new Plaine(), new Prairie(), 0);
		carte[12][14].alterTerrain(new Litoral(), new None());
		carte[12][15].alterTerrain(new Plaine(), new Foret(), 13);
		carte[12][16].alterTerrain(new Litoral(), new None());
		carte[12][17].alterTerrain(new Litoral(), new None());

		carte[11][10].alterTerrain(new Coline(), new Prairie(), 0);
		carte[11][11].alterTerrain(new Montagne(), new None(), 0);
		carte[11][12].alterTerrain(new Montagne(), new None(), 0);
		carte[11][13].alterTerrain(new Coline(), new Desert(), 0);
		carte[11][14].alterTerrain(new Plaine(), new Foret(), 13);
		carte[11][15].alterTerrain(new Plaine(), new Foret(), 13);
		carte[11][16].alterTerrain(new Litoral(), new None());

		carte[10][10].alterTerrain(new Montagne(), new None(), 0);
		carte[10][11].alterTerrain(new Montagne(), new None(), 0);
		carte[10][12].alterTerrain(new Montagne(), new None(), 0);
		carte[10][13].alterTerrain(new Montagne(), new None(), 13);
		carte[10][14].alterTerrain(new Plaine(), new Foret(), 13);
		carte[10][15].alterTerrain(new Litoral(), new None());

		carte[9][10].alterTerrain(new Montagne(), new None(), 0);
		carte[9][11].alterTerrain(new Montagne(), new None(), 0);
		carte[9][12].alterTerrain(new Montagne(), new None(), 0);
		carte[9][13].alterTerrain(new Plaine(), new Foret(), 13);

		carte[8][10].alterTerrain(new Montagne(), new None(), 0);
		carte[8][11].alterTerrain(new Montagne(), new None(), 0);
		carte[8][12].alterTerrain(new Montagne(), new None(), 0);
		
		//Amerique du Sud
		carte[19][19].alterTerrain(new Plage(), new None(), 16);
		carte[19][20].alterTerrain(new Plage(), new None(), 16);

		carte[18][19].alterTerrain(new Coline(), new Foret(), 3);
		carte[18][20].alterTerrain(new Coline(), new Prairie(), 16);
		carte[18][21].alterTerrain(new Coline(), new Prairie(), 16);
		carte[18][22].alterTerrain(new Plage(), new None(), 16);

		carte[17][18].alterTerrain(new Plaine(), new Jungle(), 2);
		carte[17][19].alterTerrain(new Plaine(), new Jungle(), 16);
		carte[17][20].alterTerrain(new Plaine(), new Jungle(), 16);
		carte[17][21].alterTerrain(new Coline(), new Prairie(), 16);
		carte[17][22].alterTerrain(new Coline(), new Prairie(), 16);
		carte[17][23].alterTerrain(new Coline(), new Prairie(), 16);

		carte[16][17].alterTerrain(new Plaine(), new Foret(), 14);
		carte[16][18].alterTerrain(new Plaine(), new Jungle(), 16);
		carte[16][19].alterTerrain(new Plaine(), new Jungle(), 16);
		carte[16][20].alterTerrain(new Plaine(), new Jungle(), 16);
		carte[16][21].alterTerrain(new Coline(), new Jungle(), 16);
		carte[16][22].alterTerrain(new Coline(), new Foret(), 16);
		carte[16][23].alterTerrain(new Coline(), new Prairie(), 16);
		carte[16][24].alterTerrain(new Coline(), new Prairie(), 16);
		carte[16][25].alterTerrain(new Coline(), new Prairie(), 17);

		carte[15][17].alterTerrain(new Coline(), new Foret(), 14);
		carte[15][18].alterTerrain(new Plaine(), new Jungle(), 16);
		carte[15][19].alterTerrain(new Plaine(), new Jungle(), 16);
		carte[15][20].alterTerrain(new Coline(), new Jungle(), 16);
		carte[15][21].alterTerrain(new Montagne(), new None(), 15);
		carte[15][22].alterTerrain(new Montagne(), new None(), 15);
		carte[15][23].alterTerrain(new Montagne(), new None(), 15);
		carte[15][24].alterTerrain(new Montagne(), new None(), 17);
		carte[15][25].alterTerrain(new Plage(), new Prairie(), 17);
		carte[15][26].alterTerrain(new Plage(), new Prairie(), 17);

		carte[14][17].alterTerrain(new Coline(), new Foret(), 14);
		carte[14][18].alterTerrain(new Coline(), new Jungle(), 14);
		carte[14][19].alterTerrain(new Coline(), new Jungle(), 16);
		carte[14][20].alterTerrain(new Coline(), new Jungle(), 15);
		carte[14][21].alterTerrain(new Montagne(), new None(), 15);
		carte[14][22].alterTerrain(new Litoral(), new None());
		carte[14][23].alterTerrain(new Litoral(), new None());
		carte[14][24].alterTerrain(new Litoral(), new None());
		carte[14][25].alterTerrain(new Montagne(), new None(), 17);
		carte[14][26].alterTerrain(new Montagne(), new None(), 17);
		carte[14][27].alterTerrain(new Montagne(), new None(), 17);

		carte[13][17].alterTerrain(new Plaine(), new Foret(), 14);
		carte[13][18].alterTerrain(new Coline(), new Jungle(), 14);
		carte[13][19].alterTerrain(new Coline(), new Foret(), 15);
		carte[13][20].alterTerrain(new Montagne(), new None(), 15);
		carte[13][21].alterTerrain(new Litoral(), new None());
		carte[13][22].alterTerrain(new Litoral(), new None());
		
		//Afrique
		carte[31][16].alterTerrain(new Coline(), new Desert(), 5);//éthiopie
		carte[31][17].alterTerrain(new Plage(), new Desert(), 2);
		carte[31][20].alterTerrain(new Litoral(), new None());
		carte[31][21].alterTerrain(new Montagne(), new None(), 3);
		carte[31][22].alterTerrain(new Montagne(), new None(), 3);

		carte[30][15].alterTerrain(new Plage(), new Desert(), 2);
		carte[30][16].alterTerrain(new Plage(), new Desert(), 2);
		carte[30][17].alterTerrain(new Montagne(), new None(), 2);
		carte[30][18].alterTerrain(new Montagne(), new None(), 2);
		carte[30][19].alterTerrain(new Montagne(), new None(), 2);
		carte[30][20].alterTerrain(new Montagne(), new None(), 2);
		carte[30][21].alterTerrain(new Litoral(), new None());
		carte[30][22].alterTerrain(new Litoral(), new None());

		carte[29][13].alterTerrain(new Plage(), new Prairie(), 2);
		carte[29][14].alterTerrain(new Plaine(), new Desert(), 2);
		carte[29][15].alterTerrain(new Plaine(), new Desert(), 2);
		carte[29][16].alterTerrain(new Coline(), new Desert(), 2);
		carte[29][17].alterTerrain(new Coline(), new Desert(), 2);
		carte[29][18].alterTerrain(new Coline(), new Desert(), 2);
		carte[29][19].alterTerrain(new Lac(), new None(), 2);
		carte[29][20].alterTerrain(new Lac(), new None(), 2);
		carte[29][21].alterTerrain(new Montagne(), new None(), 2);
		carte[29][22].alterTerrain(new Montagne(), new None(), 2);
		carte[29][23].alterTerrain(new Litoral(), new None());

		carte[28][13].alterTerrain(new Plage(), new Prairie(), 2);
		carte[28][14].alterTerrain(new Coline(), new Desert(), 2);
		carte[28][15].alterTerrain(new Coline(), new Desert(), 2);
		carte[28][16].alterTerrain(new Coline(), new Desert(), 2);
		carte[28][17].alterTerrain(new Coline(), new Desert(), 2);
		carte[28][18].alterTerrain(new Coline(), new Prairie(), 9);
		carte[28][19].alterTerrain(new Coline(), new Prairie(), 9);
		carte[28][20].alterTerrain(new Coline(), new Prairie(), 2);
		carte[28][21].alterTerrain(new Montagne(), new None(), 2);
		carte[28][22].alterTerrain(new Montagne(), new None(), 2);
		carte[28][23].alterTerrain(new Montagne(), new None(), 2);

		carte[27][13].alterTerrain(new Plage(), new None(), 5);
		carte[27][14].alterTerrain(new Coline(), new Desert(), 5);
		carte[27][15].alterTerrain(new Coline(), new Desert(), 2);
		carte[27][16].alterTerrain(new Coline(), new Desert(), 3);
		carte[27][17].alterTerrain(new Coline(), new Desert(), 2);
		carte[27][18].alterTerrain(new Coline(), new Foret(), 9);
		carte[27][19].alterTerrain(new Coline(), new Foret(), 9);
		carte[27][20].alterTerrain(new Montagne(), new None(), 9);
		carte[27][21].alterTerrain(new Montagne(), new None(), 2);

		carte[26][12].alterTerrain(new Plage(), new None(), 3);
		carte[26][13].alterTerrain(new Coline(), new Desert(), 3);
		carte[26][14].alterTerrain(new Coline(), new Desert(), 3);
		carte[26][15].alterTerrain(new Coline(), new Desert(), 3);
		carte[26][16].alterTerrain(new Coline(), new Prairie(), 3);
		carte[26][17].alterTerrain(new Coline(), new Prairie(), 2);
		carte[26][18].alterTerrain(new Litoral(), new None());
		carte[26][19].alterTerrain(new Litoral(), new None());
		carte[26][20].alterTerrain(new Litoral(), new None());

		carte[25][12].alterTerrain(new Plage(), new Desert(), 3);
		carte[25][13].alterTerrain(new Coline(), new Desert(), 3);
		carte[25][14].alterTerrain(new Coline(), new Desert(), 3);
		carte[25][15].alterTerrain(new Coline(), new Desert(), 3);
		carte[25][16].alterTerrain(new Coline(), new Desert(), 3);
		carte[25][17].alterTerrain(new Litoral(), new None());
		carte[25][18].alterTerrain(new Litoral(), new None());

		carte[24][12].alterTerrain(new Montagne(), new None(), 3);
		carte[24][13].alterTerrain(new Coline(), new Prairie(), 3);
		carte[24][14].alterTerrain(new Coline(), new Desert(), 3);
		carte[24][15].alterTerrain(new Coline(), new Prairie(), 3);
		carte[24][16].alterTerrain(new Plage(), new None(), 2);
		carte[24][17].alterTerrain(new Litoral(), new None());

		carte[23][14].alterTerrain(new Plage(), new Prairie(), 3);
		carte[23][15].alterTerrain(new Plage(), new Prairie(), 3);
		carte[23][16].alterTerrain(new Litoral(), new None());

		carte[22][14].alterTerrain(new Litoral(), new None());
		carte[22][15].alterTerrain(new Litoral(), new None());
	
		//Europe
		carte[31][8].alterTerrain(new Coline(), new Prairie(), 1);
		carte[31][9].alterTerrain(new Coline(), new Prairie(), 1);

		carte[30][6].alterTerrain(new Coline(), new Prairie(), 1);
		carte[30][7].alterTerrain(new Coline(), new Prairie(), 1);
		carte[30][8].alterTerrain(new Coline(), new Prairie(), 1);
		carte[30][9].alterTerrain(new Coline(), new Prairie(), 1);
		carte[30][10].alterTerrain(new Coline(), new Prairie(), 1);

		carte[29][5].alterTerrain(new Montagne(), new Tundra(), 10);
		carte[29][6].alterTerrain(new Coline(), new Prairie(), 10);
		carte[29][7].alterTerrain(new Plaine(), new Prairie(), 1);
		carte[29][8].alterTerrain(new Plaine(), new Prairie(), 1);
		carte[29][9].alterTerrain(new Plaine(), new Prairie(), 8);
		carte[29][10].alterTerrain(new Plaine(), new Prairie(), 8);

		carte[28][5].alterTerrain(new Montagne(), new Tundra(), 10);
		carte[28][6].alterTerrain(new Montagne(), new Tundra(), 10);
		carte[28][7].alterTerrain(new Litoral(), new None());
		carte[28][8].alterTerrain(new Plaine(), new Prairie(), 8);
		carte[28][9].alterTerrain(new Plaine(), new Prairie(), 8);
		carte[28][10].alterTerrain(new Montagne(), new Prairie(), 8);
		carte[28][11].alterTerrain(new Litoral(), new None());
		carte[28][12].alterTerrain(new Litoral(), new None());

		carte[27][6].alterTerrain(new Montagne(), new Tundra(), 10);
		carte[27][7].alterTerrain(new Montagne(), new Tundra(), 10);
		carte[27][8].alterTerrain(new Litoral(), new None(), 19);
		carte[27][9].alterTerrain(new Coline(), new Foret(), 8);
		carte[27][10].alterTerrain(new Montagne(), new None(), 5);
		carte[27][11].alterTerrain(new Coline(), new Prairie(), 5);
		carte[27][12].alterTerrain(new Litoral(), new None(), 5);

		carte[26][7].alterTerrain(new Coline(), new Tundra(), 10);
		carte[26][8].alterTerrain(new Plaine(), new Foret(), 4);
		carte[26][9].alterTerrain(new Coline(), new Prairie(), 4);
		carte[26][10].alterTerrain(new Montagne(), new None(), 3);
		carte[26][11].alterTerrain(new Litoral(), new None(), 3);

		carte[25][8].alterTerrain(new Litoral(), new None());
		carte[25][9].alterTerrain(new Plaine(), new Foret(), 9);
		carte[25][10].alterTerrain(new Coline(), new Prairie(), 3);
		carte[25][11].alterTerrain(new Litoral(), new None(), 6);

		carte[24][8].alterTerrain(new Coline(), new Prairie(), 2);
		carte[24][9].alterTerrain(new Litoral(), new None(), 2);
		carte[24][10].alterTerrain(new Litoral(), new None(), 3);
		carte[24][11].alterTerrain(new Montagne(), new None(), 6);

		carte[23][11].alterTerrain(new Litoral(), new None(), 6);
		carte[23][12].alterTerrain(new Litoral(), new None(), 6);
		carte[23][13].alterTerrain(new Litoral(), new None(), 3);

		carte[22][7].alterTerrain(new Montagne(), new None(), 19);//Islande
		
		
		//Arabie Turquie
		carte[33][11].alterTerrain(new Montagne(), new None(), 18);
		carte[33][12].alterTerrain(new Montagne(), new None(), 18);

		carte[32][11].alterTerrain(new Lac(), new None(), 1);
		carte[32][12].alterTerrain(new Montagne(), new None(), 18);
		carte[32][13].alterTerrain(new Plaine(), new Desert(), 18);
		carte[32][14].alterTerrain(new Litoral(), new None());
		carte[32][15].alterTerrain(new Litoral(), new None());

		carte[31][11].alterTerrain(new Lac(), new None(), 18);
		carte[31][12].alterTerrain(new Coline(), new Prairie(), 18);
		carte[31][13].alterTerrain(new Plaine(), new Desert(), 18);
		carte[31][14].alterTerrain(new Plaine(), new Desert(), 2);
		carte[31][15].alterTerrain(new Litoral(), new None());

		carte[30][11].alterTerrain(new Plaine(), new Prairie(), 18);
		carte[30][12].alterTerrain(new Litoral(), new None());
		carte[30][13].alterTerrain(new Plaine(), new Prairie(), 2);
		carte[30][14].alterTerrain(new Litoral(), new None());

		carte[29][11].alterTerrain(new Plaine(), new Prairie(), 18);
		carte[29][12].alterTerrain(new Litoral(), new None(), 2);
		
		
		//Inde
		carte[37][13].alterTerrain(new Plaine(), new Prairie(), 2);
		carte[37][14].alterTerrain(new Litoral(), new None(), 2);
		carte[37][15].alterTerrain(new Litoral(), new None(), 2);
		carte[37][16].alterTerrain(new Litoral(), new None(), 2);

		carte[36][13].alterTerrain(new Plaine(), new Prairie(), 2);
		carte[36][14].alterTerrain(new Coline(), new Prairie(), 2);
		carte[36][15].alterTerrain(new Plaine(), new Prairie(), 2);
		carte[36][16].alterTerrain(new Litoral(), new None(), 2);
		carte[36][17].alterTerrain(new Litoral(), new None(), 2);

		carte[35][12].alterTerrain(new Montagne(), new None(), 2);
		carte[35][13].alterTerrain(new Plaine(), new Prairie(), 2);
		carte[35][14].alterTerrain(new Coline(), new Prairie(), 2);
		carte[35][15].alterTerrain(new Coline(), new Prairie(), 2);
		carte[35][16].alterTerrain(new Coline(), new Prairie(), 2);
		carte[35][17].alterTerrain(new Coline(), new Prairie(), 2);

		carte[34][12].alterTerrain(new Montagne(), new None(), 2);
		carte[34][13].alterTerrain(new Coline(), new Desert(), 2);
		carte[34][14].alterTerrain(new Coline(), new Prairie(), 2);
		carte[34][15].alterTerrain(new Coline(), new Prairie(), 2);
		carte[34][16].alterTerrain(new Litoral(), new None(), 2);

		carte[33][13].alterTerrain(new Plaine(), new Prairie(), 2);
		carte[33][14].alterTerrain(new Litoral(), new None(), 2);
		carte[33][15].alterTerrain(new Litoral(), new None(), 2);
		carte[33][16].alterTerrain(new Litoral(), new None(), 2);
		
		//URSS
		carte[49][6].alterTerrain(new Coline(), new Tundra(), 1);
		carte[49][7].alterTerrain(new Coline(), new Tundra(), 1);

		carte[48][6].alterTerrain(new Coline(), new Tundra(), 1);
		carte[48][7].alterTerrain(new Coline(), new Tundra(), 1);

		carte[47][6].alterTerrain(new Coline(), new Tundra(), 1);
		carte[47][7].alterTerrain(new Coline(), new Tundra(), 1);
		carte[47][8].alterTerrain(new Coline(), new Tundra(), 1);

		carte[46][6].alterTerrain(new Coline(), new Tundra(), 1);
		carte[46][7].alterTerrain(new Coline(), new Tundra(), 1);
		carte[46][8].alterTerrain(new Coline(), new Tundra(), 1);
		carte[46][9].alterTerrain(new Coline(), new Tundra(), 1);

		carte[45][5].alterTerrain(new Plaine(), new Foret(), 1);
		carte[45][6].alterTerrain(new Coline(), new Tundra(), 1);
		carte[45][7].alterTerrain(new Coline(), new Tundra(), 1);

		carte[44][5].alterTerrain(new Coline(), new Tundra(), 1);
		carte[44][6].alterTerrain(new Coline(), new Tundra(), 1);
		carte[44][7].alterTerrain(new Coline(), new Tundra(), 1);
		carte[44][8].alterTerrain(new Litoral(), new None(), 1);
		carte[44][9].alterTerrain(new Coline(), new Prairie(), 1);
		carte[44][10].alterTerrain(new Coline(), new Prairie(), 7);

		carte[43][6].alterTerrain(new Plaine(), new Foret(), 1);
		carte[43][7].alterTerrain(new Coline(), new Tundra(), 1);
		carte[43][8].alterTerrain(new Plaine(), new Prairie(), 1);

		carte[42][6].alterTerrain(new Plaine(), new Tundra(), 1);
		carte[42][7].alterTerrain(new Coline(), new Tundra(), 1);
		carte[42][8].alterTerrain(new Plaine(), new Foret(), 1);

		carte[41][6].alterTerrain(new Plaine(), new Tundra(), 1);
		carte[41][7].alterTerrain(new Coline(), new Tundra(), 1);
		carte[41][8].alterTerrain(new Coline(), new Tundra(), 1);
		carte[41][9].alterTerrain(new Coline(), new Foret(), 1);

		carte[40][5].alterTerrain(new Plaine(), new Tundra(), 1);
		carte[40][6].alterTerrain(new Coline(), new Tundra(), 1);
		carte[40][7].alterTerrain(new Coline(), new Tundra(), 1);
		carte[40][8].alterTerrain(new Coline(), new Tundra(), 1);
		carte[40][9].alterTerrain(new Plaine(), new Foret(), 1);

		carte[39][5].alterTerrain(new Plaine(), new Foret(), 1);
		carte[39][6].alterTerrain(new Coline(), new Foret(), 1);
		carte[39][7].alterTerrain(new Coline(), new Foret(), 1);
		carte[39][8].alterTerrain(new Coline(), new Foret(), 1);
		carte[39][9].alterTerrain(new Plaine(), new Prairie(), 1);

		carte[38][4].alterTerrain(new Coline(), new Foret(), 1);
		carte[38][5].alterTerrain(new Coline(), new Foret(), 1);
		carte[38][6].alterTerrain(new Coline(), new Foret(), 1);
		carte[38][7].alterTerrain(new Coline(), new Foret(), 1);
		carte[38][8].alterTerrain(new Coline(), new Foret(), 1);
		carte[38][9].alterTerrain(new Coline(), new Prairie(), 1);

		carte[37][4].alterTerrain(new Coline(), new Foret(), 1);
		carte[37][5].alterTerrain(new Coline(), new Foret(), 1);
		carte[37][6].alterTerrain(new Coline(), new Foret(), 1);
		carte[37][7].alterTerrain(new Coline(), new Foret(), 1);
		carte[37][8].alterTerrain(new Coline(), new Prairie(), 1);
		carte[37][9].alterTerrain(new Coline(), new Prairie(), 1);
		carte[37][10].alterTerrain(new Coline(), new Prairie(), 1);

		carte[36][5].alterTerrain(new Montagne(), new None(), 1);
		carte[36][6].alterTerrain(new Montagne(), new None(), 1);
		carte[36][7].alterTerrain(new Coline(), new Prairie(), 1);
		carte[36][8].alterTerrain(new Coline(), new Prairie(), 1);
		carte[36][9].alterTerrain(new Coline(), new Prairie(), 1);

		carte[35][6].alterTerrain(new Montagne(), new None(), 1);
		carte[35][7].alterTerrain(new Montagne(), new None(), 1);
		carte[35][8].alterTerrain(new Montagne(), new None(), 1);
		carte[35][9].alterTerrain(new Montagne(), new None(), 1);

		carte[34][5].alterTerrain(new Coline(), new Prairie(), 1);
		carte[34][6].alterTerrain(new Coline(), new Prairie(), 1);
		carte[34][7].alterTerrain(new Coline(), new Prairie(), 1);
		carte[34][8].alterTerrain(new Coline(), new Prairie(), 1);
		carte[34][9].alterTerrain(new Coline(), new Foret(), 1);
		carte[34][10].alterTerrain(new Coline(), new Prairie(), 1);
		carte[34][11].alterTerrain(new Coline(), new Prairie(), 1);

		carte[33][5].alterTerrain(new Plage(), new Foret(), 1);
		carte[33][6].alterTerrain(new Coline(), new Prairie(), 1);
		carte[33][7].alterTerrain(new Coline(), new Prairie(), 1);
		carte[33][8].alterTerrain(new Coline(), new Foret(), 1);
		carte[33][9].alterTerrain(new Lac(), new None(), 1);
		carte[33][10].alterTerrain(new Lac(), new None(), 1);

		carte[32][6].alterTerrain(new Plage(), new Prairie(), 1);
		carte[32][7].alterTerrain(new Coline(), new Prairie(), 1);
		carte[32][8].alterTerrain(new Coline(), new Prairie(), 1);
		carte[32][9].alterTerrain(new Coline(), new Prairie(), 1);
		carte[32][10].alterTerrain(new Coline(), new Foret(), 1);

		carte[31][7].alterTerrain(new Coline(), new Prairie(), 1);
		carte[31][10].alterTerrain(new Coline(), new Prairie(), 1);

		carte[0][6].alterTerrain(new Coline(), new Tundra(), 1); //pointe de l'Alaska, parceque les Americains sont des capitalistes qui achetent les pays pendant leur crise économique
		carte[0][7].alterTerrain(new Coline(), new Tundra(), 1);
		
		
		//Japon Corée
		carte[45][8].alterTerrain(new Litoral(), new None(), 1);
		carte[45][9].alterTerrain(new Litoral(), new None(), 7);
		carte[45][10].alterTerrain(new Litoral(), new None(), 7);
		carte[45][11].alterTerrain(new Coline(), new Prairie(), 7);
		carte[45][12].alterTerrain(new Coline(), new Prairie(), 7);

		carte[44][11].alterTerrain(new Litoral(), new None(), 7);
		carte[44][12].alterTerrain(new Coline(), new Foret(), 7);

		carte[43][11].alterTerrain(new Plaine(), new Prairie(), 7);
		carte[43][12].alterTerrain(new Litoral(), new None(), 7);
		carte[43][13].alterTerrain(new Litoral(), new None());
		
		//Chine (un peu grande mais en vrai, le tibet n'existe pas)
		carte[43][9].alterTerrain(new Coline(), new Prairie(), 7);//territoire de la Chine, japonais
		carte[43][10].alterTerrain(new Coline(), new Prairie(), 7);
		
		carte[42][9].alterTerrain(new Coline(), new Desert(), 7);
		carte[42][10].alterTerrain(new Coline(), new Foret(), 7);
		carte[42][11].alterTerrain(new Plaine(), new Prairie(), 7);
		carte[42][12].alterTerrain(new Plaine(), new Prairie(), 11);
		carte[42][13].alterTerrain(new Litoral(), new None());
		
		carte[41][10].alterTerrain(new Coline(), new Desert(), 12);
		carte[41][11].alterTerrain(new Coline(), new Desert(), 12);
		carte[41][12].alterTerrain(new Plaine(), new Prairie(), 11);
		carte[41][13].alterTerrain(new Plaine(), new Prairie(), 11);
		carte[41][14].alterTerrain(new Plage(), new None(), 11);

		carte[40][10].alterTerrain(new Coline(), new Desert(), 12);
		carte[40][11].alterTerrain(new Coline(), new Desert(), 12);
		carte[40][12].alterTerrain(new Coline(), new Desert(), 12);
		carte[40][13].alterTerrain(new Coline(), new Foret(), 11);
		carte[40][14].alterTerrain(new Coline(), new Foret(), 11);

		carte[39][10].alterTerrain(new Coline(), new Desert(), 12);
		carte[39][11].alterTerrain(new Coline(), new Desert(), 12);
		carte[39][12].alterTerrain(new Montagne(), new Foret(), 12);
		carte[39][13].alterTerrain(new Montagne(), new None(), 11);
		carte[39][14].alterTerrain(new Coline(), new Foret(), 11);

		carte[38][10].alterTerrain(new Coline(), new Desert(), 12);
		carte[38][11].alterTerrain(new Montagne(), new None(), 12);
		carte[38][12].alterTerrain(new Montagne(), new None(), 11);
		carte[38][13].alterTerrain(new Montagne(), new None(), 11);
		carte[38][14].alterTerrain(new Coline(), new Foret(), 11);

		carte[37][12].alterTerrain(new Montagne(), new None(), 11);
		carte[37][11].alterTerrain(new Montagne(), new None(), 11);

		carte[36][10].alterTerrain(new Coline(), new Desert(), 11);
		carte[36][11].alterTerrain(new Coline(), new Desert(), 11);
		carte[36][12].alterTerrain(new Montagne(), new None(), 11);

		carte[35][10].alterTerrain(new Coline(), new Desert(), 11);
		carte[35][11].alterTerrain(new Montagne(), new None(), 11);
		
		//Indochine, Indonésie
		carte[45][18].alterTerrain(new Coline(), new Foret(), 9); //on reviens de la droite vers la gauche
		carte[45][19].alterTerrain(new Litoral(), new None());
		
		carte[44][18].alterTerrain(new Plage(), new None(), 9);
		carte[44][19].alterTerrain(new Coline(), new Prairie(), 9);

		carte[43][17].alterTerrain(new Litoral(), new None());
		carte[43][18].alterTerrain(new Litoral(), new None());
		carte[43][19].alterTerrain(new Coline(), new Prairie(), 9);
		carte[43][20].alterTerrain(new Litoral(), new None());

		carte[42][14].alterTerrain(new Litoral(), new None());
		carte[42][15].alterTerrain(new Plage(), new None(), 0);
		carte[42][16].alterTerrain(new Plage(), new None(), 0);
		carte[42][17].alterTerrain(new Litoral(), new None());
		carte[42][18].alterTerrain(new Plage(), new None(), 9);
		carte[42][19].alterTerrain(new Coline(), new Prairie(), 9);
		carte[42][20].alterTerrain(new Litoral(), new None());

		carte[41][15].alterTerrain(new Litoral(), new None());
		carte[41][16].alterTerrain(new Plaine(), new Prairie(), 0);
		carte[41][17].alterTerrain(new Coline(), new Foret(), 0);
		carte[41][18].alterTerrain(new Plage(), new None(), 9);
		carte[41][19].alterTerrain(new Litoral(), new None(), 9);
		carte[41][20].alterTerrain(new Litoral(), new None());
		carte[41][21].alterTerrain(new Litoral(), new None());

		carte[40][15].alterTerrain(new Plage(), new None(), 3);
		carte[40][16].alterTerrain(new Litoral(), new None());
		carte[40][17].alterTerrain(new Litoral(), new None());
		carte[40][18].alterTerrain(new Coline(), new Prairie(), 9);
		carte[40][19].alterTerrain(new Coline(), new Prairie(), 9);

		carte[39][15].alterTerrain(new Plaine(), new Prairie(), 11);
		carte[39][16].alterTerrain(new Plaine(), new Prairie(), 2);
		carte[39][17].alterTerrain(new Plaine(), new Prairie(), 9);
		carte[39][18].alterTerrain(new Coline(), new Prairie(), 9);

		carte[38][15].alterTerrain(new Plage(), new None(), 7);
		carte[38][16].alterTerrain(new Litoral(), new None());
		carte[38][17].alterTerrain(new Coline(), new Prairie(), 9);
	
	//Australie
		carte[47][24].alterTerrain(new Litoral(), new None(), 2);
		carte[47][23].alterTerrain(new Litoral(), new None(), 2);

		carte[46][22].alterTerrain(new Coline(), new Desert(), 2);
		carte[46][23].alterTerrain(new Coline(), new Prairie(), 2);
		carte[46][24].alterTerrain(new Coline(), new Prairie(), 2);
		carte[46][25].alterTerrain(new Litoral(), new None(), 2);

		carte[45][20].alterTerrain(new Plaine(), new Prairie(), 2);
		carte[45][21].alterTerrain(new Plaine(), new Prairie(), 2);
		carte[45][22].alterTerrain(new Plaine(), new Prairie(), 2);
		carte[45][23].alterTerrain(new Plaine(), new Prairie(), 2);
		carte[45][24].alterTerrain(new Plaine(), new Prairie(), 2);
		carte[45][25].alterTerrain(new Litoral(), new None(), 2);

		carte[44][20].alterTerrain(new Plaine(), new Desert(), 2);
		carte[44][21].alterTerrain(new Plaine(), new Prairie(), 2);
		carte[44][22].alterTerrain(new Plaine(), new Prairie(), 2);
		carte[44][23].alterTerrain(new Plaine(), new Desert(), 2);
		carte[44][24].alterTerrain(new Litoral(), new None(), 2);
		carte[44][25].alterTerrain(new Litoral(), new None(), 2);

		carte[43][21].alterTerrain(new Plaine(), new Desert(), 2);
		carte[43][22].alterTerrain(new Coline(), new Desert(), 2);
		carte[43][23].alterTerrain(new Plage(), new None(), 2);
		carte[43][24].alterTerrain(new Litoral(), new None(), 2);

		carte[42][21].alterTerrain(new Plaine(), new Desert(), 2);
		carte[42][22].alterTerrain(new Plaine(), new Desert(), 2);
		carte[42][23].alterTerrain(new Plaine(), new Desert(), 2);
		carte[42][24].alterTerrain(new Litoral(), new None(), 2);

		carte[41][22].alterTerrain(new Plaine(), new Desert(), 2);
		carte[41][23].alterTerrain(new Plaine(), new Desert(), 2);
		
		//update de la map
		for(int x = 0; x < tailleX; x++) {
			for(int y = 0; y < tailleY; y++) {
				carte[x][y].updateStats();
			}
		}
		
		map = carte;
		
	}
	
	public Case[][] getMap(){return map;}
	public ArrayList<Civilisation> getCivs(){return civs;}
}
