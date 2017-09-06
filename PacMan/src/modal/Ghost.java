package modal;

import java.awt.Point;
import java.lang.invoke.VolatileCallSite;
import java.util.ArrayList;

public class Ghost {
	private MovementLogic ml;
	private Player player;

	private Point pos;
	private Point gSize;
	private int tileSize;
	private ArrayList<Point> polku;

	public Ghost(Point pos, MovementLogic ml, Point gSize, int tileSize, Player player) {
		this.pos = pos;
		this.ml = ml;
		this.gSize = gSize;
		this.tileSize = tileSize;
		this.player = player;
	}

	public Point randomPoint() {
		Point point;
		int randX = (int) (Math.random() * gSize.getX() - tileSize);
		while (randX % tileSize != 0) {
			randX = (int) (Math.random() * gSize.getX() - tileSize);
			if (randX % tileSize == 0) {
				break;
			}
		}
		int randY = (int) (Math.random() * gSize.getY() - tileSize);
		while (randY % tileSize != 0) {
			randY = (int) (Math.random() * gSize.getY() - tileSize);
			if (randY % tileSize == 0) {
				break;
			}
		}
		point = new Point(randX, randY);
		if (ml.posibleMove(point)) {
			return point;
		} else {
			return randomPoint();
		}
		// System.out.println(randX+", "+randY);

	}

	public void path(Point pos, Point target) {
		polku = new ArrayList<Point>();
		double dX = target.getX() - pos.getX();
		double dY = target.getY() - pos.getY();

		System.out.println(polku.size());

	}

	public synchronized void update() {
		double random = Math.random();
		if (random <= 0.9) {
			//path(pos, randomPoint());
			pos = randomPoint();
		} else{
			// path(pos, player.getPos());
			pos = player.getPos();
		}
	}

	public Point position() {
		return pos;
	}

	public Point getPos() {
		return pos;
	}

}
