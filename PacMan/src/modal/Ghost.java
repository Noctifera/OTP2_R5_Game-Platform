package modal;

import java.awt.Point;

public class Ghost {
	private MovementLogic ml;
	private Player player;

	private Point[] polku = new Point[5];
	private Point pos;
	private Point gSize;
	private int tileSize;
	private int size;

	public Ghost(MovementLogic ml, Point gSize, int tileSize, Player player) {
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
		if (ml.avoidWall(point)) {
			System.out.println("rand: " + point);
			return point;
		} else {
			return randomPoint();
		}
		// System.out.println(randX+", "+randY);

	}

	public Point left(Point pos) {
		// minus tile width
		Point newpoint = new Point((int) pos.getX() - tileSize, (int) pos.getY());
		//newpoint = ml.yli(newpoint);
		if (ml.avoidWall(newpoint)) {
			return newpoint;
		} else {
			return pos;
		}

	}

	public Point up(Point pos) {
		// minus tile height
		Point newpoint = new Point((int) pos.getX(), (int) pos.getY() - tileSize);
		//newpoint = ml.yli(newpoint);
		if (ml.avoidWall(newpoint)) {
			return ml.yli(newpoint);
		} else {
			return pos;
		}
	}

	public Point right(Point pos) {
		// add tile width
		Point newpoint = new Point((int) pos.getX() + tileSize, (int) pos.getY());
		//newpoint = ml.yli(newpoint);
		if (ml.avoidWall(newpoint)) {
			return newpoint;
		} else {
			return pos;
		}
	}

	public Point down(Point pos) {
		// add tile height
		Point newpoint = new Point((int) pos.getX(), (int) pos.getY() + tileSize);
		//newpoint = ml.yli(newpoint);
		if (ml.avoidWall(newpoint)) {
			return newpoint;
		} else
			return pos;
	}

	public Point nextPoint(Point pos, Point target) {
		double dX = target.getX() - pos.getX();
		// System.out.println(dX);
		double dY = target.getY() - pos.getY();
		// System.out.println(dY);
		double posY = pos.getY();
		double posX = pos.getX();
		size = 1;
		if (dX < 0) {
			// left
			posX = left(new Point((int)posX, (int)posY)).getX();

			if(posX == pos.getX()){
				posX = right(new Point((int)posX,(int)posY)).getX();
			}

		}
		if (dX > 0) {
			// right
			posX = right(new Point((int)posX, (int)posY)).getX();

			if(posX == pos.getX()){
				posX = left(new Point((int)posX,(int)posY)).getX();
			}

		}
		if (dY < 0) {
			// up
			posY = up(new Point((int)posX, (int)posY)).getY();

			if(posY == pos.getY()){
				posY = down(new Point((int)posX,(int)posY)).getY();
			}

		}
		if (dY > 0) {
			// down
			posY = down(new Point((int)posX, (int)posY)).getY();

			if(posY == pos.getY()){
				posY = up(new Point((int)posX,(int)posY)).getY();
			}

		}
		return new Point((int) posX, (int) posY);

	}

	public synchronized void update() {
		if (polku[0] != null && size < polku.length) {
			pos = polku[size];
			size++;
		}
		else {
			double random = Math.random();
			if (random <= 0.9) {
				Point rand = randomPoint();
				for (int i = 0; i < polku.length; i++) {
					if (i == 0) {
						polku[i] = nextPoint(pos, rand);
					} else {
						polku[i] = nextPoint(polku[i - 1], rand);
					}
				}

				// pos = randomPoint();
			} else {
				Point p = player.getPos();
				for(int i = 0; i<polku.length; i++){
					if(i == 0){
						polku[i] = nextPoint(pos,p);
					}else{
						polku[i] = nextPoint(polku[i-1], p);
					}

				}

				// pos = player.getPos();
			}
			size = 0;
		}
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

}
