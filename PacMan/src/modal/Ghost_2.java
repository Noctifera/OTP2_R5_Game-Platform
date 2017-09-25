package modal;

import java.awt.Point;
import java.util.ArrayList;

public class Ghost_2  {
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
	public boolean multipleUp(ArrayList<Point> ylist, Point last, Point target) {
		ArrayList<Point> up = new ArrayList<>();

		for (Point p : ylist) {
			if (last.getX() == p.getX() && last.getY() > p.getY()) {
				up.add(p);
			}
		}

		ArrayList<Point> up2 = new ArrayList<>();
		for (int i = (int) last.getY()-tileSize; i >= up.get(up.size() - 1).getY();) {
			Point point = new Point((int) last.getX(), i);
			if (ml.freespaces().contains(point)) {
				up2.add(point);
			}
			i = i - tileSize;
		}

		if (up2.size() == Math.abs((last.getY()-up.get(up.size() - 1).getY())) / tileSize) {
			return true;
		} else {
			return false;
		}

	}

	public ArrayList<Point> moveMultipleUp(ArrayList<Point> ylist, Point last, Point target) {
		ArrayList<Point> up = new ArrayList<>();

		for (Point p : ylist) {
			if (last.getY() > p.getY()) {
				up.add(p);
			}
		}

		ArrayList<Point> up2 = new ArrayList<>();
		for (int i = (int) last.getY()-tileSize; i >= up.get(up.size() - 1).getY();) {
			Point point = new Point((int) last.getX(), i);
			if (ml.freespaces().contains(point)) {
				up2.add(point);
			}
			i = i - tileSize;
		}

		return up2;

	}

	public boolean multipleDown(ArrayList<Point> ylist, Point last, Point target) {
		ArrayList<Point> down = new ArrayList<>();

		for (Point p : ylist) {
			if (last.getY() < p.getY()) {
				down.add(p);
			}
		}

		ArrayList<Point> down2 = new ArrayList<>();
		for (int i = (int) last.getY()+tileSize; i <= down.get(0).getY();) {
			Point point = new Point((int) last.getX(), i);
			if (ml.freespaces().contains(point)) {
				down2.add(point);
			}
			i = i + tileSize;
		}
		
		if (down2.size() == Math.abs((last.getY()-down.get(0).getY())) / tileSize) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<Point> moveMultipleDown(ArrayList<Point> ylist, Point last, Point target) {
		ArrayList<Point> down = new ArrayList<>();

		for (Point p : ylist) {
			if (last.getY() < p.getY()) {
				down.add(p);
			}
		}

		ArrayList<Point> down2 = new ArrayList<>();
		for (int i = (int) last.getY()+tileSize; i <= down.get(0).getY();) {
			Point point = new Point((int) last.getX(), i);
			if (ml.freespaces().contains(point)) {
				down2.add(point);
			}
			i = i + tileSize;
		}

		return down2;
	}

	public boolean multipleLeft(ArrayList<Point> xlist, Point last, Point target) {
		ArrayList<Point> left = new ArrayList<>();

		for (Point p : xlist) {
			if (last.getX() > p.getX()) {
				left.add(p);
			}

		}
		//System.out.println("left: "+left);
		ArrayList<Point> left2 = new ArrayList<>();
		
		for (int i = (int) last.getX()-tileSize; i >= left.get(left.size() - 1).getX();) {
			Point point = new Point(i, (int) last.getY());
			//System.out.println("last: "+last);
			//System.out.println("left point: "+ point);
			if (ml.freespaces().contains(point)) {
				left2.add(point);
			}
			i = i - tileSize;

		}
		
		//System.out.println("left2: "+left2);
		if (left2.size() == Math.abs((last.getX()-left.get(left.size()-1).getX())/tileSize)) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<Point> moveMultipleLeft(ArrayList<Point> xlist, Point last, Point target) {
		ArrayList<Point> left = new ArrayList<>();

		for (Point p : xlist) {
			if (last.getX() > p.getX()) {
				left.add(p);
			}

		}
		ArrayList<Point> left2 = new ArrayList<>();
		for (int i = (int) last.getX()-tileSize; i >= left.get(left.size() - 1).getX();) {
			Point point = new Point(i, (int) last.getY());
			if (ml.freespaces().contains(point)) {
				left2.add(point);
			}
			i = i - tileSize;

		}
		return left2;
	}

	public boolean mulitpleRight(ArrayList<Point> xlist, Point last, Point target) {
		ArrayList<Point> right = new ArrayList<>();

		for (Point p : xlist) {
			if (last.getY() == p.getY() && last.getX() < p.getX()) {
				right.add(p);
			}

		}
		ArrayList<Point> right2 = new ArrayList<>();
		for (int i = (int) last.getX()+tileSize; i <= right.get(0).getX();) {
			Point point = new Point(i, (int) last.getY());

			if (ml.freespaces().contains(point)) {
				right2.add(point);
			}
			i = i + tileSize;
		}
		if (right2.size() == Math.abs((last.getX()-right.get(0).getX())) / tileSize) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<Point> moveMulitpleRight(ArrayList<Point> xlist, Point last, Point target) {
		ArrayList<Point> right = new ArrayList<>();

		for (Point p : xlist) {
			if (last.getY() == p.getY() && last.getX() < p.getX()) {
				right.add(p);
			}

		}
		ArrayList<Point> right2 = new ArrayList<>();
		for (int i = (int) last.getX()+tileSize; i <= right.get(0).getX();) {
			Point point = new Point(i, (int) last.getY());

			if (ml.freespaces().contains(point)) {
				right2.add(point);
			}
			i = i + tileSize;
		}
		return right2;
	}
	
}
