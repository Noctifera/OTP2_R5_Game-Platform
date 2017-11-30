package characters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import hibernate.DataBaseConnection;
import javafx.scene.input.KeyCode;
import map.MovementLogic;

public class Player extends Observable implements Character {

	private MovementLogic ml;
	private Point pos = new Point();
	private Score s;
	private int life;
	private boolean vulnerable = false;
	private boolean eaten = false;
	private boolean gameEnd = false;
	private List<Point> path = new ArrayList<>();
	private int reader = 0;

	public Player(MovementLogic ml, int life) {
		this.ml = ml;
		this.s = new Score(0);
		this.life = life;
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		if (!this.pos.equals(null)) {
			ml.returnToNormal(this.pos);
		}
		this.pos = pos;
	}

	public void path(KeyCode event) {
		path = move(event);
		// System.out.println(path);
		reader = 0;
	}

	public List<Point> getPath() {
		return path;
	}

	private List<Point> move(KeyCode event) {

		ArrayList<Point> list = new ArrayList<>();
		if(event.equals(KeyCode.W)){
			event = KeyCode.UP;
		}else if(event.equals(KeyCode.S)) {
			event = KeyCode.DOWN;
		}else if(event.equals(KeyCode.A)) {
			event = KeyCode.LEFT;
		}else if(event.equals(KeyCode.D)) {
			event = KeyCode.RIGHT;
		}
		switch (event) {
		case UP:
			// ylös

			Point up = ml.up(pos);
			up = ml.yli(up);
			while (ml.freespaces().contains(up)) {
				list.add(up);
				// System.out.println(up);
				up = ml.up(list.get(list.size() - 1));
				up = ml.yli(up);

				if (list.contains(up)) {
					break;
				}

			}
			return list;
		case DOWN:
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
		case LEFT:
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
		case RIGHT:
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
		default:
			System.out.println("use WASD keys");
			break;
		}
		list.add(pos);
		return list;

	}

	public void score(Point pos) {
		if (ml.dots().contains(pos)) {
			s.dot();
			ml.dots().remove(pos);
			ml.playersetTMap(pos, "Empty");
		} else if (ml.largeDots().contains(pos)) {
			s.LargeDot();
			vulnerable = true;
			ml.largeDots().remove(pos);
			ml.playersetTMap(pos, "Empty");

		}

	}

	public ArrayList<Point> allDots() {
		return ml.dots();
	}

	public ArrayList<Point> allLargeDots() {
		return ml.largeDots();
	}

	public boolean getVulnerable() {
		return vulnerable;
	}

	public void setVulnerable(boolean vulnerable) {
		this.vulnerable = vulnerable;
	}

	public int getLife() {
		return life;
	}

	public Point playerSpawn() {
		return ml.playerSpawn();
	}

	public boolean post(String playerName) {
		return DataBaseConnection.post(s.score, playerName);
	}

	@Override
	public boolean iseaten() {
		return eaten;

	}

	@Override
	public Point characterSpawn() {

		return ml.playerSpawn();
	}

	@Override
	public void getNextPos() {

		if (ml.dots().size() == 0 && ml.largeDots().size() == 0) {
			gameEnd = true;
		}if(life <= 0) {
			gameEnd = true;
		}
		ml.returnToNormal(pos);
		pos = path.get(reader);
		score(pos);
		reader++;
		ml.setToMap(pos, "Pacman");
		setChanged();
		notifyObservers(life + "," + s.score);
	}

	@Override
	public void findPath() {
		// TODO Auto-generated method stub
		path.clear();
		reader = 0;
	}
	public void eaten() {
		life--;
	}
	@Override
	public int pathlength() {
		// TODO Auto-generated method stub
		return path.size();
	}

	@Override
	public int getReader() {
		// TODO Auto-generated method stub
		return reader;
	}

	public int getScore() {
		return s.getScore();
	}

	public void setScore(int score) {
		s.setScore(score);
	}

	@Override
	public boolean isGameEnd() {
		// TODO Auto-generated method stub
		return gameEnd;
	}

	@Override
	public void setEaten(boolean eaten) {
		// TODO Auto-generated method stub
		this.eaten = eaten;
	}

}
