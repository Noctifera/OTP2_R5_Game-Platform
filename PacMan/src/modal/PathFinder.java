package modal;

import java.awt.Point;
import java.util.ArrayList;

public class PathFinder {
	
	private FileOut fileOut;
	private MovementLogic ml;

	public PathFinder(FileOut fileOut, MovementLogic ml) {
		super();
		this.fileOut = fileOut;
		this.ml = ml;
	}

	public ArrayList<Point> path(Point start, Point goal) {

		ArrayList<Node> closedSet = new ArrayList<>();
		ArrayList<Node> openSet = new ArrayList<>();

		openSet.add(new Node(start, null, 0, Integer.MAX_VALUE));
		Node current;
		while (!openSet.isEmpty()) {

			current = lowestHcost(openSet);
			closedSet.add(current);
			openSet.remove(current);

			// System.out.println("pos: " + current.getId() + " goal: " + goal);
			if (current.getId().equals(goal)) {
				// path found
				ArrayList<Point> path = retrunPath(current);
				fileOut.GhostPathToFile("path", path);
				return path;
			}
			ArrayList<Node> nei = neighbors(current, goal, start, closedSet);
			fileOut.GhostNodeToFile("nodes", current);
			for (Node n : nei) {

				if (!openSet.contains(n)) {
					// System.out.println("add n: " + n);
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
			// System.out.println("up");

			// System.out.println("fromGoalScore");
			int fromGoalScore = fromGoalScore(goal, upPoint);
			// System.out.println("gScore");
			int fromStartScore = fromStartScore(current);

			Node up = new Node(upPoint, current, fromStartScore, fromGoalScore);
			if (!closedSet.contains(up)) {
				list.add(up);
				// System.out.println("Up: " + up.toString());
			}

		}
		if (set.contains(rightPoint)) {
			// System.out.println("right");

			// System.out.println("fromGoalScore");
			int fromGoalScore = fromGoalScore(goal, rightPoint);
			// System.out.println("gScore");
			int fromStartScore = fromStartScore(current);

			Node right = new Node(rightPoint, current, fromStartScore, fromGoalScore);

			if (!closedSet.contains(right)) {
				list.add(right);
				// System.out.println("Right: " + right.toString());
			}

		}
		if (set.contains(downPoint)) {
			// System.out.println("down");

			// System.out.println("fromGoalScore");
			int fromGoalScore = fromGoalScore(goal, downPoint);
			// System.out.println("gScore");
			int fromStartScore = fromStartScore(current);

			Node down = new Node(downPoint, current, fromStartScore, fromGoalScore);

			if (!closedSet.contains(down)) {
				list.add(down);
				// System.out.println("Down: " + down.toString());
			}

		}
		if (set.contains(leftPoint)) {
			// System.out.println("left");

			// System.out.println("fromGoalScore");
			int fromGoalScore = fromGoalScore(goal, leftPoint);
			// System.out.println("gScore");
			int fromStartScore = fromStartScore(current);

			Node left = new Node(leftPoint, current, fromStartScore, fromGoalScore);

			if (!closedSet.contains(left)) {
				list.add(left);
				// System.out.println("left: " + left.toString());
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
		// System.out.println("path found");
		ArrayList<Point> wrongList = new ArrayList<>();
		ArrayList<Point> path = new ArrayList<>();
		Node api = current;
		while (api != null) {
			wrongList.add(api.getId());
			api = api.getPrev();
		}
		for (int i = wrongList.size() - 1; i >= 0; i--) {
			path.add(wrongList.get(i));
		}
		return path;
	}

	public int fromGoalScore(Point goal, Point point) {
		double dX = Math.abs(goal.getX() - point.getX());
		double dY = Math.abs(goal.getY() - point.getY());
		// System.out.println("dX: " + dX);
		// System.out.println("dY: " + dY);
		int fromGoalScore;
		if (dX > 0 && dY > 0) {
			fromGoalScore = (int) Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
		} else {
			fromGoalScore = (int) (dX + dY);
		}
		return fromGoalScore;
	}
	public int fromStartScore(Node parent) {
		return parent.getCostFromStart()+40;
	}
}
