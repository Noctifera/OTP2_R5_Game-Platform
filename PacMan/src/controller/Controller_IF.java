package controller;

import java.util.ArrayList;

import javafx.scene.Scene;
import modal.Draw;
import modal.Ghost;

/**
 * Controllers Interface
 * @author markus
 *
 */
public interface Controller_IF {
	
	/**
	 * 
	 * @param scene The Main Scene
	 * @param draw Canvas Draw
	 * @param ghlist the List of Ghosts
	 */
	public void start(Scene scene, Draw draw,Ghost[] ghlist);
	/**
	 * 
	 * @param fileName The name for the file where the map is
	 */
	public void getMap(String fileName);
	/**
	 * 
	 */
	public void gethighScore();
	/**
	 * 
	 */
	public void setLives();
	/**
	 * 
	 */
	public void setScore();
	/**
	 * 
	 * @param playername the name which is saved to database
	 */
	public void setHighScore(String playername);
	/**
	 * 
	 * @return ArrayList of Strings of the HighScore DataBases scores
	 */
	public ArrayList<String> presentScore();
	/**
	 * 	
	 * @return ArrayList of Strings from the DataBase of Names
	 */
	public ArrayList<String> presentName();
	/**
	 * 
	 * @return ArrayList of Strings from DataBase of the Date
	 */
	public ArrayList<String> presentDate();
	/**
	 * runs Mains GameOver
	 */
	public void gameOver();
	
}
