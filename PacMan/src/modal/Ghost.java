package modal;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Ghost extends PathFinder implements Ghost_IF {
	private MovementLogic ml;
	private Player player;
	private FileIn fileIn;

	private Node pos;
	private String ghost;
	// private String vulnerable;

	public Ghost(MovementLogic ml, Player player, FileOut fileOut, FileIn fileIn, String ghost) {
		super(fileOut, ml);
		this.player = player;
		this.fileIn = fileIn;
		this.ghost = ghost;
		this.ml = ml;
	}


	public ArrayList<Node> insPath() {
		
		double rand = Math.random();

		if (rand <= 0.1) {
			Node randpoint = new Node(ml.randomPoint());
			if (pathFromFile(randpoint) != null) {
				return pathFromFile(randpoint);
			} else {
				return path(pos, randpoint);
			}
		} else {
			
			Node point = new Node(player.getPos());
			if (pathFromFile(point) != null) {
				return pathFromFile(point);
			} else {
				return path(pos, point);
			}

		}

	}
	public Node moveOne(Node goal) {
		ArrayList<Node>list = new ArrayList<>();
		
		for(Point p: ml.moves(pos.getId())) {
			if(ml.freespaces().contains(p)) {
				Node node = new Node(p,new Node(pos.getId()),fromStartScore(new Node(ml.ghostHouse())),fromGoalScore(goal, p));
				list.add(node);
			}
		}
		return lowestHcost(list);
	}

	public ArrayList<Node> pathFromFile(Node target) {
		ArrayList<ArrayList<Node>> paths = new ArrayList<>();
		try {
			paths = fileIn.ghostPathFromFile("path");

			for (ArrayList<Node> p : paths) {
				
				if (p.get(0).getId().equals(pos.getId()) && p.get(p.size() - 1).getId().equals(target.getId())) {
					return p;
				}
			}
			// System.out.println("All paths: "+paths);
		} catch (NullPointerException e) {
			e.printStackTrace();

		}
		return null;
	}

	public Node getPos() {
		return pos;
	}

	public Color getColor() {
		if (ghost == "Blinky") {
			return Color.RED;
		} else if (ghost == "Speedy") {
			return Color.PINK;
		} else if (ghost == "Bashful") {
			return Color.AQUAMARINE;
		} else {
			return Color.GOLD;
		}
	}

	public String getGhost() {
		return ghost;
	}

	public void setPos(Node pos) {
		this.pos = pos;
	}

	public Point ghostHouse() {
		return ml.ghostHouse();
	}
}