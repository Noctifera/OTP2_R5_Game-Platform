package map_PacMan;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Gets the Map from file
 * @author markus
 * @version 1.0
 * 
 *
 */
public interface Map_IF {
	/**
	 * 
	 * @return players spawn {@link Point} from map
	 */
	public Point getPlayerSpawn(); //etsitään kartasta playerin spawn piste ja palautetaan se;
	/**
	 * 
	 * @return Ghost House {@link Point}
	 */
	public Point getGhostHouse(); //etsitään kartasta ghost house ja palautetaan sen piste;
	/**
	 * 
	 * @return the Map
	 */
	public HashMap<Point, String> getMap();
	/**
	 * 
	 * @return list of all the walls in the map
	 */
	public ArrayList<Point> getWalls();
	/**
	 * 
	 * @return list all spaces that aren't either a wall or ghost house from the map
	 */
	public ArrayList<Point> freeSpaces();
	/**
	 * gets all the dots from the map and stores them in a list
	 */
	public void setDots();
	/**
	 * 
	 * @return list of dots
	 */
	public ArrayList<Point> getDots();
	/**
	 * gets all large dots from the map and stores them in a list
	 */
	public void setLargeDots();
	/**
	 * 
	 * @return list of large dots
	 */
	public ArrayList<Point> getLargeDots();
	
	
	


}
