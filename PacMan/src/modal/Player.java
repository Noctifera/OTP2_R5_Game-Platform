package modal;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.input.KeyCode;

public class Player extends Score implements Player_IF {
	private MovementLogic ml;
	private HighScore hs;

	private Point pos;
	private int life;
	private String vulnerable = "deactive";
	private Sounds sounds;

	public Player(MovementLogic ml, HighScore hs, int life,Sounds sounds) {
		super(0);
		this.ml = ml;
		this.hs = hs;
		this.life = life;
		this.sounds = sounds;
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
			// ylös

			Point up = ml.up(pos);
			up = ml.yli(up);
			while (ml.freespaces().contains(up)) {
				list.add(up);

				up = ml.up(list.get(list.size() - 1));
				up = ml.yli(up);

				if (list.contains(up)) {
					break;
				}

			}
			return list;
		case S:
			// alas
			Point down = ml.down(pos);
			down = ml.yli(down);

			while (ml.freespaces().contains(down)) {
				list.add(down);

				down = ml.down(list.get(list.size() - 1));
				down = ml.yli(down);

				if (list.contains(down)) {
					break;
				}

			}
			return list;
		case A:
			// vasemalle
			Point left = ml.left(pos);
			left = ml.yli(left);

			while (ml.freespaces().contains(left)) {
				list.add(left);

				left = ml.left(list.get(list.size() - 1));
				left = ml.yli(left);

				if (list.contains(left)) {
					break;
				}

			}
			return list;
		case D:
			// oikealle
			Point right = ml.right(pos);
			right = ml.yli(right);

			while (ml.freespaces().contains(right)) {
				list.add(right);

				right = ml.right(list.get(list.size() - 1));
				right = ml.yli(right);

				if (list.contains(right)) {
					break;
				}

			}
			return list;
		case ESCAPE:
			// lopetetaan
			System.exit(0);
		default:
			System.out.println("use WASD keys");
			break;
		}
		list.add(pos);
		return list;

	}

	public void score(Point pos) {
		if ((ml.dots()).contains(pos)) {
			sounds.playSound(sounds.getDot());
			dot();
			(ml.dots()).remove(pos);
		}
		else if (ml.largeDots().contains(pos)) {
			sounds.playSound(sounds.getLargeDot());
			LargeDot();
			if (pos != null)
				ml.largeDots().remove(pos);
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

	public void gethighScore() {
		hs.selectFromDatabase();
	}

	public void setHighScore(int score, String playername, String date) {
		hs.post(score, playername, date);
	}
	public ArrayList<String> presentScore() {
		return hs.getScore();
	}
	
	public ArrayList<String> presentName() {
		return hs.getName();
	}
	
	public ArrayList<String> presentDate() {
		return hs.getDate();
	}

}
