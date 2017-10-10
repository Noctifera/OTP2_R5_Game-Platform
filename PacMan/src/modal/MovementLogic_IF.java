package modal;

import java.awt.Point;
import java.util.ArrayList;
/**
 * Both ghost and players logics where it is possible to move
 * @author markus
 * @version 1.0
 *
 */
public interface MovementLogic_IF {
	
	/**
	 * 
	 * @param newpos position you are moving to
	 * @return position if newpos is over map
	 */
	public Point yli(Point newpos); //mitä tapahtuu kun mennään alueen ulkopuolelle
	/**
	 * 
	 * @param point you're position
	 * @return return one tile up
	 */
	public Point up(Point point); //yhden ruudun liikuminen yl�s
	/**
	 * 
	 * @param point you're position
	 * @return one tile up
	 */
	public Point down(Point point); //yhden ruudun liikumen alas
	/**
	 * 
	 * @param point you're position
	 * @return one tile left
	 */
	public Point left(Point point);
	/**
	 * 
	 * @param point you're position
	 * @return one tile right
	 */
	public Point right(Point point);
	/**
	 * 
	 * @param point you're position
	 * @return a list of [up,down,left,right]
	 */
	public Point[] moves(Point point);
	/**
	 * 
	 * @return list of dots on the map
	 */
	public ArrayList<Point> dots();
	/**
	 * 
	 * @return list of large dots on the map
	 */
	public ArrayList<Point> largeDots();
	/**
	 * 
	 * @return list of all walls on the map
	 */
	public ArrayList<Point> walls();
	/**
	 * 
	 * @return list of possible spaces to move to
	 */
	public ArrayList<Point> freespaces();
	/**
	 * 
	 * @return a random point that is possible to move to and on a tile
	 */
	public Point randomPoint();
	
	/**
	 * 
	 * @return ghosthouse from {@link Map}
	 */
	public Point ghostHouse();
	/**
	 * 
	 * @return player spawn point from {@link Map}
	 */
	public Point playerSpawn();
	
	
}
