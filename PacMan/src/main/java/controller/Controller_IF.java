package controller;

import canvas.ComCanvas;
import canvas.Draw_IF;
import ghosts.Ghost;
import map.Map;
import sounds.Sounds;

/**
 * Controllers Interface
 * 
 * @author markus
 *
 */
public interface Controller_IF {

	/**
	 * 
	 * @param scene
	 *            The Main Scene
	 * @param draw
	 *            Canvas Draw
	 * @param ghlist
	 *            the List of Ghosts
	 */
	public void start(Ghost[] ghlist, Map map, ComCanvas cc);

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
	 * @param playername
	 *            the name which is saved to database
	 */
	public void setHighScore(String playername);

	/**
	 * runs Mains GameOver
	 */
	public void gameOver();

}
