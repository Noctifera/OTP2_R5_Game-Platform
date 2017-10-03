package modal;

import java.awt.Point;
import java.util.ArrayList;

import javax.management.remote.rmi.RMIIIOPServerImpl;

import javafx.scene.paint.Color;

public class Ghost extends PathFinder implements Ghost_IF {
	private MovementLogic ml;
	private Player player;
	private FileIn fileIn;

	private Point pos;
	private String ghost;
	// private String vulnerable;

	public Ghost(MovementLogic ml, Player player, FileOut fileOut, FileIn fileIn, String ghost) {
		super(fileOut,ml);
		this.player = player;
		this.fileIn = fileIn;
		this.ghost = ghost;
		this.ml = ml;
	}
	public ArrayList<Point> moveRandom(){
		Point randpoint = ml.randomPoint();
		return path(pos,randpoint);
	}
	public ArrayList<Point> moveToPlayer(){
		Point point = player.getPos();
		return path(pos, point);
	}

	public ArrayList<Point> insPath() {
		ArrayList<ArrayList<Point>> paths = new ArrayList<>();
		try {
			paths = fileIn.ghostPathFromFile("path");
			System.out.println("All paths: "+paths);
		} catch (NullPointerException e) {
			e.printStackTrace();
			return moveRandom();
		}
		double rand = Math.random();

		if (rand <= 0.49) {
			return moveRandom();
		}else {
			return moveToPlayer();
		}
		
	}

	public Point getPos() {
		return pos;
	}
	public Color getColor() {
		if(ghost == "Blinky") {
			return Color.RED;
		}else if(ghost == "Speedy") {
			return Color.PINK;
		}else if(ghost == "Bashful") {
			return Color.AQUAMARINE;
		}else {
			return Color.GOLD;
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