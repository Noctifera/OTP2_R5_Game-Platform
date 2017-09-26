package modal;

import java.awt.Point;

import javafx.scene.input.KeyCode;

public class Player extends Score implements Player_IF {
	private Point pos;
	private MovementLogic ml;
	private int blSize;
	private int life;
	private String vulnerable = "deactive";

	public Player(MovementLogic ml, int blSize, int life) {
		super(0);
		this.ml = ml;
		this.blSize = blSize;
		this.life = life;
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public void move(KeyCode event) {
		// TODO Auto-generated method stub
		Point newpos = pos;

		switch (event) {
		case W:
			double up = pos.getY() - blSize;
			newpos = new Point((int) pos.getX(), (int) up);
			// ylös
			// miinus laatikon korkeus
			up = pos.getY() - blSize;
			newpos = new Point((int) pos.getX(), (int) up);
			// System.out.println(newpos);

			newpos = ml.yli(newpos);
			// System.out.println(newpos);
			if (ml.avoidWall(newpos)) {
				pos = newpos;
				score(newpos);
			}
			break;
		case S:
			// alas
			// plus laatikon korkeus
			double down = pos.getY() + blSize;
			newpos = new Point((int) pos.getX(), (int) down);
			newpos = ml.yli(newpos);
			// System.out.println(newpos);
			if (ml.avoidWall(newpos)) {
				pos = newpos;
				score(newpos);
			}
			break;
		case A:
			// miinus laatikon leveys
			// vasemalle
			double left = pos.getX() - blSize;
			newpos = new Point((int) left, (int) pos.getY());
			newpos = ml.yli(newpos);
			// System.out.println(newpos);
			if (ml.avoidWall(newpos)) {
				pos = newpos;
				score(newpos);
			}
			break;
		case D:
			// oikealle
			// plus laatikon leveys
			double right = pos.getX() + blSize;
			newpos = new Point((int) right, (int) pos.getY());
			newpos = ml.yli(newpos);
			// System.out.println(newpos);
			if (ml.avoidWall(newpos)) {
				pos = newpos;
				score(newpos);
			}
			break;
		case ESCAPE:
			// lopetetaan
			System.exit(0);
		default:
			System.out.println("use WASD keys");
			newpos = null;
			break;
		}
		// pm.move();
		System.out.println("player postition: " + pos);

	}

	public void score(Point pos) {
		if ((ml.dots()).contains(pos)) {			
			dot();
			(ml.dots()).remove(pos);
		}
		if (ml.largeDots().contains(pos)) {
			LargeDot();
			if(pos != null) ml.largeDots().remove(pos);
		}

	}

	public String getVulnerable() {
		return vulnerable;
	}

	public void setVulnerable(String vulnerable) {
		this.vulnerable = vulnerable;
	}

	public void getEaten() {
		life--;
	}

	public int getLife() {
		return life;
	}

	public Point playerSpawn() {
		return ml.playerSpawn();
	}
}
