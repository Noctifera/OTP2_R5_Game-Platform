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

	public ArrayList<Point> move(KeyCode event) {
		
		ArrayList<Point> list = new ArrayList<>();
		switch (event) {
		case W:
			//ylös
			
			Point up = ml.up(pos);
			up = ml.yli(up);
			while(ml.freespaces().contains(up)) {
				list.add(up);
				
				up = ml.up(list.get(list.size()-1));
				up = ml.yli(up);
				
				if(list.contains(up)) {
					break;
				}
				
			}
			break;
		case S:
			// alas
			Point down = ml.down(pos);
			down = ml.yli(down);

			while(ml.freespaces().contains(down)) {
				list.add(down);
				
				down = ml.down(list.get(list.size()-1));
				down = ml.yli(down);
				
				if(list.contains(down)) {
					break;
				}
				
				
			}
			break;
		case A:
			// vasemalle
			Point left = ml.left(pos);
			left = ml.yli(left);
			
			while(ml.freespaces().contains(left)) {
				list.add(left);
				
				left = ml.left(list.get(list.size()-1));
				left = ml.yli(left);
				
				if(list.contains(left)) {
					break;
				}
				
			}
			break;
		case D:
			// oikealle
			Point right = ml.right(pos);
			right = ml.yli(right);

			while(ml.freespaces().contains(right)) {
				list.add(right);
				
				right = ml.right(list.get(list.size()-1));
				right = ml.yli(right);

				if(list.contains(right)) {
					break;
				}
				
			}
			break;
		case ESCAPE:
			// lopetetaan
			System.exit(0);
		default:
			System.out.println("use WASD keys");
			break;
		}
		return list;

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
