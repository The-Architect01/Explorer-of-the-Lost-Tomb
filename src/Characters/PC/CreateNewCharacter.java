package Characters.PC;

import javafx.stage.*;

import javax.swing.JOptionPane;

import GameElements.Game;
import GameElements.SplashScreen;
import GameElements.RoomTileMap;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.geometry.*;

public class CreateNewCharacter extends Stage {

	//Fonts
	static Font customFont28 = Font.loadFont("file:Assets/Regula Old Face.otf",28);
	static Font customFont18 = Font.loadFont("file:Assets/Regula Old Face.otf",18);
	static Font customFont24 = Font.loadFont("file:Assets/Regula Old Face.otf",24);
	static Font customFont20 = Font.loadFont("file:Assets/Regula Old Face.otf",20);

	public CreateNewCharacter() {
		
		Label title = new Label("Create your character!");

		//Name field
		TextField name = new TextField();
		Label NameLabel = new Label("Character Name: ");
		
		HBox nameInfo = new HBox(NameLabel,name);
		nameInfo.setSpacing(5);
		nameInfo.setPadding(new Insets(5,5,5,5));

		//Class Field
		Label classSelect = new Label("Available Classes: ");
		ComboBox<String> ClassSelection = new ComboBox<String>(FXCollections.observableArrayList("Rogue","Warrior","Wizard","Archer"));
		ClassSelection.setStyle("-fx-font: 18px \"Regula Old Face\";");

		HBox classInfo = new HBox(classSelect, ClassSelection);
		classInfo.setSpacing(5);
		classInfo.setPadding(new Insets(5,5,5,5));

		//Character
		ImageView characterPreview = new ImageView();
		characterPreview.setFitHeight(180);
		characterPreview.setFitWidth(180);
		
		//Buttons
		Button confirm = new Button("Confirm");
		confirm.setFont(customFont20);
		confirm.setPrefSize(150, 20);
		
		Button cancel = new Button("Cancel");
		cancel.setFont(customFont20);
		cancel.setPrefSize(150, 20);

		HBox Buttons = new HBox(confirm,cancel);
		Buttons.setSpacing(10);
		Buttons.setPadding(new Insets(5,5,0,5));
		Buttons.setAlignment(Pos.CENTER);

		//Error Message
		Label message = new Label();
		message.setFont(customFont18);
		message.setVisible(false);
		message.setPrefHeight(50);
		
		//Button Actions
		confirm.setOnAction(event->{//Creates a new character
			try {
				if(name.getText().length()<3) {
					message.setText("You haven't entered a valid name!");
					message.setVisible(true);
				}else if(ClassSelection.getValue().isEmpty()) {
					message.setText("You haven't choosen a class yet!");
					message.setVisible(true);
				}else {
					try {
						close();
						JOptionPane.showMessageDialog(null, "Welcome to \"Explorer of the Lost Tomb\"!\n\nTo Play\n"
								+ "- Move: WASD and Arrow Keys\n- Attack: Click on the enemy that you wish to attack.\n"
								+ "  Be aware of attack radius and damage type!\n"
								+ "\nThe end goal, survive as long as you can. Your score is multiplied each time you defeat a boss.");
						new Game(new Character(name.getText().toString(),ClassSelection.getValue().toString()));
						RoomTileMap.launch.class.newInstance().start(Game.floor);
					} catch (Exception e) {
						e.printStackTrace();
					}
					event.consume();
				}	
			}catch(NullPointerException e) {
				message.setText("You haven't choosen a class yet!");
				message.setVisible(true);
			}
		});

		cancel.setOnAction(event->{
			try {
				SplashScreen.Splash.class.newInstance().start(SplashScreen.GameMenu.class.newInstance());
				close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		//Form Actions
		setOnCloseRequest(event->{
			try {
				SplashScreen.Splash.class.newInstance().start(new SplashScreen.GameMenu());
				close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		//Formating
		title.setFont(customFont28);
		NameLabel.setFont(customFont24);
		name.setFont(customFont18);
		classSelect.setFont(customFont24);
		ClassSelection.getEditor().setFont(customFont18);

		Label stats = new Label();
		stats.setFont(customFont18);
		stats.setPrefHeight(50);
		
		VBox characterInfo = new VBox(characterPreview, stats);
		characterInfo.setAlignment(Pos.CENTER);

		//Class Actions
		ClassSelection.setOnAction(event->{
			characterPreview.setImage(new Image("file:Images/" + ClassSelection.getValue() + ".png"));
			stats.setText(Character.getStats(ClassSelection.getValue()));
		});

		VBox Left = new VBox(title,new HBox(NameLabel,name),new HBox(classSelect,ClassSelection),Buttons,message);
		message.setAlignment(Pos.CENTER_LEFT);
		Left.setAlignment(Pos.CENTER_LEFT);
		Left.setPadding(new Insets(10,10,5,10));
		HBox All = new HBox(Left, characterInfo);
		All.setAlignment(Pos.CENTER);
		All.setStyle("-fx-background-color:white");
		All.setPadding(new Insets(5,5,5,5));
		initStyle(StageStyle.UTILITY);
		centerOnScreen();
		setScene(new Scene(All));
		setResizable(false);
		setTitle("Explorer of the Lost Tomb");
	}
	public static class launchScreen extends Application{//Default Launch for all other stages.

		@Override
		public void start(Stage primaryStage) throws Exception {
			primaryStage.setResizable(false);
			primaryStage.show();
			primaryStage.setOnCloseRequest(event->{
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to close the application?",
						"Explorer of the Lost Tomb",JOptionPane.YES_NO_OPTION)==
						JOptionPane.YES_OPTION) {Game.Player.death();}
				else {event.consume();}
			});
		}

	}
}
