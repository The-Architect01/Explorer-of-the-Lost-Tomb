package Rooms;
//
//
//import java.util.Random;
//import java.io.*;
//
public class Polish {/*DISABLED*/}
//
//	private static Random rand = new Random();
//	private static Room[][] map;
//
//	public static void fillMaze() throws ClassNotFoundException, IOException {
//		FileInputStream readFile = new FileInputStream("CurrentLevel.dng"); //Opens the File containing the maze
//		ObjectInputStream dungeonIn = new ObjectInputStream(readFile);
//		map = (Room[][])dungeonIn.readObject();
//
//		readFile.close();
//		dungeonIn.close();
//		//	map = dungeonLevel;
//
//		int y = map.length; //Sets a counter of rows
//		int x = map[0].length; //sets a counter of columns
//
//		for(int i = 0; i<y;i++) {
//			for(int j = 0; j<x;j++) {
//				if(map[i][j]!=null) {
//					if (map[i][j].getClass() == Entrance.class) {
//						System.out.println("Entrance is at: " + i +"," +j);
//					}else if (map[i][j].getClass() == Exit.class) {
//						System.out.println("Exit is at: " + i+","+j);
//					}
//				}
//			}
//		}
//			//sets the number of exits a room has
//			for(int i = 0; i<y;i++) {
//				for(int j = 0; j<x;j++) {
//					if(map[i][j]==null) {continue;}
//					map[i][j].numberOfExits = rand.nextInt(3)+2;
//				}
//			}
//
//			//sets the map's room's settings
//			for (int i = 0; i < y; i++) {
//				for (int j = 0; j < x; j++) {
//					fillRoom(i,j);
//				}
//			}
//
//			//prints the output
//			for (int i = 0; i<y;i++) {
//				for (int j = 0;j<x;j++) {
//					printMap(j,i);
//				}
//				System.out.println();
//			}
//		if(!new MapChecker(mapOut(),getEntrance(),getExit()).checkMap()) {Maze.ConstructFloor(1);}
//
//		//	return map;
//	}
//
//	private static void fillRoom(int y, int x) {//Assigns room exits
//		if(map[y][x]==null) {//skips room if no room
//			return;
//		}else {
//			for(int i = 0; i<map[y][x].numberOfExits; i++) {
//				int num = rand.nextInt(4);
//				switch (num){
//				case 0:
//					if (map[y][x].hasNorthExit) {
//						i--;
//						continue;
//					}else {
//						try { //sets the room's north exit
//							if (map[y-1][x] != null && (map[y-1][x].numberOfExits>0 || map[y-1][x].hasSouthExit)) {
//								map[y][x].hasNorthExit=true;
//								map[y-1][x].numberOfExits-=1;
//								map[y-1][x].hasSouthExit=true;
//							}else {
//								map[y][x].hasNorthExit=false;
//							}
//							//Catch exits going out of the map
//						}catch (IndexOutOfBoundsException | NullPointerException e) {
//							map[y][x].hasNorthExit=false;
//						}
//					}
//					break;
//				case 1:
//					if (map[y][x].hasEastExit) {
//						i--;
//						continue;
//					}else {
//						try {//sets the room's east exit
//							if(map[y][x+1] !=null && (map[y][x+1].numberOfExits>0 || map[y][x+1].hasWestExit)) {
//								map[y][x].hasEastExit=true;
//								map[y][x+1].numberOfExits-=1;
//								map[y][x+1].hasWestExit=true;
//							}else {
//								map[y][x].hasEastExit=false;
//							}
//							//Catch exits going out of map
//						}catch(IndexOutOfBoundsException | NullPointerException e) {
//							map[y][x].hasEastExit=false;
//						}
//					}
//					break;
//				case 2:
//					if (map[y][x].hasSouthExit) {
//						i--;
//						continue;
//					}else {
//						try {//sets the room's south exit
//							if(map[y+1][x] !=null && (map[y+1][x].numberOfExits>0 || map[y+1][x].hasNorthExit)) {
//								map[y][x].hasSouthExit=true;
//								map[y+1][x].numberOfExits-=1;
//								map[y+1][x].hasNorthExit=true;
//							}else {
//								map[y][x].hasSouthExit=false;
//							}
//							//Catch exits going out of map
//						}catch(IndexOutOfBoundsException | NullPointerException e) {
//							map[y][x].hasSouthExit=false;
//						}
//					}
//					break;
//				case 3:
//					if (map[y][x].hasWestExit) {
//						i--;
//						continue;
//					}else {
//						try {//sets the room's west exit
//							if(map[y][x-1] !=null && (map[y][x-1].numberOfExits>0 && map[y][x-1].hasEastExit)) {
//								map[y][x].hasWestExit=true;
//								map[y][x-1].numberOfExits-=1;
//								map[y][x-1].hasEastExit=true;
//							}else {
//								map[y][x].hasWestExit=false;
//							}
//							//Catch exits going out of map
//						}catch(IndexOutOfBoundsException | NullPointerException e) {
//							map[y][x].hasWestExit=false;
//						}
//					}
//					break;
//				}
//			}
//		}
//	}
//
//	private static void printMap(int x, int y) {//prints the map
////		try{
////			boolean north = map[y][x].hasNorthExit;
////			boolean south = map[y][x].hasSouthExit;
////			boolean east = map[y][x].hasEastExit;
////			boolean west = map[y][x].hasWestExit;
////
////			if (north && south && east && west) {
////				System.out.print(Room.JUNCTION);
////			}else if(north && south && east) {
////				System.out.print(Room.RIGHT_DIV);
////			}else if(north && south && west) {
////				System.out.print(Room.LEFT_DIV);
////			}else if (north && east && west) {
////				System.out.print(Room.UP_DIV);
////			}else if(south && east && west) {
////				System.out.print(Room.DOWN_DIV);
////			}else if(north && west) {
////				System.out.print(Room.UPRIGHT_TURN);
////			}else if (north && east) {
////				System.out.print(Room.UPLEFT_TURN);
////			}else if (south &&west){
////				System.out.print(Room.DOWNRIGHT_TURN);
////			}else if (south && east) {
////				System.out.print(Room.DOWNLEFT_TURN);
////			}else if ((north && south)||north||south) {
////				System.out.print(Room.VERTICAL_WALL);
////			}else if ((east && west)||east||west) {
////				System.out.print(Room.HORIZONTAL_WALL);
////			}else {
////				System.out.print("·");
////			}
////		}catch(NullPointerException e) {
////			System.out.print("·");
////		}
//	}
//
//	private static char[][] mapOut(){
//		char[][] maping = new char[8][8];
//	//	for (int i = 0; i<map.length;i++) {
////			for(int j =0; j<map[0].length; j++) {
////				try{
////					boolean north = map[i][j].hasNorthExit;
////					boolean south = map[i][j].hasSouthExit;
////					boolean east = map[i][j].hasEastExit;
////					boolean west = map[i][j].hasWestExit;
////
////					if (north && south && east && west) {
////						maping[i][j]=Room.JUNCTION;
////					}else if(north && south && east) {
////						maping[i][j]=Room.RIGHT_DIV;
////					}else if(north && south && west) {
////						maping[i][j]=Room.LEFT_DIV;
////					}else if (north && east && west) {
////						maping[i][j]=Room.UP_DIV;
////					}else if(south && east && west) {
////						maping[i][j]=Room.DOWN_DIV;
////					}else if(north && west) {
////						maping[i][j]=Room.UPRIGHT_TURN;
////					}else if (north && east) {
////						maping[i][j]=Room.UPLEFT_TURN;
////					}else if (south &&west){
////						maping[i][j]=Room.DOWNRIGHT_TURN;
////					}else if (south && east) {
////						maping[i][j]=Room.DOWNLEFT_TURN;
////					}else if ((north && south)||north||south) {
////						maping[i][j]=Room.VERTICAL_WALL;
////					}else if ((east && west)||east||west) {
////						maping[i][j]=Room.HORIZONTAL_WALL;
////					}else {
////						maping[i][j]='·';
////					}
//				//}catch(NullPointerException e) {
//					//maping[i][j] = '·';
//				//}
//			//}
//		//}
//		//return maping;
//	}
//
//	private static int[] getExit() {
//		int[] sample = {-1,-1};
//		for (int i = 0; i <map.length;i++) {
//			for(int j = 0; j <map[0].length;j++) {
//				if (map[i][j] == null) {continue;}
//				if (map[i][j].getClass() == Exit.class) {
//					sample[0] = i;
//					sample[1] = j;
//				}
//			}
//		}
//		return sample;
//	}
//
//	private static int[] getEntrance() {
//		int[] sample = {-1,-1};
//		for (int i = 0; i <map.length;i++) {
//			for(int j = 0; j <map[0].length;j++) {
//				if (map[i][j] == null) {continue;}
//				if (map[i][j].getClass() == Entrance.class) {
//					sample[0] = i;
//					sample[1] = j;
//				}
//			}
//		}
//		return sample;
//	}
//}
//
///*
//So if you have a non-static field in a subclass inherited from a superclass, the field only changes in that instance like regular?
//*/