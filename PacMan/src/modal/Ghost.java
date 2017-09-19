package modal;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.control.Tab;

public class Ghost implements Ghost_IF {
	private MovementLogic ml;
	private Player player;

	private Point pos;
	private Point gSize;
	private int tileSize;
	private int size = 1;
	private ArrayList<Point> path = new ArrayList<>();
	private String vulnerable;

	public Ghost(MovementLogic ml, Point gSize, int tileSize, Player player) {
		this.ml = ml;
		this.gSize = gSize;
		this.tileSize = tileSize;
		this.player = player;
	}

	public Point up(Point point) {
		return new Point((int) point.getX(), (int) point.getY() - tileSize);
	}

	public Point down(Point point) {
		return new Point((int) point.getX(), (int) point.getY() + tileSize);
	}

	public Point left(Point point) {
		return new Point((int) point.getX() - tileSize, (int) point.getY());
	}

	public Point right(Point point) {
		return new Point((int) point.getX() + tileSize, (int) point.getY());

	}

	public Point randomPoint() {
		Point point;
		int randX = (int) (Math.random() * gSize.getX() - tileSize);
		while (randX % tileSize != 0) {
			randX = (int) (Math.random() * gSize.getX() - tileSize);
		}
		int randY = (int) (Math.random() * gSize.getY() - tileSize);
		while (randY % tileSize != 0) {
			randY = (int) (Math.random() * gSize.getY() - tileSize);
		}
		point = new Point(randX, randY);
		if (ml.avoidWall(point)) {
			// System.out.println("rand: " + point);
			return point;
		} else {
			return randomPoint();
		}
		// System.out.println(randX+", "+randY)

	}

	public ArrayList<Point> nodes() {
		ArrayList<Point> possibleSpaces = ml.freeSpaces();
		ArrayList<Point> node = new ArrayList<>();
		for (int i = 0; i < gSize.getY();) {
			for (int j = 0; j < gSize.getX();) {
				int possible = 0;
				Point point = new Point(j, i);
				if (possibleSpaces.contains(point)) {
					if (possibleSpaces.contains(up(point)) && !node.contains(up(point))) {
						possible++;
					}
					if (possibleSpaces.contains(down(point)) && !node.contains(down(point))) {
						possible++;
					}
					if (possibleSpaces.contains(left(point)) && !node.contains(left(point))) {
						possible++;
					}
					if (possibleSpaces.contains(right(point)) && !node.contains(right(point))) {
						possible++;

					}
				}
				if (possible > 1) {
					node.add(point);
				}
				j = j + tileSize;
			}
			i = i + tileSize;
		}

		return node;

	}

	public synchronized ArrayList<Point> path(Point target, ArrayList<Point> list) {
		int dX = (int) (target.getX() - list.get(list.size() - 1).getX());
		int dY = (int) (target.getY() - list.get(list.size() - 1).getY());

		// boolean suppress = false;
		ArrayList<Point> possibleSpaces = ml.freeSpaces();
		int possible = 0;
		String string = "";
		// ylös
		if (possibleSpaces.contains(up(list.get(list.size() - 1))) && !list.contains(up(list.get(list.size() - 1)))) {
			//System.out.println("possible up");
			possible++;
			string = string + " up"; //
			//System.out.println("string" + string);
		} // alas
		if (possibleSpaces.contains(down(list.get(list.size() - 1))) && !list.contains(down(list.get(list.size() - 1)))) {
			//System.out.println("possible down");
			possible++;
			string = string + " down";
			// System.out.println("string" + string);
		} // vasemalle
		if (possibleSpaces.contains(left(list.get(list.size() - 1))) && !list.contains(left(list.get(list.size() - 1)))) {
			//System.out.println("possible left");
			possible++;
			string = string + " left";
			// System.out.println("string" + string);
		} // oikealle
		if (possibleSpaces.contains(right(list.get(list.size() - 1))) && !list.contains(right(list.get(list.size() - 1)))) {
			//System.out.println("possible right");
			possible++;
			string = string + " right";
			// System.out.println("string" + string);
		}

		if (possible == 1) {
			if (string.contains("up")) {
				//System.out.println("one up");
				list.add(up(list.get(list.size() - 1)));

			}
			if (string.contains("down")) {
				// System.out.println("one down");
				list.add(down(list.get(list.size() - 1)));

			}
			if (string.contains("left")) {
				// System.out.println("one left");
				list.add(left(list.get(list.size() - 1)));
			}
			if (string.contains("right")) {
				// System.out.println("one right");
				list.add(right(list.get(list.size() - 1)));
			}
			return path(target, list);

		} else if (possible > 1) {
			if (string.contains("up") && dY < 0) {
				//System.out.println("multiple up");
				ArrayList<Point> up = new ArrayList<>();
				up.addAll(list);
				up.add(new Point(up(list.get(list.size() - 1))));
				return path(target, up);

			}
			if (string.contains("down") && dY > 0) {
				//System.out.println("multiple down");
				ArrayList<Point> down = new ArrayList<>();
				down.addAll(list);
				down.add(new Point(down(list.get(list.size() - 1))));
				return path(target, down);

			}
			if (string.contains("left") && dX < 0) { 
				//System.out.println("multiple left");
				ArrayList<Point> left = new ArrayList<>();
				left.addAll(list);
				left.add(new Point(left(list.get(list.size() - 1))));
				return path(target, left);

			}
			if (string.contains("right") && dX > 0) { 
				//System.out.println("multiple right");
				ArrayList<Point> right = new ArrayList<>();
				right.addAll(list);
				right.add(new Point(right(right.get(right.size() - 1))));
				return path(target, right);
			}

		}

		System.out.println("target:" + target + " , " + "position: " + list.get(list.size() - 1));
		// System.out.println("path: "+list.toString());
		return list;
	}

	public synchronized void update() throws NullPointerException {
		setPos(path.get(size));
		// System.out.println("full path: " + path.toString());
		// System.out.println("path size: " + path.size());
		// System.out.println("pos: " + pos);
		// System.out.println("Spos: " + path.get(size));
		size++;

	}

	public void insPath() {
		path.clear();
		double rand = Math.random();
		ArrayList<Point> list = new ArrayList<>();
		// System.out.println(pos);
		// System.out.println("\n new path \n");
		list.add(pos);
		// path.addAll(list);

		// pos = ml.ghostHouse();
		if (rand <= 0.9) {
			Point randpoint = randomPoint();
			// .out.println(nodes().toString());
			// System.out.println(nodes().size());
			path = path(randpoint, list);
			// testPath(randpoint, list);
			// System.out.println(randpoint);
			size = 1;
		} else {
			Point point = player.getPos();
			path = path(point, list);
			// testPath(point, list);
			size = 1;
		}

	}

	public Point getPos() {
		return pos;
	}

	public ArrayList<Point> getPath() {
		return path;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

}
