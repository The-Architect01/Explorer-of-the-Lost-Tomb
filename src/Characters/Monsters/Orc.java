package Characters.Monsters;

import javafx.scene.image.Image;

public class Orc extends Monster {

	static final String[] STRENGTH = {"None"};
	static final String[] WEAKNESS = {"Magic"};
	
	//Files
	static final Image ORC = new Image("file:Images/Orc.png");
	static final Image ORC_HIT = new Image("file:Images/Orc_Hit.png");
	
	public Orc() {
		MaxHP = 5;
		currentHP = MaxHP;
		Attack = 2;
		Speed = 1;
		Strengths = STRENGTH;
		Weaknesses = WEAKNESS;
		AGGRESSION = .85f;
		Weapon = "Sword";
		AC = 10;
		CR = 1f;
		
		HEALPROB = .00f;
		MOVEPROB = .9f;
		ATTACKPROB = .35f;
		WAITPROB = .1f;
		
		setImage(ORC);
		GameObjectNom = ORC;
		GameObjectHit = ORC_HIT;

		setOnMouseClicked(clicked);

	}
}
