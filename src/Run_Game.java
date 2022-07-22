import GameElements.SplashScreen;
import javafx.application.*;
import javafx.stage.*;

public class Run_Game{
	
	public static void main(String[] args) throws Exception {
		Splash.showSplash(args); //Starts Game
	}
	
	public static class Splash extends Application{

		public static void showSplash(String[] args) throws Exception {
			launch(args);
		}
		public void start(Stage primaryStage) throws Exception {
			primaryStage = new SplashScreen();
			primaryStage.show();
		}
	}
}



//For image, must have "File:" proceeding.
//Media + media player
/*
 * Notes: Move; TYPE: Left/Right/Down/Up Attack: TYPE: Attack (direction) Help:
 * Type HELP Pick Item: Intersect with item.
 */
/*
 * <NOTES> Treat the screen as an array Screen class? See:
 * http://staff.washington.edu/gmobus/Academics/TCSS372/Project/Console.java
 * SUMMARY:: Screen as array, Cursor location is a loop that loops through array
 * Possibly (to get rid of cursor) have a 3 input method (value, x, y) that
 * places a character at the xy coordinates. Then reprint the array using/r OR
 * reprint screen by creating a bogus array of the same size, print, then
 * reprint array? </NOTES>
 */