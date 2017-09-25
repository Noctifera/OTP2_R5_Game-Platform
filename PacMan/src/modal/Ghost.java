package modal;

import java.awt.Point;
import java.util.ArrayList;

public class Ghost extends Ghost_2 implements Ghost_IF {
	private MovementLogic ml;
	private Player player;

	private Point pos;
	private Point gSize;
	private int tileSize;
	private int size = 1;
	private ArrayList<Point> path = new ArrayList<>();
	ArrayList<Ghost_2> gh2list = new ArrayList<>();
	// private String vulnerable;

	public Ghost(MovementLogic ml, Point gSize, int tileSize, Player player) {
		super(tileSize, ml);
		this.ml = ml;
		this.gSize = gSize;
		this.tileSize = tileSize;
		this.player = player;
	}

	public int getSize() {
		return size;
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
		// if (ml.avoidWall(point)) {
		if (nodes().contains(point)) {
			// System.out.println("rand: " + point);
			return point;
		} else {
			return randomPoint();
		}
		// System.out.println(randX+", "+randY)

	}

	public ArrayList<Point> nodes() {
		ArrayList<Point> possibleSpaces = ml.freespaces();
		ArrayList<Point> node = new ArrayList<>();
		for (Point point : possibleSpaces) {
			// System.out.println("Nodes point: "+point);
			int possible = 0;
			if (possibleSpaces.contains(up(point))) {
				possible++;
			}
			if (possibleSpaces.contains(down(point))) {
				possible++;
			}
			if (possibleSpaces.contains(left(point))) {
				possible++;
			}
			if (possibleSpaces.contains(right(point))) {
				possible++;

			}
			if (possible >= 2) {
				node.add(point);
			}

		}

		return node;

	}

	public ArrayList<Point> path(Point target, ArrayList<Point> list) {
		System.out.println("method start");
		System.out.println("target:" + target + " , " + "position: " + list.get(list.size() - 1));
		// System.out.println("path: " + list.toString());
		if (target.equals(list.get(list.size() - 1))) {
			System.out.println("path found");
			return list;
		} else {

			ArrayList<Point> nodes = nodes();
			ArrayList<Point> xlist = new ArrayList<>();
			ArrayList<Point> ylist = new ArrayList<>();

			ArrayList<Point> possibleSpaces = ml.freespaces();
			int possible = 0;
			String string = "";

			// System.out.println("target: " + target + " , " + "last position: " +
			// list.get(list.size() - 1));
			System.out.println("nodes: " + nodes);

			Point last = list.get(list.size() - 1);

			if (nodes.contains(last)) {

				for (Point p : nodes) {
					if (last.getY() == p.getY()) {
						xlist.add(p);
					}
					if (last.getX() == p.getX()) {
						ylist.add(p);
					}

				}
			}

			int dX = (int) (target.getX() - list.get(list.size() - 1).getX());
			int dY = (int) (target.getY() - list.get(list.size() - 1).getY());
			System.out.println("dX: " + dX);
			System.out.println("dY: " + dY);
			// ylös
			if (dY < 0) {
				// System.out.println("possible up");
				possible++;
				string = string + " up"; //
				// System.out.println("string" + string);
			} // alas
			if (dY > 0) {
				// System.out.println("possible down");
				possible++;
				string = string + " down";
				// System.out.println("string" + string);
			} // vasemalle
			if (dX < 0) {
				// System.out.println("possible left");
				possible++;
				string = string + " left";
				// System.out.println("string" + string);
			} // oikealle
			if (dX > 0) {
				// System.out.println("possible right");
				possible++;
				string = string + " right";
				// System.out.println("string" + string);
			}
			System.out.println("string: " + string);
			if (possible == 1) {
				int moved = 0;

				if (string.contains("up")) {
					if (multipleUp(ylist, last, target)) {
						moved++;
						System.out.println("one up");
						list.addAll(moveMultipleUp(ylist, last, target));
					} else {
						System.out.println("not possible up");
					}
				}
				if (string.contains("down")) {
					if (multipleDown(ylist, last, target)) {
						moved++;
						System.out.println("one down");
						list.addAll(moveMultipleDown(ylist, last, target));
					} else {
						System.out.println("not possible down");
					}

				}
				if (string.contains("left")) {
					if (multipleLeft(xlist, last, target)) {
						moved++;
						System.out.println("one left");
						list.addAll(moveMultipleLeft(xlist, last, target));
					} else {
						System.out.println("not possible left");

					}

				}
				if (string.contains("right")) {
					if (mulitpleRight(xlist, last, target)) {
						moved++;
						System.out.println("one right");
						list.addAll(moveMulitpleRight(xlist, last, target));
					} else {
						System.out.println("not possible right");
					}

				}
				if (moved > 0) {
					return path(target, list);
				}

			} else if (possible > 1) {
				multipleMove(string, last, target, xlist, ylist, list);
			}

			System.out.println("not path: " + list);
			return null;
		}
		// System.out.println("finished path: " + list.toString());
	}

	public ArrayList<Point> multipleMove(String string, Point last, Point target, ArrayList<Point> xlist,
			ArrayList<Point> ylist, ArrayList<Point> list) {
		if (string.contains("up")) {
			if (multipleUp(ylist, last, target)) {
				list.addAll(moveMultipleUp(ylist, last, target));
				return path(target, list);
			} else {
				System.out.println("not possible multi up");
				String apu = string.replace(" up", "");
				if (apu != "") {
					return multipleMove(apu, last, target, xlist, ylist, list);
				} else {
					return path(target, list);
				}
			}

		}
		if (string.contains("down")) {
			if (multipleDown(ylist, last, target)) {
				list.addAll(moveMultipleDown(ylist, last, target));
				return path(target, list);
			} else {
				System.out.println("not possible multi down");
				String apu = string.replace(" down", "");
				if (apu != "") {
					return multipleMove(apu, last, target, xlist, ylist, list);
				} else {
					return path(target, list);
				}

			}

		}
		if (string.contains("left")) {
			if (multipleLeft(xlist, last, target)) {
				list.addAll(moveMultipleLeft(xlist, last, target));
				return path(target, list);
			} else {
				System.out.println("not possible multi left");
				String apu = string.replace(" left", "");
				if (apu != "") {
					return multipleMove(apu, last, target, xlist, ylist, list);
				} else {
					return path(target, list);
				}
			}

		}
		if (string.contains("right")) {
			if (mulitpleRight(xlist, last, target)) {
				list.addAll(moveMulitpleRight(xlist, last, target));
				return path(target, list);
			} else {
				System.out.println("not possible multi right");
				String apu = string.replace(" right", "");
				if (apu != "") {
					return multipleMove(apu, last, target, xlist, ylist, list);
				} else {
					return path(target, list);
				}
			}

		}
		return null;

	}

	public synchronized void update() throws NullPointerException {
		setPos(path.get(size));
		// System.out.println("full path: " + path.toString());
		// System.out.println("path size: " + path.size());
		// System.out.println("pos: " + pos);
		// System.out.println("Spos: " + path.get(size));
		size++;

	}

	public void insPath() throws InterruptedException {
		path.clear();
		gh2list.clear();
		double rand = Math.random();
		ArrayList<Point> list = new ArrayList<>();
		// System.out.println(pos);
		// System.out.println("\n new path \n");
		list.add(pos);

		if (rand <= 0.9) {
			Point randpoint = randomPoint();
			// System.out.println(nodes().toString());
			// System.out.println(nodes().size());
			path = path(randpoint, list);
			// System.out.println("target: " + randpoint);
			// testPath(randpoint, list);

			size = 1;
		} else {
			Point point = player.getPos();
			path = path(point, list);
			// System.out.println("target: " + point);
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

	public Point ghostHouse() {
		return ml.ghostHouse();
	}
}