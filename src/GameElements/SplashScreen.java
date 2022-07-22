package GameElements;

import javax.swing.JOptionPane;

import Characters.PC.CreateNewCharacter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class SplashScreen extends Stage {
	
	//Fonts
	static Font customFont32 = Font.loadFont("file:Assets/Regula Old Face.otf",32);
	static Font customFont28 = Font.loadFont("file:Assets/Regula Old Face.otf",28);

	public SplashScreen() {//Makes a Splash

		ImageView Logo = new ImageView(new Image("file:Images/g.png"));
		Logo.setFitHeight(175);
		Logo.setPreserveRatio(true);

		Label presents = new Label("Presents");
		Label title = new Label("Explorer of the Lost Tomb");

		title.setFont(customFont32);		
		presents.setFont(customFont28);

		VBox arrangment = new VBox(Logo, presents, title);
		arrangment.setSpacing(10);
		arrangment.setPadding(new Insets(10,10,10,10));
		arrangment.setAlignment(Pos.CENTER);
		arrangment.setStyle("-fx-background-color: white");

		Image icon = new Image("File:Images/DungeonFull.png");
		getIcons().add(icon);
		
		Timeline splashTimer = new Timeline(//Shows Splash for 5 seconds
				new KeyFrame(Duration.seconds(5),
						event->{
							try {
								Splash.class.newInstance().start(new GameMenu());
							} catch (Exception e) {
								e.printStackTrace();
							}
							this.close();
						}));

		splashTimer.play();
		setResizable(false);
		setScene(new Scene(arrangment,500,300));
		initStyle(StageStyle.UNDECORATED);
		centerOnScreen();
	}
	public static class Splash extends Application{//Makes a new Screen
		
		public void showSplash(SplashScreen stage) throws Exception {
			start(stage);
		}

		public void start(Stage primaryStage) throws Exception {
			primaryStage.centerOnScreen();
			primaryStage.show();
		}
	}
	public static class GameMenu extends Stage {//Makes a new Game Menu

		public static ScoreBoard ScoreBoard = null;

		//Sets Button colors
		Image buttonClick = new Image("File:Images/ButtonClick.png");
		BackgroundImage backImageButtonClick = new BackgroundImage(buttonClick, BackgroundRepeat.NO_REPEAT , BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background backButtonClick = new Background(backImageButtonClick);	

		Image buttonClicked = new Image("File:Images/ButtonDisabled.png");
		BackgroundImage backImageButtonClicked = new BackgroundImage(buttonClicked, BackgroundRepeat.NO_REPEAT , BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background backButtonClicked = new Background(backImageButtonClicked);	

		public GameMenu() {
			VBox arrangment = new VBox();

			Label title = new Label("Explorer of the Lost Tomb");
			title.setFont(customFont32);
			title.setStyle("-fx-background-color:#E7E6E6");

			Button start =  new Button("Start");
			start.setFont(customFont28);
			start.setOnMouseEntered(event->{
				start.setBackground(backButtonClick);
			});

			start.setOnAction(event->{//Creates a new screen for character creation
				start.setBackground(backButtonClicked);
				try {
					Splash.class.newInstance().start(new CreateNewCharacter());
					this.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			start.setPrefSize(150, 20);

			Button options = new Button("Options");
			options.setFont(customFont28);
			options.setPrefSize(150, 20);
			options.setOnMouseEntered(event->{//Changes color
				options.setBackground(backButtonClick);
			});
			options.setDisable(true);
/*INVALID	options.setOnAction(event->{
				options.setBackground(backButtonClicked);
				try {
					Splash.class.newInstance().start(new Options());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
*/			
			setOnCloseRequest(event->{//Closes Game
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to close the application?",
						"Explorer of the Lost Tomb",JOptionPane.YES_NO_OPTION)==
						JOptionPane.YES_OPTION) {System.exit(0);}
				else {event.consume();}
			});

			VBox GameOptions = new VBox(start,options);
			GameOptions.setSpacing(20);

			try {//Retrieves data
				ScoreBoard = new ScoreBoard();
				ScoreBoard.setAlignment(Pos.BASELINE_LEFT);
			} catch (Exception e) {
				e.printStackTrace();
			}

			//General Display
			HBox CenterDisplay = new HBox(GameOptions, ScoreBoard);
			CenterDisplay.setSpacing(50);
			CenterDisplay.setAlignment(Pos.CENTER);

			Image image = new Image("File:Images/DungeonFull.png");
			BackgroundImage backImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT , BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
			Background baseBackground = new Background(backImage);		
			arrangment.setBackground(baseBackground);

			Image imageButton = new Image("File:Images/Button.png");
			BackgroundImage backImageButton = new BackgroundImage(imageButton, BackgroundRepeat.NO_REPEAT , BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
			Background ButtonBackground = new Background(backImageButton);		
			options.setBackground(ButtonBackground);
			start.setBackground(ButtonBackground);

			start.setOnMouseExited(event->{//Changes Image
				start.setBackground(ButtonBackground);
			});
			options.setOnMouseExited(event->{//Changes Image
				options.setBackground(ButtonBackground);
			});

			arrangment.getChildren().add(title);
			arrangment.getChildren().add(CenterDisplay);
			arrangment.setAlignment(Pos.CENTER);
			arrangment.setSpacing(15);

			initStyle(StageStyle.UTILITY);
			centerOnScreen();
			setScene(new Scene(arrangment,625,450));

			setResizable(false);
			setTitle("Explore of the Lost Tomb");
		}

	}
}

