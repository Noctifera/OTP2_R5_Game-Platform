package modal;

import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;
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
 * 
 * @param event button pressed "w" for up, "a" for left, "s" for down, "d" for right
 * @return list of point possible to move the the given direction
 */
	public ArrayList<Point> move(KeyCode event); // "WASD" napeilla liikuminen palauttaa arrayListin ruudist johon voidaan liikua siihen suuntaan
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
