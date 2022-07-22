package Characters.PC;

import java.io.File;
import java.io.IOException;
import java.time.*;
import java.time.temporal.ChronoUnit;

import GameElements.Game;
import GameElements.GameObject;
import GameElements.ScoreBoard;
import GameElements.SplashScreen;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Character extends GameObject{
	
	private String className;
	private String name;
	private Instant startTime;
	private Instant endTime;
	final int charHP = 15;
	final int charAC = 12;
	
	//Files
	private final static Image WARRIOR = new Image("file:Images/Warrior.png");
	private final static Image WARRIOR_HIT = new Image("file:Images/Warrior_Hit.png");
	
	private final static Image WIZARD = new Image("file:Images/Wizard.png");
	private final static Image WIZARD_HIT = new Image("file:Images/Wizard_Hit.png");
	
	private final static Image ARCHER = new Image("file:Images/Archer.png");
	private final static Image ARCHER_HIT = new Image("file:Images/Archer_Hit.png");
	
	private final static Image ROGUE = new Image("file:Images/Rogue.png");
	private final static Image ROGUE_HIT = new Image("file:Images/Rogue_Hit.png");

	static final Media PLAYER_DEATH = new Media(new File("Music/PlayerDeath.wav").toURI().toString());
	
	public Character(String name, String className) {
		this.className = className;
		this.name = name;
		MaxHP = charHP;
		AC = charAC;
		Attack = 5;
		currentHP = charHP;
		Strengths = new String[] {"None"};
		Weaknesses = new String[] {"None"};
		switch (className.toLowerCase()) {
		case "warrior": //Makes a warrior
			Weapon = "Sword";
			setImage(WARRIOR);
			GameObjectNom = WARRIOR;
			GameObjectHit = WARRIOR_HIT;
			Strengths = new String[] {"Sword","Dagger"};
			Weaknesses = new String[] {"Magic"};
			break;
		case "wizard": //Makes a Wizard
			Weapon = "Magic";
			setImage(WIZARD);
			GameObjectNom = WIZARD;
			GameObjectHit = WIZARD_HIT;
			Strengths = new String[] {"Magic"};
			Weaknesses = new String[] {"Sword"};
			break;
		case "rogue": //Makes a Rogue
			Weapon = "Dagger";
			setImage(ROGUE);
			GameObjectNom = ROGUE;
			GameObjectHit = ROGUE_HIT;
			Strengths = new String[] {"Bow","Dagger"};
			Weaknesses = new String[] {"Magic"};
			break;
		case "archer": //Makes an Archer
			Weapon = "Bow";
			setImage(ARCHER);
			GameObjectNom = ARCHER;
			GameObjectHit = ARCHER_HIT;
			Strengths = new String[] {"Bow"};
			Weaknesses = new String[] {"Sword"};
			break;
		}
		startTime = Instant.now();//Starts timer
		LocationX = 3;
		LocationY = 2;
	}
	
	public static String getStats(String className) { //Gets the standard info for each class
		switch(className.toLowerCase()) {
		case "warrior":
			return ("Weapon: Sword\nAttack Radius: 1 unit");
		case "wizard":
			return ("Weapon: Magic\nAttack Radius: 1 line");
		case "rogue":
			return ("Weapon: Dagger\nAttack Radius: 1 unit");
		case "archer":
			return ("Weapon: Bow\nAttack Radius: 1 line");
		}
		return("");
	}
	
	public Object getCharacterDetail(String detail) {
		switch (detail.toLowerCase()) {
		case "weapon":
			return Weapon;
		case "class":
			return className;
		case "name":
			return name;
		case "hp":
			return currentHP;
		case "time survived":
			endTime = Instant.now();
			return ChronoUnit.SECONDS.between(startTime, endTime);
		}
		return "Detail not found";
	}
	
	public String getCharName() {
		return name;
	}

	@Override
	public void death() {//Starts the game over
		MediaPlayer die = new MediaPlayer(PLAYER_DEATH);
		die.play();
		try {
			Long timeSurvived = (long)getCharacterDetail("Time Survived");
			ScoreBoard.saveScores((int)(timeSurvived.doubleValue() * Game.floorPass));
			try {
				this.getScene().getWindow().hide();
				SplashScreen.Splash.class.newInstance().start(new SplashScreen.GameMenu());
			}catch(Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			Game.reset();
		}
	}
	
	public String getWeapon() {
		return Weapon;
	}
	
}
