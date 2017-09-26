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
			if (possible >= 1) {
				node.add(point);
			}

		}

		return node;

	}

	public ArrayList<Point> smallPath(ArrayList<Point> list, Point last, Point target, ArrayList<Point> xlist,
			ArrayList<Point> ylist) {
		System.out.println("small path start");
		String string = "";
		if (!doesNotContain(moveMultipleUp(ylist, last, target), list)) {
			// System.out.println("possible up");
			string = string + " up"; //
			// System.out.println("string" + string);
		} // alas
		if (!doesNotContain(moveMultipleDown(ylist, last, target), list)) {
			// System.out.println("possible down");
			string = string + " down";
			// System.out.println("string" + string);
		} // vasemalle
		if (!doesNotContain(moveMultipleLeft(xlist, last, target), list)) {
			// System.out.println("possible left");
			string = string + " left";
			// System.out.println("string" + string);
		} // oikealle
		if (!doesNotContain(moveMulitpleRight(xlist, last, target), list)) {
			// System.out.println("possible right");
			string = string + " right";
			// System.out.println("string" + string);
		}
		return multipleMove(string, last, target, xlist, ylist, list);
	}
	public void aStar(Point start, Point goal) {
		
		ArrayList<Point> openSet = ml.freespaces();
		ArrayList<Node> closedSet = new ArrayList<>();
		
		
		 closedSet.add(new Node(start,0,Integer.MAX_VALUE));
		 openSet.remove(start);
		Point current = start;
		while(!openSet.isEmpty()) {
			if(current.equals(goal)) {
				//path found
				retrunPath();
				break;
			}
			if(openSet.contains(up(current))){
				double gScore = Math.abs(current.getY()- up(current).getY());
				double fScore = Math.abs((goal.getX() - up(current).getX()))+ Math.abs((goal.getY() - up(current).getY()));
				
				closedSet.add(new Node(up(current), gScore,fScore));
				openSet.remove(up(current));
			}
			if(openSet.contains(right(current))) {
				double gScore = Math.abs(current.getX()- right(current).getX());
				double fScore = Math.abs((goal.getX() - right(current).getX()))+ Math.abs((goal.getY() - right(current).getY()));
				
				closedSet.add(new Node(right(current), gScore,fScore));
				openSet.remove(right(current));
			}
			if(openSet.contains(down(current))) {
				double gScore = Math.abs(current.getY()- down(current).getY());
				double fScore = Math.abs((goal.getX() - down(current).getX()))+ Math.abs((goal.getY() - down(current).getY()));
				closedSet.add(new Node(down(current), gScore,fScore));
				openSet.remove(down(current));
				
			}
			if(openSet.contains(left(current))) {
				double gScore = Math.abs(current.getX()- left(current).getX());
				double fScore = Math.abs((goal.getX() - left(current).getX()))+ Math.abs((goal.getY() - left(current).getY()));
				
				closedSet.add(new Node(left(current), gScore,fScore));
				openSet.remove(left(current));
				
			}
			int k = 0 ;
			for(int i = 0; i<closedSet.size(); i++) {
				if(closedSet.get(i).getHscore()< closedSet.get(k).getHscore() ) {
					k = i;
				}
			}
			
			
			
			
			
			
			
			
		}
		
	}
	public ArrayList<Point> retrunPath(){
		System.out.println("path found");
		return null;
	}
	
	

	public ArrayList<Point> path(Point target, ArrayList<Point> list) {
		System.out.println("method start");
		System.out.println("target:" + target + " , " + "position: " + list.get(list.size() - 1));

		ArrayList<Point> nodes = nodes();
		ArrayList<Point> xlist = new ArrayList<>();
		ArrayList<Point> ylist = new ArrayList<>();

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
		System.out.println("xlist: " + xlist);
		System.out.println("ylist: " + ylist);
		// ylös
		if (nodesUP(ylist, last)) {
			if (dY < 0 && !doesNotContain(moveMultipleUp(ylist, last, target), list)) {
				// System.out.println("possible up");
				possible++;
				string = string + " up"; //
				// System.out.println("string" + string);
			}
		}
		// alas
		if (nodesDown(ylist, last)) {
			if (dY > 0 && !doesNotContain(moveMultipleDown(ylist, last, target), list)) {
				// System.out.println("possible down");
				possible++;
				string = string + " down";
				// System.out.println("string" + string);
			}
		}
		// vasemalle
		if (nodesLeft(xlist, last)) {
			if (dX < 0 && !doesNotContain(moveMultipleLeft(xlist, last, target), list)) {
				// System.out.println("possible left");
				possible++;
				string = string + " left";
				// System.out.println("string" + string);
			}
		}
		// oikealle
		if (nodesRight(xlist, last)) {
			if (dX > 0 && !doesNotContain(moveMulitpleRight(xlist, last, target), list)) {
				// System.out.println("possible right");
				possible++;
				string = string + " right";
				// System.out.println("string" + string);
			}
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
					String apu = " right";
					return multipleMove(apu, last, target, xlist, ylist, list);

				}
			}
			if (string.contains("down")) {
				if (multipleDown(ylist, last, target)) {
					moved++;
					System.out.println("one down");
					list.addAll(moveMultipleDown(ylist, last, target));
				} else {
					System.out.println("not possible down");
					String apu = " left";
					return multipleMove(apu, last, target, xlist, ylist, list);
				}

			}
			if (string.contains("left")) {
				if (multipleLeft(xlist, last, target)) {
					moved++;
					System.out.println("one left");
					list.addAll(moveMultipleLeft(xlist, last, target));
				} else {
					System.out.println("not possible left");
					String apu = " up";
					return multipleMove(apu, last, target, xlist, ylist, list);

				}

			}
			if (string.contains("right")) {
				if (mulitpleRight(xlist, last, target)) {
					moved++;
					System.out.println("one right");
					list.addAll(moveMulitpleRight(xlist, last, target));
				} else {
					System.out.println("not possible right");
					String apu = " down";
					return multipleMove(apu, last, target, xlist, ylist, list);
				}

			}
			if (moved > 0) {
				return path(target, list);
			}

		} else if (possible > 1) {
			return multipleMove(string, last, target, xlist, ylist, list);
		}

		if (target.equals(list.get(list.size() - 1))) {
			System.out.println("path found");
			System.out.println("path: " + list.toString());
			return list;
		} else {
			if (string.hashCode() == 0) {
				return smallPath(list, last, target, xlist, ylist);
			} else {
				System.out.println("not path: " + list);
				ArrayList<Point> nonPath = new ArrayList<>();
				nonPath.add(list.get(0));
				return nonPath;
			}
		}
		// System.out.println("finished path: " + list.toString());
	}

	public ArrayList<Point> multipleMove(String string, Point last, Point target, ArrayList<Point> xlist,
			ArrayList<Point> ylist, ArrayList<Point> list) {

		if (string.contains("up")) {
			System.out.println("multi Up");
			if (multipleUp(ylist, last, target)) {
				list.addAll(moveMultipleUp(ylist, last, target));
				return path(target, list);
			} else {
				System.out.println("not possible multi up");
				String apu = string.replace(" up", "");
				System.out.println(apu);
				if (apu.hashCode() != 0) {
					return multipleMove(apu, last, target, xlist, ylist, list);
				} else {
					String s = " right";
					return multipleMove(s, last, target, xlist, ylist, list);
				}
			}

		}
		if (string.contains("down")) {
			System.out.println("multi Down");
			if (multipleDown(ylist, last, target)) {
				list.addAll(moveMultipleDown(ylist, last, target));
				return path(target, list);
			} else {
				System.out.println("not possible multi down");
				String apu = string.replace(" down", "");
				System.out.println(apu);
				if (apu.hashCode() != 0) {
					return multipleMove(apu, last, target, xlist, ylist, list);
				} else {
					String s = " left";
					return multipleMove(s, last, target, xlist, ylist, list);
				}
			}
		}

		if (string.contains("left")) {
			System.out.println("multi Left");
			if (multipleLeft(xlist, last, target)) {
				list.addAll(moveMultipleLeft(xlist, last, target));
				return path(target, list);
			} else {
				System.out.println("not possible multi left");
				String apu = string.replace(" left", "");
				System.out.println(apu);
				if (apu.hashCode() != 0) {
					return multipleMove(apu, last, target, xlist, ylist, list);
				} else {
					String s = " up";
					return multipleMove(s, last, target, xlist, ylist, list);
				}
			}
		}

		if (string.contains("right")) {
			System.out.println("multi Right");
			if (mulitpleRight(xlist, last, target)) {
				list.addAll(moveMulitpleRight(xlist, last, target));
				return path(target, list);
			} else {
				System.out.println("not possible multi right");
				String apu = string.replace(" right", "");
				System.out.println(apu);
				if (apu.hashCode() != 0) {
					return multipleMove(apu, last, target, xlist, ylist, list);
				} else {
					String s = " down";
					return multipleMove(s, last, target, xlist, ylist, list);
				}
			}
		}

		return path(target, list);
	}

	public ArrayList<Point> insPath() {

		double rand = Math.random();
		ArrayList<Point> list = new ArrayList<>();
		// System.out.println(pos);
		// System.out.println("\n new path \n");
		list.add(pos);

		if (rand <= 0.9) {
			Point randpoint = randomPoint();
			size = 1;

			return path(randpoint, list);

		} else {
			Point point = player.getPos();
			size = 1;

			return path(point, list);

		}

	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public Point ghostHouse() {
		return ml.ghostHouse();
	}
}