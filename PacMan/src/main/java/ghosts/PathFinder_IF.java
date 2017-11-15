package ghosts;

import java.awt.Point;
import java.util.ArrayList;

public interface PathFinder_IF {
	/**
	 * 
	 * @param start Point where to start
	 * @param goal Point for finish
	 * @return returns the path from start to finish
	 */
	public ArrayList<Point> path(Point start, Point goal);
	
	/**
	 * 
	 * @param current Node for current position
	 * @param goal Point for goal
	 * @param start Point for start
	 * @param closedSet ArrayList for nodes that have been visited
	 * @return returns list of neighboring nodes
	 */
	public ArrayList<Node> neighbors(Node current, Point goal, Point start, ArrayList<Node> closedSet);
	
	/**
	 * 
	 * @param list list of open neighboring nodes
	 * @return returns the cheapest node
	 */
	public Node lowestHcost(ArrayList<Node> list);
	
	/**
	 * 
	 * @param current The current node
	 * @return returns the path
	 */
	public ArrayList<Point> retrunPath(Node current);
	
	/**
	 * 
	 * @param goal the finish point
	 * @param point the starting point
	 * @return calculate distance between start and finish
	 */
	public int fromGoalScore(Point goal, Point point);
	
	/**
	 * 
	 * @param parent starting point
	 * @return returns the cost from starting point to parent
	 */
	public int fromStartScore(Node parent);
}
