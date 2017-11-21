package application;

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
