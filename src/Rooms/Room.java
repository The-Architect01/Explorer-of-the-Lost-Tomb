package Rooms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import Characters.Monsters.*;

public class Room implements Serializable{

	private static final long serialVersionUID = 1L;

	//=======================================================================================================
	//Entrance/Exit Requirements
	//=======================================================================================================
	protected boolean hasNorthExit = false;
	protected boolean hasEastExit = false;
	protected boolean hasSouthExit = false;
	protected boolean hasWestExit = false;
	public int numberOfExits = 0;
	
	/*=======================================================================================================

	//=======================================================================================================
	//Room Details
	//=======================================================================================================
	*/
	protected boolean hasMonster = false;
	protected boolean hasTreasure = false;
	protected boolean defeatAllMonster = false;
	
	private Random rng = new Random();
	
	private String type = "";
	
	public ArrayList<Monster> monsters; 
	
	/*========================================================================================================
	//Methods//
	//========================================================================================================*/
	
	public String[] getExitDirections() { //returns an array containing all exit directions
		String[] returnArray = new String[4];
		int counter = 0;
		if(hasNorthExit) {
			returnArray[counter] = "North";
			counter++;
		}
		if(hasEastExit) {
			returnArray[counter] = "East";
			counter++;
		}
		if(hasSouthExit) {
			returnArray[counter] = "South";
			counter++;
		}
		if(hasWestExit) {
			returnArray[counter] = "West";
			counter++;
		}
		return returnArray;
	}

	public boolean hasExit(String direction) { //Checks if the room has an exit in the direction
		switch (direction.toLowerCase()) {
		case "north":
			if(hasNorthExit) {return true;}
			break;
		case "south":
			if(hasSouthExit) {return true;}
			break;
		case "east":
			if(hasEastExit) {return true;}
			break;
		case "west":
			if(hasWestExit) {return true;}
			break;
		}
		return false;
	}
	
	public Room(String type) {
		numberOfExits = rng.nextInt(3)+2; //Generates a number between 2 and 4
		for(int i = 0; i<numberOfExits;i++) { //Loops through until exit number is matched
			switch(rng.nextInt(4)) { //Tries to generate a exit in a random direction
			case 0:
				if (hasNorthExit) { //If the room already has an exit in this direction
									//Give the room another "chance" to get another direction
					i--;
					continue;
				}else {				//If not, give the room an exit in this direction
					hasNorthExit=true;
				}
				break;
			case 1: //see above
				if (hasEastExit) {
					i--;
					continue;
				}else {
					hasEastExit=true;
				}
				break;
			case 2: //see above
				if (hasSouthExit) {
					i--;
					continue;
				}else {
					hasSouthExit=true;
				}
				break;
			case 3: //see above
				if (hasWestExit) {
					i--;
					continue;
				}else {
					hasWestExit=true;
				}
				break;
			}
		}

		if (type != "") {								//Checks if the room is "normal"
			if (type.equalsIgnoreCase("entrance")) {	//Makes a new entrance
				hasTreasure = false;					//No treasure
				defeatAllMonster = false;				//No monsters clear requirement
				hasMonster = false;						//No monsters
				this.type = type;
			}else if(type.equalsIgnoreCase("exit")) {	//Makes a new Exit
				defeatAllMonster = true;				//Forces the player to fight
				hasMonster = true;						//Will generate a boss
				monsters = GameElements.Game.generateMonsters("Boss");
				this.type = type;
			}
		}else {
//			if(rng.nextInt(100) % 5 == 0) {				//One in 5 rooms will have treasure
//				hasTreasure = true;
//			}
			if(rng.nextInt(100) % 3 == 0) {				//One in 3 rooms will have monster(s)
				hasMonster = true;
				monsters = GameElements.Game.generateMonsters("");
			}
			if(rng.nextInt(100) % 3 == 0 && hasMonster) {//One in 9 rooms will force the player to defeat all
				defeatAllMonster = true;
			}
		}

	}
	
	
	public void overrideExit(String exit, boolean toValue) {//Forces the input to be open or closed
		switch (exit.toLowerCase()) {
		case "north":
			hasNorthExit = toValue;
			break;
		case "east":
			hasEastExit = toValue;
			break;
		case "south":
			hasSouthExit = toValue;
			break;
		case"west":
			hasWestExit = toValue;
			break;
		}
	}
	
	public void setPlayerX(int x) {
		GameElements.Game.Player.setLocationX(x);
	}
	
	public void setPlayerY(int y) {
		GameElements.Game.Player.setLocationY(y);
	}
	
	public boolean getDefeatAllMonsters() {
		return defeatAllMonster;
	}

	public void setDefeatAllMonsters(boolean value) {
		defeatAllMonster = value;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean getHasMonsters() {
		return hasMonster;
	}

	public Monster getBoss() {
		for(Monster monster:monsters) {
			if(monster.getClass() == Boss.class) {
				return monster;
			}
		}
		return null;
	}
}
