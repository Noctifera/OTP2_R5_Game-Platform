package modal;

import java.awt.Point;
import java.util.ArrayList;

public class PathFinder {

	private FileOut fileOut;
	private MovementLogic ml;

	public PathFinder(FileOut fileOut, MovementLogic ml) {
		this.fileOut = fileOut;
		this.ml = ml;
	}

	public ArrayList<Point> path(Point start, Point goal) {

		ArrayList<Node> closedSet = new ArrayList<>();
		ArrayList<Node> openSet = new ArrayList<>();

		Node current = new Node(start, null, 0, Integer.MAX_VALUE);
		openSet.add(current);
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

		for (Point p : ml.moves(current.getId())) {

			if (ml.freespaces().contains(p)) {

				int fromGoalScore = fromGoalScore(goal, p);
				int fromStartScore = fromStartScore(current);
				Node node = new Node(p, current, fromStartScore, fromGoalScore);

				if (!closedSet.contains(node)) {

					list.add(node);

				}

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
		ArrayList<Node> wrongList = new ArrayList<>();
		ArrayList<Point> path = new ArrayList<>();
		Node api = current;
		while (api != null) {
			wrongList.add(api);
			api = api.getPrev();
		}
		for (int i = wrongList.size() - 1; i >= 0; i--) {
			path.add(wrongList.get(i).getId());
		}
		return path;
	}

	public int fromGoalScore(Point goal, Point point) {
		double dX = Math.abs(goal.getX() - point.getX());
		double dY = Math.abs(goal.getY() - point.getY());

		int fromGoalScore;
		if (dX > 0 && dY > 0) {
			fromGoalScore = (int) Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
		} else {
			fromGoalScore = (int) (dX + dY);
		}
		return fromGoalScore;
	}

	public int fromStartScore(Node parent) {
		return parent.getCostFromStart() + 40;
	}
}
