package GameElements;
import java.util.ArrayList;
import java.util.Random;
import Characters.Monsters.*;
import Rooms.Room;
import Characters.PC.Character;

/* This class contains the data to construct the actual game */
public class Game{

	static Room[][] map;
	static int x = 0;
	static int y = 0;
	public static int floorPass = 0;

	static Random rng = new Random();
	
	public static RoomTileMap floor;
	public static boolean hasExit = false;

	public static Character Player;
	
	public Game(Character PC) {//Start Game
		Player = PC;
		map = new Room[8][8];
		map[4][4] = new Room("Entrance");
		Room me = map[4][4];
		floor = new RoomTileMap(me);
		x = 4;
		y = 4;
		floorPass+=1;
	}

	public Game() {//New floor
		map = new Room[8][8];
		map[4][4] = new Room("Entrance");
		Room me = map[4][4];
		floor = new RoomTileMap(me);
		x = 4;
		y = 4;
		floorPass+=1;
	}
	
	//Resets Player Progress
	public static void reset() {floorPass = 0;}
	
	public static Room getNextRoom(String direction) {//Creates the next room
		
		Room nextRoom;
		
		switch(direction.toLowerCase()) {
		case "north":
			y-=1;
			break;
		case "south":
			y+=1;
			break;
		case "east":
			x+=1;
			break;
		case "west":
			x-=1;
			break;
		}

		if (map[x][y] != null) {
			nextRoom = map[x][y];
		}else {
			Room thisRoom;
			//Checks if map is exit
			if (!hasExit && (rng.nextInt(50) % 7 == 0)) {thisRoom = new Room("Exit"); hasExit = true;}else {thisRoom = new Room("");}

			//Checks bounds//
			if (x == 0) {thisRoom.overrideExit("West", false);}
			if (x == 7) {thisRoom.overrideExit("East", false);}
			if (y == 0) {thisRoom.overrideExit("North", false);}
			if (y == 7) {thisRoom.overrideExit("South", false);}

			//Checks rooms around//
			try {if (map[x-1][y].hasExit("West")) {thisRoom.overrideExit("East", true);
			}else if (!map[x-1][y].hasExit("West")) {thisRoom.overrideExit("East", false);}
			}catch(NullPointerException | ArrayIndexOutOfBoundsException e) {
				if (e.getClass() == ArrayIndexOutOfBoundsException.class) {
					thisRoom.overrideExit("East", false);
				}
			}
			try {if (map[x+1][y].hasExit("East")) {thisRoom.overrideExit("West", true);}
			else if (!map[x+1][y].hasExit("East")) {thisRoom.overrideExit("West", false);}
			}catch(NullPointerException | ArrayIndexOutOfBoundsException e) {
				if (e.getClass() == ArrayIndexOutOfBoundsException.class) {
					thisRoom.overrideExit("West", false);
				}
			}
			try {if (map[x][y+1].hasExit("North")){thisRoom.overrideExit("South", true);}
			else if(!map[x][y+1].hasExit("North")) {thisRoom.overrideExit("South", false);}
			}catch(NullPointerException | ArrayIndexOutOfBoundsException e) {
				if (e.getClass() == ArrayIndexOutOfBoundsException.class) {
					thisRoom.overrideExit("South", false);
				}
			}
			try {if (map[x][y-1].hasExit("South")){thisRoom.overrideExit("North", true);}
			else if(!map[x][y-1].hasExit("South")) {thisRoom.overrideExit("North",false);}
			}catch(NullPointerException | ArrayIndexOutOfBoundsException e) {
				if (e.getClass() == ArrayIndexOutOfBoundsException.class) {
					thisRoom.overrideExit("North", false);
				}
			}

			//Updates Map//
			nextRoom = thisRoom;
			map[x][y] = thisRoom;
		}

		//Sets the player entrance location
		switch (direction.toLowerCase()) {
		case "west":
			nextRoom.setPlayerX(0);
			nextRoom.setPlayerY(2);
			break;
		case "east":
			nextRoom.setPlayerX(7);
			nextRoom.setPlayerY(2);
			break;
		case "south":
			nextRoom.setPlayerX(3);
			nextRoom.setPlayerY(0);
			break;
		case "north":
			nextRoom.setPlayerX(3);
			nextRoom.setPlayerY(5);
			break;
		}
		return nextRoom;
	}
	
	public static ArrayList<Monster> generateMonsters(String override){ //Spawns Monsters
		ArrayList<Monster> spawns = new ArrayList<Monster>();
		if (override.equalsIgnoreCase("Boss")){
			spawns.add(new Boss());
		}else {
			float CR = 0;
			do {
				Monster newSpawn = new Rat();
				switch(rng.nextInt(4)) {
				case 0:
					newSpawn = new Rat();
					break;
				case 1:
					newSpawn = new Stirge();
					break;
				case 2:
					newSpawn = new Crawling_Hand();
					break;
				case 3:
					newSpawn = new Orc();
					break;
				}
				CR+=newSpawn.getCR();
				spawns.add(newSpawn);
			}while(CR<=1);
		}
		return spawns;
	}
}