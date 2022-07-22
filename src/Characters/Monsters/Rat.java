package Characters.Monsters;

import javafx.scene.image.Image;

public class Rat extends Monster{
	
	static final String[] STRENGTH = {"Bow"};
	static final String[] WEAKNESS = {"Dagger","Magic"};
	
	//Files
	static final Image RAT = new Image("file:Images/Rat.png");
	static final Image RAT_HIT = new Image("file:Images/Rat_Hit.png");

	public Rat() {
		MaxHP = 3;
		currentHP = MaxHP;
		Attack = 1;
		Speed = 2;
		Strengths = STRENGTH;
		Weaknesses = WEAKNESS;
		AGGRESSION = .2f;
		Weapon = "Dagger";
		AC = 9;
		CR = .25f;
		
		HEALPROB = .00f;
		MOVEPROB = .85f;
		ATTACKPROB = .25f;
		WAITPROB = .15f;	
		
		setImage(RAT);
		GameObjectNom = RAT;
		GameObjectHit = RAT_HIT;
		
		setOnMouseClicked(clicked);

	}

}
