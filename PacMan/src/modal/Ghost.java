package modal;

import java.awt.Point;
import java.util.ArrayList;

public class Ghost {
	private MovementLogic ml;
	private Player player;

	private Point[] polku = new Point[5];
	private Point pos;
	private Point gSize;
	private int tileSize;
	private int size;

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

	public int left(Point pos) {
		// minus tile width
		Point newpoint = ml.yli(pos);
		if (ml.posibleMove(pos)) {
			return (int) newpoint.getX() - tileSize;
		} else {
			return up(pos);
		}
	}

	public int up(Point pos) {
		// minus tile height
		Point newpoint = ml.yli(pos);
		if (ml.posibleMove(pos)) {
			return (int) newpoint.getY() - tileSize;
		} else {
			return right(pos);
		}
	}

	public int right(Point pos) {
		// add tile width
		Point newpoint = ml.yli(pos);
		return (int) newpoint.getX() + tileSize;
	}

	public int down(Point pos) {
		// add tile height
		Point newpoint = ml.yli(pos);
		return (int) newpoint.getY() + tileSize;
	}

	public Point[] path(Point pos, Point target) {
		double dX = target.getX() - pos.getX();
		// System.out.println(dX);
		double dY = target.getY() - pos.getY();
		// System.out.println(dY);
		int posY = (int) pos.getY();
		int posX = (int) pos.getX();
		size = 1;
		for (int i = 0; i < polku.length; i++) {
			if (dX < 0) {
				// left
				posX = left(new Point(posX, (int) pos.getY()));

			} else if (dX > 0) {
				// right
				posX = right(new Point(posX, (int) pos.getY()));
			}
			if (dY < 0) {
				// up
				posY = up(new Point((int) pos.getY(), posY));
			} else if (dY > 0) {
				// down
				posY = down(new Point((int) pos.getY(), posY));
			}
			Point apu = ml.yli(new Point(posX, posY));
			polku[i] = new Point(apu);
		}
		for (Point p : polku) {
			System.out.println(p);
		}
		System.out.println("");

		return polku;
	}

	public synchronized void update() {
		if (polku[0] != null && size < polku.length) {
			pos = polku[size];
			size++;
		} else {
			double random = Math.random();
			if (random <= 0.9) {
				path(pos, randomPoint());
				// pos = randomPoint();
			} else {
				path(pos, player.getPos());
				// pos = player.getPos();
			}
			size = 0;
		}
	}

	public Point position() {
		return pos;
	}

	public Point getPos() {
		return pos;
	}

}
