package pathfinding_PacMan;

import java.awt.Point;
import java.util.ArrayList;

public interface PathFinder_IF {
	/**
	 * 
	 * @param start Point where to start
	 * @param goal Point for finish
	 * @return returns the path from start to finish
	 */
	public ArrayList<Point> route(Point start, Point goal);
}
