package GameElements;
import java.io.File;
import java.util.ConcurrentModificationException;
import java.util.Random;
import javax.swing.JOptionPane;

import Characters.Monsters.Boss;
import Characters.Monsters.Monster;
import Rooms.Room;
import javafx.animation.Animation;
//import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/*
 * RoomTileMap contains all of the graphic and event code required to
 * render the data found in the Room class.
 */
public class RoomTileMap extends Stage {

	//Size
	final static int colNum = 7;
	final static int rowNum = 5;

	//Sounds
	static final Media BACKGROUND = new Media(new File("Music/Exploration.wav").toURI().toString());
	static MediaPlayer BGM = new MediaPlayer(BACKGROUND);
	static final Media FIGHT_MUSIC = new Media(new File("Music/Fight.wav").toURI().toString());

	//IMAGES
	ImageView WallNorth = new ImageView(new Image("File:Images/WallPatch.png"));
	ImageView WallSouth = new ImageView(new Image("File:Images/WallPatch.png"));
	ImageView WallEast = new ImageView(new Image("File:Images/WallPatchR.png"));
	ImageView WallWest = new ImageView(new Image("File:Images/WallPatchL.png"));

	ImageView MagicWallN = new ImageView(new Image("File:Images/Magic Wall.png"));
	ImageView MagicWallS = new ImageView(new Image("File:Images/Magic Wall.png"));
	ImageView MagicWallE = new ImageView(new Image("File:Images/Magic Wall.png"));
	ImageView MagicWallW = new ImageView(new Image("File:Images/Magic Wall.png"));

	//Class Variables
	static Room thisRoom;

	Random rng = new Random();

	static GridPane base;

	public RoomTileMap(Room currentRoom){//Displays the proper room image

		initStyle(StageStyle.UTILITY); //Removes the Min/Max options
		thisRoom = currentRoom;
		BGM.setAutoPlay(true);
		BGM.setCycleCount(Animation.INDEFINITE);//Loops the music
		if(!BGM.getMedia().equals(BACKGROUND)) {BGM.stop(); BGM = new MediaPlayer(BACKGROUND); BGM.play();}

		Image icon = new Image("File:Images/DungeonFull.png");
		getIcons().add(icon);
		base = new GridPane();

		setTitle("Explorer of the Lost Tomb");
		setResizable(false);
		Image image = new Image("File:Images/DungeonFull.png");
		BackgroundImage backImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT , BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background baseBackground = new Background(backImage);		
		base.setBackground(baseBackground);

		base.setPrefSize(625,450);
		base.setMinSize(625, 450);
		base.setMaxSize(625, 450);

		reset();

		/**/
		WallNorth.setFitHeight(84);
		WallNorth.setFitWidth(250);

		WallSouth.setRotate(180);
		WallSouth.setFitHeight(84);
		WallSouth.setFitWidth(250);

		WallEast.setFitHeight(250);
		WallEast.setFitWidth(86);

		WallWest.setFitHeight(250);
		WallWest.setFitWidth(93);
		WallWest.setScaleX(.95);
		WallWest.setTranslateX(1);
		/**/		

		/**/
		MagicWallN.setFitHeight(70);
		MagicWallN.setFitWidth(230);
		MagicWallN.setTranslateX(17.5);
		MagicWallN.setTranslateY(10);

		MagicWallS.setRotate(180);
		MagicWallS.setFitHeight(70);
		MagicWallS.setFitWidth(230);
		MagicWallS.setTranslateX(17.5);
		MagicWallS.setTranslateY(-9);

		MagicWallE.setRotate(90);
		MagicWallE.setFitHeight(68);
		MagicWallE.setFitWidth(230);
		MagicWallE.setTranslateX(-85);
		MagicWallE.setTranslateY(0);

		MagicWallW.setRotate(270);
		MagicWallW.setFitHeight(68);
		MagicWallW.setFitWidth(230);
		MagicWallW.setTranslateX(-55);
		MagicWallW.setTranslateY(0);
		/**/

		base.setAlignment(Pos.BOTTOM_CENTER);

		initializeRoom();

		base.add(Game.Player, Game.Player.getLocationX(), Game.Player.getLocationY());

		base.add(WallNorth, 2, 0, 3, 1);
		base.add(WallWest, 0, 1, 1, 3);
		base.add(WallEast, 6, 1, 1, 3);
		base.add(WallSouth, 2, 4, 3, 1);

		base.add(MagicWallN, 2, 0, 3,1);
		base.add(MagicWallS, 2, 4, 4, 1);
		base.add(MagicWallE, 6, 1, 1, 3);
		base.add(MagicWallW, 0, 1, 1, 3);

		Scene room = new Scene(base,625,450);
		room.setOnKeyPressed(event->{
			KeyCode value = event.getCode();
			try {
				MoveCharacter(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		setScene(room);

		//REDACTED
		/*Timeline monsterTurn = new Timeline(
		new KeyFrame(Duration.seconds(1),
						event->{
							checkMagicRoom();
							checkEncounters();
							System.out.println("Fired");
						}));
		monsterTurn.setCycleCount(Animation.INDEFINITE);
		monsterTurn.play();*/
	}


	@SuppressWarnings(value= {"all"})
	private void MoveCharacter(KeyCode value) throws Exception {//Moves PC
		// TODO Auto-generated method stub
		switch (value) {
		case W:
		case UP://Checks if character can move North
			if(Game.Player.getLocationY() == 0) {goToNewRoom("North");}
			if(Game.Player.getLocationY() == 2 && (Game.Player.getLocationX() == 0 || Game.Player.getLocationX() == 6)) {break;}
			if(Game.Player.getLocationY() == 1 &&  (WallNorth.isVisible()||MagicWallN.isVisible())) {break;}else 
				if(Game.Player.getLocationY() == 1 && Game.Player.getLocationX() != 3){break;}
			if(Game.Player.getLocationY()-1 == -1) {Game.Player.setLocationY(0);}
			if(!Game.Player.checkValid("North")) {break;}
			Game.Player.setLocationY(Game.Player.getLocationY()-1);
			base.setRowIndex(Game.Player, Game.Player.getLocationY());
			Game.Player.setRotate(180);
			break;
		case S:
		case DOWN://Checks if character can move south
			if(Game.Player.getLocationY() == 4) {goToNewRoom("South");}
			if(Game.Player.getLocationY() == 2 && (Game.Player.getLocationX() == 0 || Game.Player.getLocationX() == 6)) {break;}
			if(Game.Player.getLocationY() == 3 && (WallSouth.isVisible()||MagicWallS.isVisible())) {break;}else
				if(Game.Player.getLocationY() == 3 && Game.Player.getLocationX() != 3) {break;}
			if(Game.Player.getLocationY()+1 == 5) {Game.Player.setLocationY(4);}
			if(!Game.Player.checkValid("South")) {break;}
			Game.Player.setLocationY(Game.Player.getLocationY()+1);
			base.setRowIndex(Game.Player, Game.Player.getLocationY());
			Game.Player.setRotate(0);
			break;
		case A:
		case LEFT://Checks if character can move east
			if(Game.Player.getLocationX() == 0) {goToNewRoom("East");}
			if(Game.Player.getLocationX() == 3 && (Game.Player.getLocationY() == 0 || Game.Player.getLocationY() == 4)) {break;}
			if(Game.Player.getLocationX() == 1 && (Game.Player.getLocationY() == 1 || Game.Player.getLocationY() == 3)) {break;}
			if(Game.Player.getLocationX() == 1 && (WallWest.isVisible()||MagicWallW.isVisible())) {break;}
			if(Game.Player.getLocationX()-1 == -1) {Game.Player.setLocationX(0);}
			if(!Game.Player.checkValid("West")) {break;}
			Game.Player.setLocationX(Game.Player.getLocationX()-1);
			base.setColumnIndex(Game.Player, Game.Player.getLocationX());
			Game.Player.setRotate(90);
			break;
		case D:
		case RIGHT://checks if character can move west
			if(Game.Player.getLocationX() == 6) {goToNewRoom("West");}
			if(Game.Player.getLocationX() == 3 && (Game.Player.getLocationY() == 0 || Game.Player.getLocationY() == 4)) {break;}
			if(Game.Player.getLocationX() == 5 && (Game.Player.getLocationY() == 1 || Game.Player.getLocationY() == 3)) {break;}
			if(Game.Player.getLocationX() == 5 && (WallEast.isVisible()||MagicWallE.isVisible())) {break;}
			if(Game.Player.getLocationX()+1 == 7) {Game.Player.setLocationX(6);}
			if(!Game.Player.checkValid("East")) {break;}
			Game.Player.setLocationX(Game.Player.getLocationX()+1);
			base.setColumnIndex(Game.Player, Game.Player.getLocationX());
			Game.Player.setRotate(270);
			break;
		}
		checkMagicRoom();
		checkEncounters();
	}
	@SuppressWarnings(value= {"all"})
	private void checkEncounters() {//Monster Turn
		try {
			for(int i = 0; i<thisRoom.monsters.size();i++) {
				try {
					thisRoom.monsters.get(i).monsterTurn(Game.Player);
				}catch(ConcurrentModificationException e) {
					i--;//If monster killed, update counter
				}
			}
		}catch(NullPointerException e) {
			return;
		}
	}

	@SuppressWarnings(value= {"all"})
	private void initializeRoom() {//Spawns monsters and walls
		if(thisRoom.hasExit("North")) {WallNorth.setVisible(false);}
		if(thisRoom.hasExit("West"))  {WallWest.setVisible(false);}
		if(thisRoom.hasExit("East"))  {WallEast.setVisible(false);}
		if(thisRoom.hasExit("South")) {WallSouth.setVisible(false);}
		if(thisRoom.getHasMonsters()) {//Spawns monsters in random locations
			for(int i = 0; i<thisRoom.monsters.size();i++) {
				if(thisRoom.monsters.get(i).getClass() == Boss.class) {
					int spawnX = rng.nextInt(4)+1;
					int spawnY = rng.nextInt(2)+1;
					base.add(thisRoom.monsters.get(i), spawnX, spawnY,2,2);
				}else {
					boolean valid = true;
					int spawnX = 1;
					int spawnY = 1;
					do {
						spawnX = rng.nextInt(5)+1;
						spawnY = rng.nextInt(3)+1;
						for(Monster spawn:thisRoom.monsters) {
							if(spawn.getLocationX() == spawnX) {
								valid=false;
								continue;
							}else {valid=true;}

							if(spawn.getLocationY() == spawnY) {
								valid=false;
								continue;
							}else {valid=true;}
						}
					}while(!valid);
					base.add(thisRoom.monsters.get(i), spawnX, spawnY);
					thisRoom.monsters.get(i).setLocationX(spawnX);
					thisRoom.monsters.get(i).setLocationY(spawnY);
				}
			}
		}
		checkMagicRoom();
	}

	private void reset() {//Forces each cell to be 90x90
		for (int i =0; i <colNum;i++) {
			ColumnConstraints colConst = new ColumnConstraints();
			colConst.setPercentWidth(100.00/colNum);
			base.getColumnConstraints().add(colConst);
		}
		for (int i =0; i <rowNum;i++) {
			RowConstraints rowConst = new RowConstraints();
			rowConst.setPercentHeight(100.00/rowNum);
			base.getRowConstraints().add(rowConst);
		}
	}

	private void checkMagicRoom(){//Checks if player has to defeat all monsters
		if(thisRoom.getDefeatAllMonsters()) {//Prevent player from leaving room
			if((Game.Player.getLocationX() > 0 && Game.Player.getLocationX() < 6) &&
					(Game.Player.getLocationY() > 0 && Game.Player.getLocationY() < 4)){
				MagicWallN.setVisible(true);
				MagicWallW.setVisible(true);
				MagicWallE.setVisible(true);
				MagicWallS.setVisible(true);

				//Starts bossFight
				if(thisRoom.getType().equalsIgnoreCase("Exit") && !BGM.getMedia().equals(FIGHT_MUSIC)) {
					BGM.stop();
					BGM = new MediaPlayer(FIGHT_MUSIC);
					BGM.setAutoPlay(true);
					BGM.setCycleCount((int) Duration.INDEFINITE.toMillis());
					BGM.play();
				}

				if(WallSouth.isVisible()) {MagicWallS.setVisible(false);}
				if(WallEast.isVisible())  {MagicWallE.setVisible(false);}
				if(WallNorth.isVisible()) {MagicWallN.setVisible(false);}
				if(WallWest.isVisible())  {MagicWallW.setVisible(false);}
			}else {
				MagicWallN.setVisible(false);
				MagicWallW.setVisible(false);
				MagicWallE.setVisible(false);
				MagicWallS.setVisible(false);
			}
		}else {
			MagicWallN.setVisible(false);
			MagicWallW.setVisible(false);
			MagicWallE.setVisible(false);
			MagicWallS.setVisible(false);
		}
	}

	public void goToNewRoom(String direction) throws Exception {//Creates a new room
		BGM.stop();
		this.hide();
		launch.class.newInstance().startNewScene(new RoomTileMap(Game.getNextRoom(direction)), BGM.getCurrentTime());
	}

	//Getters
	public static int getMaxCols() {return colNum;}
	
	public static int getMaxRow() {return rowNum;}

	public static Room getRoom() {return thisRoom;}

	public static GridPane getTileMap() {return base;}

	public static MediaPlayer getMusic() {return BGM;}

	//FOR LAUNCHING NEW SCREENS
	public static class launch extends Application{

		public void startNewScene(RoomTileMap stage, Duration current) throws Exception {
			RoomTileMap.BGM.seek(current);
			start(stage);

		}

		@Override
		public void start(Stage primaryStage) throws Exception {//Default settings
			BGM.play();
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest(event->{
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to close the application?",
						"Explorer of the Lost Tomb",JOptionPane.YES_NO_OPTION)==
						JOptionPane.YES_OPTION) {
					Game.Player.death();
				}else {event.consume();}
			});
			primaryStage.setOnHidden(event->{
				BGM.stop();
			});
			primaryStage.centerOnScreen();
			primaryStage.show();
		}
	}

}