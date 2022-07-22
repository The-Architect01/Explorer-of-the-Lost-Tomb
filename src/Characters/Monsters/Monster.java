package Characters.Monsters;

import GameElements.Game;
import GameElements.GameObject;
import GameElements.RoomTileMap;
import Characters.PC.Character;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.Random;
import javax.swing.JOptionPane;


public class Monster extends GameObject{

	protected final EventHandler<MouseEvent> clicked = event -> { //If Monster is clicked, they are 'attacked'
		if(areNeighbors(this,Game.Player)||(getClass()==Boss.class && (((Game.Player.getWeapon().equalsIgnoreCase("Bow")||Game.Player.getWeapon().equalsIgnoreCase("Magic"))&&
				((Game.Player.getLocationX() == LocationX||Game.Player.getLocationX() == LocationX+1 
				|| Game.Player.getLocationY() == LocationY || Game.Player.getLocationY() == LocationY+1))))||
				((Game.Player.getWeapon().equalsIgnoreCase("Bow")||Game.Player.getWeapon().equalsIgnoreCase("Magic"))&&
				((Game.Player.getLocationX() == LocationX||Game.Player.getLocationY() == LocationY))))){
			Game.Player.Attack((GameObject)event.getSource());
		}else {
			JOptionPane.showMessageDialog(null, String.format("%s is not close enough to attack!", Game.Player.getCharName()));
		}
		if(stillAlive()) {monsterTurn(Game.Player);}
	};
	
	int Speed;
	float CR;

	float HEALPROB;
	float MOVEPROB;
	float ATTACKPROB;
	float WAITPROB;	
	float AGGRESSION;

	public Monster() {
		LocationX = 1;
		LocationY = 1;
	}

	public void monsterTurn(Character PC) {
		float rng = (float) Math.random();
		if(rng<HEALPROB) {//Heals NOTE: ONLY BOSS HAS HEAL CHANCE!
			Heal();
		}else if(rng<ATTACKPROB && areNeighbors(this,PC)) { //Attacks Player
			Attack(PC);
		}else if(rng<MOVEPROB) {//Moves Monster
			RoomTileMap.getTileMap().getChildren().remove(this);
			if(Math.random()<AGGRESSION) { //Moves monster closer to the player
				if(Math.random()<.5) {
					if(PC.getLocationX()>LocationX) {
						Move("East");
					}else {
						Move("West");
					}
				}else {
					if(PC.getLocationY()>LocationY) {
						Move("South");
					}else {
						Move("North");
					}
				}
			}else { // Moves the Monster in a random direction
				try {
					switch(Random.class.newInstance().nextInt(4)) {
					case 0:	//North
						Move("North");
						break;
					case 1:	//East
						Move("East");
						break;
					case 2:	//South
						Move("South");
						break;
					case 3:	//West
						Move("West");
						break;
					}
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			RoomTileMap.getTileMap().add(this, LocationX, LocationY);
		}else if(rng<WAITPROB) {
			return;
		}
	}	
	
	//Returns the Challenge Rating of the monster
	public float getCR() {return CR;}

	@Override
	public void death() {//Removes the monster from the map.
		RoomTileMap.getTileMap().getChildren().remove(this);
		RoomTileMap.getRoom().monsters.remove(this);
		JOptionPane.showMessageDialog(null, String.format("%s has died!",getName()));
		if (RoomTileMap.getRoom().monsters.isEmpty()) {
			RoomTileMap.getRoom().setDefeatAllMonsters(false);
			JOptionPane.showMessageDialog(null, String.format("%s has cleared this room of all monsters!", Game.Player.getName()));
		}
	}
}
/*
 * Monsters BOSS: Vampire (۞), Minotor (Ჾ), Dragon (꘨), Demon (Ɣ), [Monster]
 * Chief (ɤ) Mid-Boss: Vampire (۞), Minotar (Ჾ), [Monster] Chief (ɤ) Minions:
 * Skeleton (Ѧ), Kobold (ü), Orc (ö), Slime (꘢), Rats (წ), Cultist (ჯ), Spiders
 * (҂), Snakes (§) //ICONS: ǂ ɤ ¤ ꘣ ꘩ ꙮ Ѯ
 */
