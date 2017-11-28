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
	private Point pos;
	private Score s;
	private int life;
	private boolean vulnerable = false;
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
		this.pos = pos;
	}
	public void path(KeyCode event) {
		path = move(event);
		//System.out.println(path);
		reader = 0;
	}
	

	public List<Point> getPath() {
		return path;
	}

	private List<Point> move(KeyCode event) {

		ArrayList<Point> list = new ArrayList<>();
		
		switch (event) {
		case W:
			// ylös

			Point up = ml.up(pos);
			up = ml.yli(up);
			while (ml.freespaces().contains(up)) {
				list.add(up);
				//System.out.println(up);
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
		default:
			System.out.println("use WASD keys");
			break;
		}
		list.add(pos);
		return list;

	}

	public void score(Point pos) {
		if ((ml.dots()).contains(pos)) {
			s.dot();
			(ml.dots()).remove(pos);
		}
		else if (ml.largeDots().contains(pos)) {
			s.LargeDot();			
			vulnerable = true;
			
			if (pos != null)
				ml.largeDots().remove(pos);
		}
		


	}
	public ArrayList<Point> allDots(){
		return ml.dots();
	}
	public ArrayList<Point> allLargeDots(){
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
	public boolean eaten() {
		// TODO Auto-generated method stub
		return false;
		
	}

	@Override
	public Point characterSpawn() {
		// TODO Auto-generated method stub
		return ml.playerSpawn();
	}

	@Override
	public void getNextPos() {
		// TODO Auto-generated method stub
		ml.playersetTMap(pos, "Empty");
		pos = path.get(reader);
		score(pos);
		reader++;
		ml.playersetTMap(pos, "Player");
		setChanged();
		notifyObservers(life+","+s.score);
	}

	@Override
	public void findPath() {
		// TODO Auto-generated method stub
		path.clear();
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

}
