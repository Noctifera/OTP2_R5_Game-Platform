package modal;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Ghost extends PathFinder implements Ghost_IF {
	private MovementLogic ml;
	private Player player;
	private FileIn fileIn;

	private Point pos;
	private String ghost;

	public Ghost(MovementLogic ml, Player player, FileOut fileOut, FileIn fileIn, String ghost) {
		super(fileOut, ml);
		this.player = player;
		this.fileIn = fileIn;
		this.ghost = ghost;
		this.ml = ml;
	}

	public ArrayList<Point> insPath() {

		double rand = Math.random();

		if (rand <= 0.30) {
			Point randpoint = ml.randomPoint();
			if (pathFromFile(randpoint) != null) {
				return pathFromFile(randpoint);
			} else {
				return path(pos, randpoint);
			}
		} else {

			Point point = player.getPos();
			if (pathFromFile(point) != null) {
				return pathFromFile(point);
			} else {
				return path(pos, point);
			}

		}

	}
	
	

	public ArrayList<Point> pathFromFile(Point target) {
		ArrayList<ArrayList<Point>> paths = new ArrayList<>();
		try {
			paths = fileIn.ghostPathFromFile("path");

			for (ArrayList<Point> p : paths) {

				if (p.get(0).equals(pos) && p.get(p.size() - 1).equals(target)) {
					return p;
				}
			}
			// System.out.println("All paths: "+paths);
		} catch (NullPointerException e) {
			e.printStackTrace();

		}
		return null;
	}

	public Point getPos() {
		return pos;
	}

	public Color getColor() {
		if (ghost == "Blinky" && player.getVulnerable().equals("deactive")) {
			return Color.RED;
		} else if (ghost == "Speedy" && player.getVulnerable().equals("deactive")) {
			return Color.PINK;
		} else if (ghost == "Bashful" && player.getVulnerable().equals("deactive")) {
			return Color.AQUAMARINE;
		} else if(ghost.equals("Pokey") && player.getVulnerable().equals("deactive")) {
			return Color.GOLD;
		}else {
			return Color.BLUEVIOLET;
		}
	}

	public String getGhost() {
		return ghost;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public Point ghostHouse() {
		return ml.ghostHouse();
	}
}