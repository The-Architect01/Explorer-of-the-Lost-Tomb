package Characters.Monsters;

import javafx.scene.image.Image;

public class Stirge extends Monster{
	
	static final String[] STRENGTH = {"Dagger"};
	static final String[] WEAKNESS = {"Bow","Magic"};
	
	//Files
	static final Image STIRGE = new Image("file:Images/Stirge.png");
	static final Image STIRGE_HIT = new Image("file:Images/Stirge_Hit.png");
	
	public Stirge() {
		MaxHP = 5;
		currentHP = MaxHP;
		Attack = 1;
		Speed = 3;
		Strengths = STRENGTH;
		Weaknesses = WEAKNESS;
		AGGRESSION = .3f;
		Weapon = "Bow";
		AC = 5;
		CR = .25f;
		
		HEALPROB = .00f;
		MOVEPROB = .9f;
		ATTACKPROB = .15f;
		WAITPROB = .1f;	
		
		setImage(STIRGE);
		GameObjectNom = STIRGE;
		GameObjectHit = STIRGE_HIT;
		
		setOnMouseClicked(clicked);

	}

}
