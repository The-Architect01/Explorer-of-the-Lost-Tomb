package GameElements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.swing.JOptionPane;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

//Superclass for all NPCs/PCs
public class GameObject extends ImageView {

	protected int LocationX = 1;
	protected int LocationY = 1;

	//Animation Variables
	protected Image GameObjectHit; 
	protected Image GameObjectNom;
	Timeline takeDamageAnimation = new Timeline(new KeyFrame(
			Duration.millis(150), event ->{
				if(getImage()==GameObjectNom) {
					setImage(GameObjectHit);
				}else {
					setImage(GameObjectNom);
				}
			})
			);

	//Damage Related Variables
	protected String[] Strengths;
	protected String[] Weaknesses;
	protected String Weapon;

	//General Variables
	protected int MaxHP;
	protected int Attack;
	protected int currentHP;
	protected int AC;

	//Collision *BUGGED*
	private static ArrayList<GameObject> objectsInMap = new ArrayList<>();

	public GameObject() {
		currentHP = MaxHP;
		setFitHeight(90);
		setFitWidth(90);
		takeDamageAnimation.setOnFinished(event->{
			setImage(GameObjectNom);
		});
	}

	public String getName() {
		if(getClass() == Game.Player.getClass()) {return Game.Player.getCharName();}
		String name = this.toString();
		name = name.split("@")[0];
		name = name.replace('[', ' ');
		name = name.trim();
		return name.replace('_', ' ');
	}

	public static ArrayList<GameObject> getObjectsInMap(){return objectsInMap;}

	//MOVEMENT RELATED
	public void setLocationY(int Y){LocationY = Y;}

	public void setLocationX(int X) {LocationX = X;}

	public int getLocationX() {return LocationX;}

	public int getLocationY() {return LocationY;}

	public void Move(String Direction) {//Moves object
		switch(Direction.toLowerCase()) {
		case "north":
			if(!checkValid("North")) {return;}
			if(LocationY - 1 == 0) {LocationY = 1;} else {LocationY-=1;}
			setRotate(180);
			break;
		case "south":
			if(!checkValid("South")) {return;}
			if(LocationY + 1 == 4) {LocationY = 3;} else {LocationY+=1;}
			setRotate(0);
			break;
		case "east":
			if(!checkValid("East")) {return;}
			if(LocationX + 1 == 6) {LocationX = 5;} else {LocationX +=1;}
			setRotate(270);
			break;
		case "west":
			if(!checkValid("West")) {return;}
			if(LocationX - 1 == 0) {LocationY = 1;} else {LocationX-=1;}
			setRotate(90);
			break;
		}
	}

	protected boolean areNeighbors(GameObject obj1, GameObject obj2){
		if (((obj1.getLocationX()+1 == obj2.getLocationX() || obj1.getLocationX()-1 == obj2.getLocationX()) &&
				(obj1.getLocationY() == obj2.getLocationY()))|| // Checks if obj2 is to the left or right of obj1
				((obj1.getLocationY()+1 == obj2.getLocationY() || obj1.getLocationY()-1 == obj2.getLocationY())&&
						(obj1.getLocationX() == obj2.getLocationX()))) { //Checks if obj2 is above or below obj1
			return true;
		}
		return false;
	}

	public boolean checkValid(String direction) { // Checks if a location is valid
		try {
			return true;			
		}catch(NullPointerException e) {
			return true;
		}
	}

	//BATTLE RELATED
	public boolean Hit(int AttackRoll) {//Checks if hit
		if(AttackRoll>=AC) {
			return true;
		}else {
			return false;
		}
	}

	public void takeDamage(double damage) {//Shows damage
		currentHP-=damage;
		takeDamageAnimation.play();
		if(currentHP<=(MaxHP*.4)) {
			takeDamageAnimation.setCycleCount(Animation.INDEFINITE);
		}else {
			takeDamageAnimation.setCycleCount(4);
		}
	}

	public boolean stillAlive() {//Sees if character alive
		if (currentHP<=0) {
			return false;
		}else {
			return true;
		}
	}

	protected void Heal() {//Heals the character
		if(currentHP<MaxHP) {
			currentHP +=1;
		}
	}

	public void Attack(GameObject target) {//Attacks the target
		Integer Damage = 1;
		try {
			Damage = Random.class.newInstance().nextInt(Attack ) + 1;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		float dmg = Damage.floatValue();
		for(String strength : target.getStrength()) {//Checks if the target has bonus against
			if (strength.equalsIgnoreCase(Weapon)) {
				dmg = Damage/2;
			}
		}
		for(String weakness: target.getWeakness()) {//Checks if the target has bonus for
			if(weakness.equalsIgnoreCase(Weapon)) {
				dmg = Damage*2;
			}
		}
		if(target.Hit(getRoll())) {//Deals damage
			JOptionPane.showMessageDialog(null,String.format("%s hit %s dealing %.2f damage.", getName(),target.getName(),dmg));
			target.takeDamage(dmg);
			if(!target.stillAlive()) {
				target.death();
			}
		}else {
			JOptionPane.showMessageDialog(null,String.format("%s missed %s!", getName(),target.getName()));
		}
	}

	public void death() {} //IMPORTANT NOTE: THIS IS A METHOD MEANT TO BE OVERRODE!

	public int getRoll() {//Calculates a random value between 3 and 18
		Random rng = new Random();

		int v1 = rng.nextInt(6)+1;
		int v2 = rng.nextInt(6)+1;
		int v3 = rng.nextInt(6)+1;
		int v4 = rng.nextInt(6)+1;

		int fv = (v1+v2+v3+v4) - Collections.min(Arrays.asList(v1,v2,v3,v4));
		return fv;
	}

	public String[] getWeakness() {return Weaknesses;}

	public String[] getStrength() {return Strengths;}

}
