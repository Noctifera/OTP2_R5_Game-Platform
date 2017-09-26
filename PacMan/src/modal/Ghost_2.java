package modal;

import java.awt.Point;
import java.util.ArrayList;

public class Ghost_2 {
	private int tileSize;
	private MovementLogic ml;

	public Ghost_2(int tileSize, MovementLogic ml) {
		this.tileSize = tileSize;
		this.ml = ml;
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

	public boolean nodesUP(ArrayList<Point> ylist, Point last) {
		boolean check = false;
		for (Point p : ylist) {
			if (last.getY() > p.getY()) {
				check = true;
			}
		}

		return check;
	}

	public boolean nodesDown(ArrayList<Point> ylist, Point last) {
		boolean check = false;
		for (Point p : ylist) {
			if (last.getY() < p.getY()) {
				check = true;
			}
		}

		return check;
	}

	public boolean nodesLeft(ArrayList<Point> xlist, Point last) {
		boolean check = false;
		for (Point p : xlist) {
			if (last.getX() > p.getX()) {
				check = true;
			}
		}

		return check;
	}

	public boolean nodesRight(ArrayList<Point> xlist, Point last) {
		boolean check = false;
		for (Point p : xlist) {
			if (last.getX() < p.getX()) {
				check = true;
			}
		}

		return check;
	}

	public boolean multipleUp(ArrayList<Point> ylist, Point last, Point target) {
		ArrayList<Point> up = new ArrayList<>();

		if (nodesUP(ylist, last)) {
			for (Point p : ylist) {
				if (last.getX() == p.getX() && last.getY() > p.getY()) {
					up.add(p);
				}
			}

			ArrayList<Point> up2 = new ArrayList<>();
			for (int i = (int) last.getY() - tileSize; i >= up.get(up.size() - 1).getY();) {
				Point point = new Point((int) last.getX(), i);
				if (ml.freespaces().contains(point)) {
					up2.add(point);
				}
				i = i - tileSize;
			}

			if (up2.size() == Math.abs((last.getY() - up.get(up.size() - 1).getY())) / tileSize) {
				return true;
			} else {
				return false;
			}
		}
		return false;

	}

	public ArrayList<Point> moveMultipleUp(ArrayList<Point> ylist, Point last, Point target) {
		ArrayList<Point> up = new ArrayList<>();

		if (multipleUp(ylist, last, target)) {
			for (Point p : ylist) {
				if (last.getY() > p.getY()) {
					up.add(p);
				}
			}

			ArrayList<Point> up2 = new ArrayList<>();
			for (int i = (int) last.getY() - tileSize; i >= up.get(up.size() - 1).getY();) {
				Point point = new Point((int) last.getX(), i);
				if (ml.freespaces().contains(point)) {
					up2.add(point);
				}
				i = i - tileSize;
			}

			return up2;

		}
		ArrayList<Point> apu = new ArrayList<>();
		apu.add(last);
		return apu;
	}

	public boolean multipleDown(ArrayList<Point> ylist, Point last, Point target) {
		ArrayList<Point> down = new ArrayList<>();

		if (nodesDown(ylist, last)) {
			for (Point p : ylist) {
				if (last.getY() < p.getY()) {
					down.add(p);
				}
			}

			ArrayList<Point> down2 = new ArrayList<>();
			for (int i = (int) last.getY() + tileSize; i <= down.get(0).getY();) {
				Point point = new Point((int) last.getX(), i);
				if (ml.freespaces().contains(point)) {
					down2.add(point);
				}
				i = i + tileSize;
			}

			if (down2.size() == Math.abs((last.getY() - down.get(0).getY())) / tileSize) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public ArrayList<Point> moveMultipleDown(ArrayList<Point> ylist, Point last, Point target) {
		ArrayList<Point> down = new ArrayList<>();
		if (multipleDown(ylist, last, target)) {
			for (Point p : ylist) {
				if (last.getY() < p.getY()) {
					down.add(p);
				}
			}

			ArrayList<Point> down2 = new ArrayList<>();
			for (int i = (int) last.getY() + tileSize; i <= down.get(0).getY();) {
				Point point = new Point((int) last.getX(), i);
				if (ml.freespaces().contains(point)) {
					down2.add(point);
				}
				i = i + tileSize;
			}

			return down2;
		}
		ArrayList<Point> apu = new ArrayList<>();
		apu.add(last);
		return apu;
	}

	public boolean multipleLeft(ArrayList<Point> xlist, Point last, Point target) {
		ArrayList<Point> left = new ArrayList<>();

		if (nodesLeft(xlist, last)) {
			for (Point p : xlist) {
				if (last.getX() > p.getX()) {
					left.add(p);
				}

			}
			// System.out.println("left: "+left);
			ArrayList<Point> left2 = new ArrayList<>();

			for (int i = (int) last.getX() - tileSize; i >= left.get(left.size() - 1).getX();) {
				Point point = new Point(i, (int) last.getY());
				// System.out.println("last: "+last);
				// System.out.println("left point: "+ point);
				if (ml.freespaces().contains(point)) {
					left2.add(point);
				}
				i = i - tileSize;

			}

			// System.out.println("left2: "+left2);
			if (left2.size() == Math.abs((last.getX() - left.get(left.size() - 1).getX()) / tileSize)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public ArrayList<Point> moveMultipleLeft(ArrayList<Point> xlist, Point last, Point target) {
		ArrayList<Point> left = new ArrayList<>();
		if (multipleLeft(xlist, last, target)) {
			for (Point p : xlist) {
				if (last.getX() > p.getX()) {
					left.add(p);
				}

			}
			ArrayList<Point> left2 = new ArrayList<>();
			for (int i = (int) last.getX() - tileSize; i >= left.get(left.size() - 1).getX();) {
				Point point = new Point(i, (int) last.getY());
				if (ml.freespaces().contains(point)) {
					left2.add(point);
				}
				i = i - tileSize;

			}
			return left2;
		}
		ArrayList<Point> apu = new ArrayList<>();
		apu.add(last);
		return apu;
	}

	public boolean mulitpleRight(ArrayList<Point> xlist, Point last, Point target) {
		ArrayList<Point> right = new ArrayList<>();

		if (nodesRight(xlist, last)) {
			for (Point p : xlist) {
				if (last.getX() < p.getX()) {
					right.add(p);
				}

			}
			ArrayList<Point> right2 = new ArrayList<>();
			for (int i = (int) last.getX() + tileSize; i <= right.get(0).getX();) {
				Point point = new Point(i, (int) last.getY());

				if (ml.freespaces().contains(point)) {
					right2.add(point);
				}
				i = i + tileSize;
			}
			if (right2.size() == Math.abs((last.getX() - right.get(0).getX())) / tileSize) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public ArrayList<Point> moveMulitpleRight(ArrayList<Point> xlist, Point last, Point target) {
		ArrayList<Point> right = new ArrayList<>();
		if (mulitpleRight(xlist, last, target)) {
			for (Point p : xlist) {
				if (last.getX() < p.getX()) {
					right.add(p);
				}

			}
			ArrayList<Point> right2 = new ArrayList<>();
			for (int i = (int) last.getX() + tileSize; i <= right.get(0).getX();) {
				Point point = new Point(i, (int) last.getY());

				if (ml.freespaces().contains(point)) {
					right2.add(point);
				}
				i = i + tileSize;
			}
			return right2;
		}
		ArrayList<Point> apu = new ArrayList<>();
		apu.add(last);
		return apu;
	}

	public boolean doesNotContain(ArrayList<Point> smallList, ArrayList<Point> list) {
		boolean contains = false;
		for (Point p : smallList) {
			if (list.contains(p)) {
				contains = true;
			}

		}
		return contains;

	}

}
