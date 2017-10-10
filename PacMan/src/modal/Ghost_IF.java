package modal;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.paint.Color;

/**
 * Functions for the ghost
 * 
 * @author markus
 * @version 1.0
 *
 */
public interface Ghost_IF {
	/**
	 * chooses either go to player or a random point
	 * 
	 * @return ArrayList of the path {@link Point}
	 */
	public ArrayList<Point> insPath(); // palauttaa polun jota liikutaan

	/**
	 * @return ghosts position
	 */

	public Point getPos(); // palauttaa haamun sen hetkisen sijainnin

	/**
	 * @return Ghosts Color according to name and vulnerable status
	 */
	public Color getColor(); // palautetaan javafx v‰ri haamun nimen perusteella

	/**
	 * @return the ghosts name
	 */
	public String getGhost(); // palauttaa hamun nimen

	/**
	 * 
	 * @param pos sets the ghosts position
	 */
	public void setPos(Point pos); // s‰ikeell‰ p‰ivitet‰‰n haamun sijaintia

	/**
	 * @return ghosthouse
	 */
	public Point ghostHouse(); // palautetaan Ghosthousin sijainti;

	/**
	 * @return vulnerable status
	 */
	public String vulnerableStatus();

	/**
	 * If the path is in the file it returns the that path
	 * 
	 * @param target Point where we want to go
	 * @return ArrayList of the path
	 */
	public ArrayList<Point> pathFromFile(Point target);

}
