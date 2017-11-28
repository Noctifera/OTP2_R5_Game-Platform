package controller;

/**
 * Controllers Interface
 * 
 * @author markus
 *
 */
public interface Controller_IF {

	/**
	 * 
	 */
	public void setTopData();

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
