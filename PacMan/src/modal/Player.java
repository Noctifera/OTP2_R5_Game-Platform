package modal;

import java.awt.Point;

import javafx.scene.input.KeyEvent;

public class Player extends Score implements Player_IF {
	private Point pos;
	private MovementLogic ml;
	private int blSize;
	private String[] strings;
	private int life;
	private String vulnerable = "deactive";

	public Player(MovementLogic ml, int blSize,String[] strings, int life) {
		super(0);
		this.ml = ml;
		this.blSize = blSize;
		this.strings = strings;
		this.life = life;
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public void move(KeyEvent event) {
		// TODO Auto-generated method stub
		Point newpos = pos;

		switch (event.getCode()) {
		case W:
			// miinus laatikon korkeus
			double up = pos.getY() - blSize;
			newpos = new Point((int) pos.getX(), (int) up);
			//System.out.println(newpos);

			newpos = ml.yli(newpos);
			//System.out.println(newpos);
			if (ml.avoidWall(newpos)) {
				pos = newpos;
				score(newpos);
			}
			break;
		case S:
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
			System.exit(0);
		default:
			System.out.println("use WASD keys");
			newpos = null;
			break;
		}
		// pm.move();
		System.out.println("player postition: "+pos);

	}
	public void score(Point pos){
		if(ml.score(pos).equals(strings[0])){
			dot();

		}if(ml.score(pos).equals(strings[1])){
			LargeDot();
			//vulnerable = "active";
		}
	}
	public String getVulnerable() {
		return vulnerable;
	}

	public void setVulnerable(String vulnerable) {
		this.vulnerable = vulnerable;
	}

	public void getEaten(){
		life--;
	}

	public int getLife() {
		return life;
	}
}
