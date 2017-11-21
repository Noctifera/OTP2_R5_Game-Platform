package characters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import hibernate.DataBaseConnection;
import javafx.scene.input.KeyCode;
import map.MovementLogic;

public class Player extends Score implements Character {
	private MovementLogic ml;

	private Point pos;
	private int life;
	private String vulnerable = "deactive";
	private List<Point> path = new ArrayList<>();
	private int reader = 0;

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
	public void path(KeyCode event) {
		path = move(event);
		System.out.println(path);
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
			dot();
			(ml.dots()).remove(pos);
		}
		else if (ml.largeDots().contains(pos)) {
			LargeDot();			
			vulnerable = "active";
			
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

	public String getVulnerable() {
		return vulnerable;
	}

	public void setVulnerable(String vulnerable) {
		this.vulnerable = vulnerable;
	}

	public int getLife() {
		return life;
	}

	public Point playerSpawn() {
		return ml.playerSpawn();
	}
	public void post(String playerName) {
		DataBaseConnection.post(score, playerName);
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
		pos = path.get(reader);
		reader++;
	}

	@Override
	public void findPath() {
		// TODO Auto-generated method stub
		
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

}
