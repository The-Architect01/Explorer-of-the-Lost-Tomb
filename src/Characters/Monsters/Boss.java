package Characters.Monsters;

import java.io.File;

import javax.swing.JOptionPane;

import GameElements.Game;
import GameElements.GameObject;
import GameElements.RoomTileMap;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Boss extends Monster {

	static final String[] STRENGTH = {"None"};
	static final String[] WEAKNESS = {"None"};

	//files
	static final Media PLAYER_WIN = new Media(new File("Music/VillianDeath.wav").toURI().toString());
	static final Image BOSS = new Image("file:Images/Final Boss Down 1.png");
	static final Image BOSS_HIT = new Image("file:Images/Boss_Hit.png");

	public Boss() {
		MaxHP = Game.floorPass * 5;
		currentHP = MaxHP;
		Attack= 4;
		Speed = 1;
		Strengths = STRENGTH;
		Weaknesses = WEAKNESS;
		Weapon = "Magic";
		AGGRESSION = 1f;
		AC = 15;

		HEALPROB = .00f;
		MOVEPROB = .99f;
		ATTACKPROB = .5f;
		WAITPROB = .01f;

		setImage(BOSS);
		GameObjectNom = BOSS;
		GameObjectHit = BOSS_HIT;
		setFitHeight(180);
		setFitWidth(180);

		setOnMouseClicked(clicked);
	}

	@Override
	public void death() {
		RoomTileMap.getMusic().stop();
		MediaPlayer win = new MediaPlayer(PLAYER_WIN);
		win.play();
		System.out.println(this.getName());
		GridPane Parent = (GridPane) getParent();
		Parent.getChildren().remove(this);
		Parent.getScene().getWindow().hide();
		RoomTileMap.getRoom().monsters.remove(this);
		JOptionPane.showMessageDialog(null, "The boss ran to the next floor! Find them!");
		try {
			new Game(); //Regenerates Map
			RoomTileMap.launch.class.newInstance().start(Game.floor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean areNeighbors(GameObject obj1, GameObject obj2){//Since the boss is twice as big, makes sure the boss can hit everything it can
		if ((((obj1.getLocationX()+2 == obj2.getLocationX()||obj1.getLocationX()+1 == obj2.getLocationX())
				|| obj1.getLocationX()-1 == obj2.getLocationX()) &&
				(obj1.getLocationY() == obj2.getLocationY()||obj1.getLocationY()+1 == obj2.getLocationY()))|| // Checks if obj2 is to the left or right of obj1
				(((obj1.getLocationY()+2 == obj2.getLocationY() || obj1.getLocationY()+1 == obj2.getLocationY())
						|| obj1.getLocationY()-1 == obj2.getLocationY())&&
						(obj1.getLocationX() == obj2.getLocationX()||obj1.getLocationX()+1 == obj2.getLocationX()))) { //Checks if obj2 is above or below obj1
			return true;
		}
		return false;
	}

	@Override
	public void Move(String Direction) {//Since the boss is twice as big, makes sure the boss doesn't go into walls
		switch(Direction.toLowerCase()) {
		case "north":
			if(!checkValid("North")) {return;}
			if(LocationY - 1 == 0) {LocationY = 1;} else {LocationY-=1;}
			setRotate(180);
			break;
		case "south":
			if(!checkValid("South")) {return;}
			if(LocationY + 1 == 3) {LocationY = 2;} else {LocationY+=1;}
			setRotate(0);
			break;
		case "east":
			if(!checkValid("East")) {return;}
			if(LocationX + 1 == 5) {LocationX = 4;} else {LocationX +=1;}
			setRotate(270);
			break;
		case "west":
			if(!checkValid("West")) {return;}
			if(LocationX - 1 == 0) {LocationY = 1;} else {LocationX-=1;}
			setRotate(90);
			break;
		}
	}
}
