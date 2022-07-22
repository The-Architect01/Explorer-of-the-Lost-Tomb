package GameElements;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.control.Label;

public class ScoreBoard extends VBox{

	//Font
	static Font customFont20 = Font.loadFont("file:Assets/Regula Old Face.otf",20);

	//Scores
	static ArrayList<String> scores;

	public ScoreBoard() throws FileNotFoundException  {//Populates the VBox
		scores = loadScores();
		Label TopScore = new Label("1: "+ scores.get(0));
		Label Second = new Label("2: "+ scores.get(1));
		Label Third = new Label("3: "+ scores.get(2));
		Label Fourth = new Label("4: "+ scores.get(3));
		Label Fifth = new Label("5: "+ scores.get(4));
		
		TopScore.setFont(customFont20);
		Second.setFont(customFont20);
		Third.setFont(customFont20);
		Fourth.setFont(customFont20);
		Fifth.setFont(customFont20);
		
		TopScore.setPrefWidth(212);
		Second.setPrefWidth(212);
		Third.setPrefWidth(212);
		Fourth.setPrefWidth(212);
		Fifth.setPrefWidth(212);
		
		getChildren().add(TopScore);
		getChildren().add(Second);
		getChildren().add(Third);
		getChildren().add(Fourth);
		getChildren().add(Fifth);
		setStyle("-fx-background-color:#E7E6E6");
	}
	
	public static ArrayList<String> loadScores() throws FileNotFoundException {//Reads Scores
		File data = new File("Scoreboard.rec");
		Scanner read = new Scanner(data);
		ArrayList<String> score = new ArrayList<String>();
		for(int i = 0; i <5; i++) {
			score.add(read.nextLine());
		}
		read.close();
		return score;
	}
	public static void saveScores(int score) throws IOException {//Updates leader board
		File data = new File("Scoreboard.rec");
		data.setWritable(true);
		for(int i = 0; i<5;i++) {
			int compScore = Integer.parseInt(scores.get(i).split(": ")[1]);
			if(score >compScore) {
				scores.add(i, String.format("%13s: %06d", Game.Player.getCharName(), score));
				break;
			}
		}
		FileWriter x = new FileWriter(data);
		for(int i = 0; i<5;i++) {
			x.write(scores.get(i)+"\n");
		}
		x.close();
	}

}
