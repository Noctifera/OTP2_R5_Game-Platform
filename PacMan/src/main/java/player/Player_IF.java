package player;

import java.awt.Point;
/**
 * Players functions
 * @author markus
 * @version 1.0
 * 
 *
 */
public interface Player_IF {
	/**
	 * 
	 * @return Players position
	 */
	public Point getPos(); // paluttaa pleyer:in posen;
	/**
	 * 
	 * @param pos position set for player
	 */
	public void setPos(Point pos); // asetetaan playerin pose;
/**
 *  check what's in the position being moved if dot get dot score if large dot large dot score and set vulnerable status active
 * @param pos position moving to
 */
	public void score(Point pos); // katotaan mitä pisteessä on ja paljonko pisteitä siitä saadaan palautetaan yhteis arvo
/**
 * if player is eaten life lost
 */
	public void getEaten(); // menetetään elämiä kun haamu syö
/**
 * 
 * @return how many lives left
 */
	public int getLife(); // palautetaan jäljellä olevia elämiä



}
