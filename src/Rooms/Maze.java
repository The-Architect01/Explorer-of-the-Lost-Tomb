package Rooms;
//
//import java.util.Random;
//import java.io.*;
//
public class Maze{//DISABLED
//
//	private static Random rand = new Random();
//
//	private static Room[][] map;
//
//	private static int i;
//	private static int j;
//
//	static int entranceX;
//	static int entranceY;
//	static int thislevel;
//	
//	public static void ConstructFloor(int level) throws IOException, ClassNotFoundException { //Works
//		thislevel = level;
//		addEntrance(level);
//		addExit();
//		quidam(entranceX,entranceY);
//		
//		FileOutputStream dungeonLevel = new FileOutputStream("CurrentLevel.dng");
//		ObjectOutputStream dungeonWriter = new ObjectOutputStream(dungeonLevel);
//		dungeonWriter.writeObject(map);
//		dungeonWriter.close();
//		//dungeonLevel.close();
////		for (int k = 0; k<j;k++) {
////			for(int l = 0; l<i;l++) {
////				if (map[k][l] == null) {
////					dungeonWriter.write("null ");
////				}else {
////					if (map[k][l].getClass().toString().contains("Entrance")) {
////						dungeonWriter.write("Entr ");
////					} else if (map[k][l].getClass().toString().contains("Exit")) {
////						dungeonWriter.write("Exit ");
////					}else {
////						dungeonWriter.write("Room ");
////					}
////				}
////			}
////			dungeonWriter.write("\n");
////		}
//		
//		Polish.fillMaze();
//		dungeonLevel.close();
//	}
//
//	private static void addEntrance(int level) { //Good
//		boolean valid = true;
//		do {
//			valid=true; //Stops the resulting infinite loop
//
//			//Generates Map
//			i = Math.abs(8); //Change if play test takes too long 
//			j = Math.abs(8); //Change if play test takes too long
//			map = new Room[j][i];
//			for (int x = 0; x<j;x++) {
//				for(int y=0;y<i;y++) {
//					map[x][y] = null;
//				}
//			}
//
//			//Generates Maze Entrance
//			int x;
//			int y;
//
//			try {
//
//				x = rand.nextInt(i+5);
//				y = rand.nextInt(j+5);
////				map[y][x] = new Entrance();
//
//			}catch(Exception e) {
//
//				valid=false;
//				continue;
//			}
//
//			entranceX = x;
//			entranceY = y;
//
//		}while (!valid);
//	}
//
//	private static void addExit() { //Good
//		boolean valid = true;
//
//		do {
//			valid = true;
//
//			//Generates Maze Exit
//			int x;
//			int y;
//			try {
//
//				x = rand.nextInt(i);
//				y = rand.nextInt(j);
//
//				if (map[y][x]!=null) {
//					valid=false;
//				}else {
//	//				map[y][x] = new Exit();
//				}
//
//			}catch (Exception e) {
//				valid=false;
//				continue;
//			}
//
//		} while (!valid);
//	}
//
//	public static boolean ListContains(String[] list, String value) { //Good
//		for(int i=0;i<list.length;i++) {
//			if(list[i].equals(value)) {//checks if the item in the list is equal to the search value 
//				return true;
//			}
//		}
//		return false;
//	}
//
//	private static void quidam(int roomX, int roomY) { //Works?
//		boolean terminate = false;
//		int x = roomX;
//		int y = roomY;
//		do {
//			Room room = map[y][x];
//
//			if (room.getClass() == Exit.class){
//				terminate = true;
//				continue;
//			}
//
//			int direction = rand.nextInt(4);
//			boolean valid = true;
//			int newX = 0;
//			int newY = 0;
////			Room newRoom = new Room();
//			try {	
//				switch (direction) {
//				case 0: //North
//					newX = x;
//					newY = y-1;
//					break;
//				case 1: //East
//					newX = x-1;
//					newY = y;
//					break;
//				case 2: //South
//					newX = x;
//					newY = y+1;
//					break;
//				case 3: //West
//					newX = x+1;
//					newY = y;
//					break;
//				}
//
//				if (!valid) {
//					continue;
//				}
//
//				if (map[newY][newX]==null) { 
//		//			map[newY][newX] = newRoom;
//				}
//
//				x = newX;
//				y = newY;
//
//			}catch(Exception e) {
//				x = roomX;
//				y = roomY;
//			}	
//		}while(!terminate);
//	}
//	
}
//
///*NOTES
// * 
// * TOO MANY ISLANDS ARE FORMING WITH CURRENT ALGOITIHM. THE MAP CHECKING ALGORITHM DOES NOT CATCH ISLANDS WITH ACCURACY
// * THIS LEADS TO TWO DIFFERENT METHODS. ONE, FIGURE OUT A WAY TO HAVE AN ARRAY OF ROOM FORMED BASED ON PLAYER MOVEMENT.
// * IE: PICK RANDOM START LOCATION IN 8X8 GRID. CHOOSE A RANDOM NUMBER OF EXITS. CHOOSE A RANDOM EXIT. ADD ROOM TO THE 
// * ARRAY. WHEN THE PLAYER EXITS ROOM, REPEAT THE PROCESS BUT WITH A RANDOM GENERATOR 1 LESS, AND THE PREVIOUS EXIT ENABLED.
// * REPEAT PROCEESS. ROOM FEATURES WILL BE GENERATED ON ROOM CREATION.
// * 
// *  - - - - - - - -		- - - - - - - -		- - - - - - - -		- - - - - - - - 	- - - - - - - -		- - - - - - - -
// *  - - - - - - - -		- - - - - - - -		- - - - - - - -		- - - - - - - -		- - - - - - - -		- - - - - - - -
// *  - - - - - - - -		- - - - - - - -		- - - - - - - -		- - - - - - - -		- - - - - - - -		- - - - E - - -
// *  - - - - - - - -		- - R - - - - -		- - R R - - - -		- - R R R - - -		- - R R R - - -		- - R R R - - -
// *  - - O - - - - -		- - O - - - - -		- - O - - - - -		- - O - - - - -		- - O - R - - -		- - O - R - - -
// *  - - - - - - - -		- - - - - - - -		- - - - - - - -		- - - - - - - -		- - - - - - - -		- - - - - - - -
// *  - - - - - - - -		- - - - - - - -		- - - - - - - -		- - - - - - - -		- - - - - - - -		- - - - - - - -
// *  - - - - - - - -		- - - - - - - -		- - - - - - - -		- - - - - - - -		- - - - - - - -		- - - - - - - -
// * 
// * PROS:									CONS:
// * - DIFFERENT ROOMS FOR EACH PLAYER.		- STORAGE -- HOW TO ENSURE BACKTRACKING??
// * - ALWAYS SOLVABLE						- BASED ON RANDOMNESS AND COULD HAVE EXCEPTIONALLY LONG MAZES
// * 
// *
// * OPTION 2:
// * FIGURE OUT A WAY TO GENERATE AN ISLAND FREE MAP.
// * 
// * PROS:									CONS:
// * - RANDOM									- NOT ALWAYS SOLVABLE
// * - EASY TO STORE							- MORE COMPLEX BEGINNING THAN ENDING 
// * - BACKTRACABLE							- LIMITS THE SIZE OF THE MAZE
// */
