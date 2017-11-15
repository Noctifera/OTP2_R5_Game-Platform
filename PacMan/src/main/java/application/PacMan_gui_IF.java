package application;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Main Classes interface
 * @author markus
 *
 */
public interface PacMan_gui_IF {
	
	/** 
	 * Gets all HighScores from DataBase and shows them
	 * @return HorizontalBox with highScores from DataBase
	 */
	public HBox bottomDataPane(); //tietokannan ulos tulo
	
	/**
	 * 
	 * @param grid parameter for the GridPane where the text is going to be in
	 * @param list parameter for the list where the data is taken from
	 * @param text the Label text for the data
	 */
	public void listLooper(GridPane grid, ArrayList<String> list, String text); //bottomDataPane käyttämä funktio
	
	/**
	 * 
	 * @return HorizontalBox with the lives and score
	 */
	public HBox topHorizonatalBox(); //elkit ja score 
	
	/**
	 *  combines all to one gridPane and added to the Scene
	 */
	public void combine(); // yhdistetään kaikki näkyviin
	
	/**
	 * Updates TopHorizontal boxes shown lives
	 * @param livs players lives
	 */
	public void setLives(int livs); // päivitetään elkit
	
	/**
	 * updates TopHorizontal boxes shown score
	 * @param score players score
	 */
	public void setScore(int score); // päivitetän pisteet
	
	/**
	 * Gives the player to add score to HighScore Database
	 * @return a small pop-up stage
	 */
	public Stage popUpGameOver();
	
	/**
	 * 
	 *  shows popUPGameOver();
	 */
	public void gameOver();
	
	
	

}
