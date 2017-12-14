package controller_PacMan;

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
	public void setHighScoreDataBase(String playername);

	/**
	 * runs Mains GameOver
	 */
	public void gameOver();

}
