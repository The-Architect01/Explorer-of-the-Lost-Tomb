package Characters.Monsters;

import javafx.scene.image.Image;

public class Crawling_Hand extends Monster{

	static final String[] STRENGTH = {"Dagger", "Bow", "Magic"};
	static final String[] WEAKNESS = {"Sword"};
		
	//Files
	static final Image CRAWLING_HAND = new Image("file:Images/Hand.png");
	static final Image CRAWLING_HAND_HIT = new Image("file:Images/Hand_Hit.png");
	
	public Crawling_Hand() {
		MaxHP = 5;
		currentHP = MaxHP;
		Attack = 2;
		Speed = 0;
		Strengths = new String[] {"Dagger","Bow","Magic"};
		Weaknesses = new String[] {"Sword"};
		Weapon = "Dagger";
		AGGRESSION = .75f;
		AC = 12;
		CR = .5f;
		
		HEALPROB = .00f;
		MOVEPROB = .95f;
		ATTACKPROB = .15f;
		WAITPROB = .05f;

		setImage(CRAWLING_HAND);
		GameObjectNom = CRAWLING_HAND;
		GameObjectHit = CRAWLING_HAND_HIT;

		setOnMouseClicked(clicked);
	}
}
