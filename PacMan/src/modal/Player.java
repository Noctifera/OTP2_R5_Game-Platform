package modal;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.input.KeyCode;

public class Player extends Score implements Player_IF {
	private Point pos;
	private MovementLogic ml;
	private int life;
	private String vulnerable = "deactive";

	public Player(MovementLogic ml, int life) {
		super(0);
		this.ml = ml;
		this.life = life;
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public void move(KeyCode event) {
		
		ArrayList<Point> list = new ArrayList<>();
		switch (event) {
		case W:
			//ylös
			Point up = ml.up(pos);
			up = ml.yli(up);
			
			if (ml.freespaces().contains(up)) {
				pos = up;
				score(up);
			}
			
			break;
		case S:
			// alas
			Point down = ml.down(pos);
			down = ml.yli(down);

			if (ml.freespaces().contains(down)) {
				pos = down;
				score(down);
			}
			break;
		case A:
			// vasemalle
			Point left = ml.left(pos);
			left = ml.yli(left);
			
			if (ml.freespaces().contains(left)) {
				pos = left;
				score(left);
			}
			break;
		case D:
			// oikealle
			Point right = ml.right(pos);
			right = ml.yli(right);

			if (ml.freespaces().contains(right)) {
				pos = right;
				score(right);
			}
			break;
		case ESCAPE:
			// lopetetaan
			System.exit(0);
		default:
			System.out.println("use WASD keys");
			break;
		}

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
