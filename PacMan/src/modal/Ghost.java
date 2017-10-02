package modal;

import java.awt.Point;
import java.util.ArrayList;

public class Ghost implements Ghost_IF {
	private MovementLogic ml;
	private Player player;
	private FileOut fileOut;
	private FileIn fileIn;

	private Point pos;

	// private String vulnerable;

	public Ghost(MovementLogic ml, Player player,FileOut fileOut,FileIn fileIn) {
		this.ml = ml;
		this.player = player;
		this.fileOut = fileOut;
		this.fileIn = fileIn;
	}
	

	
	public ArrayList<Point> path(Point start, Point goal) {

		ArrayList<Node> closedSet = new ArrayList<>();
		ArrayList<Node> openSet = new ArrayList<>();

		openSet.add(new Node(start,null, 0, Integer.MAX_VALUE));
		Node current;
		while (!openSet.isEmpty()) {
			
			
			current = lowestHcost(openSet);
			closedSet.add(current);
			openSet.remove(current);

			//System.out.println("pos: " + current.getId() + " goal: " + goal);
			if (current.getId().equals(goal)) {
				// path found
				ArrayList<Point >path = retrunPath(current);
				fileOut.GhostPathToFile("path", path);
				return path;
			}
			ArrayList<Node> nei = neighbors(current, goal, start, closedSet);
			current.setNeighbors(nei);
			for (Node n : nei) {
				//.out.println("all n: " + n);
				if (!openSet.contains(n)) {
					//System.out.println("add n: " + n);
					openSet.add(n);
				}
			}
			

		}
		return null; // no path

	}

	public ArrayList<Node> neighbors(Node current, Point goal, Point start, ArrayList<Node> closedSet) {
		ArrayList<Node> list = new ArrayList<>();
		ArrayList<Point> set = ml.freespaces();

		Point upPoint = ml.up(current.getId());
		Point rightPoint = ml.right(current.getId());
		Point downPoint = ml.down(current.getId());
		Point leftPoint = ml.left(current.getId());

		if (set.contains(upPoint)) {
			//System.out.println("up");

			//System.out.println("fScore");
			int fScore = fScore(goal, upPoint);
			//System.out.println("gScore");
			int gScore = fScore(start, upPoint);

			Node up = new Node(upPoint, current,gScore,fScore);
			if (!closedSet.contains(up)) {
				list.add(up);
				//System.out.println("Up: " + up.toString());
			}

		}
		if (set.contains(rightPoint)) {
			//System.out.println("right");

			//System.out.println("fScore");
			int fScore = fScore(goal, rightPoint);
			//System.out.println("gScore");
			int gScore = fScore(start, rightPoint);

			Node right = new Node(rightPoint,current, gScore, fScore);

			if (!closedSet.contains(right)) {
				list.add(right);
				//System.out.println("Right: " + right.toString());
			}

		}
		if (set.contains(downPoint)) {
			//System.out.println("down");

			//System.out.println("fScore");
			int fScore = fScore(goal, downPoint);
			//System.out.println("gScore");
			int gScore = fScore(start, downPoint);

			Node down = new Node(downPoint,current, gScore, fScore);

			if (!closedSet.contains(down)) {
				list.add(down);
				//System.out.println("Down: " + down.toString());
			}

		}
		if (set.contains(leftPoint)) {
			//System.out.println("left");

			//System.out.println("fScore");
			int fScore = fScore(goal, leftPoint);
			//System.out.println("gScore");
			int gScore = fScore(start, leftPoint);

			Node left = new Node(leftPoint,current, gScore, fScore);

			if (!closedSet.contains(left)) {
				list.add(left);
				//System.out.println("left: " + left.toString());
			}

		}
		return list;
	}

	public Node lowestHcost(ArrayList<Node> list) {
		Node cheapest = list.get(0);
		for (Node n : list) {

			if (n.getCombinedCost() < cheapest.getCombinedCost()) {
				cheapest = n;
			}
		}
		return cheapest;
	}

	public ArrayList<Point> retrunPath(Node current) {
		//System.out.println("path found");
		ArrayList<Point> wrongList = new ArrayList<>();
		ArrayList<Point> path = new ArrayList<>();
		Node api = current;
    	while(api != null){
    		wrongList.add(api.getId());
    		api = api.getPrev();
    	}
    	for(int i = wrongList.size()-1; i>=0;i--) {
    		path.add(wrongList.get(i));
    	}
		return path;
	}

	public int fScore(Point goal, Point point) {
		double dX = Math.abs(goal.getX() - point.getX());
		double dY = Math.abs(goal.getY() - point.getY());
	//	System.out.println("dX: " + dX);
		//System.out.println("dY: " + dY);
		int fScore;
		if (dX > 0 && dY > 0) {
			fScore = (int) Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
		} else {
			fScore = (int) (dX + dY);
		}
		return fScore;
	}

	public ArrayList<Point> insPath() {
		fileIn.ghostPathFromFile("path");

		double rand = Math.random();
		ArrayList<Point> list = new ArrayList<>();
		list.add(pos);

		if (rand <= 0.9) {
			
			Point randpoint = ml.randomPoint();
			return path(pos, randpoint);

		} else {
			Point point = player.getPos();
			return path(pos, point);

		}

	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public Point ghostHouse() {
		return ml.ghostHouse();
	}
}