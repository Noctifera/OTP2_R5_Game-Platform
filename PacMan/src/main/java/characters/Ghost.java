package characters;

import java.awt.Point;
import java.util.ArrayList;
import map.MovementLogic;
import pathfinding.PathFinder;

/**
 * Extends PathFinder, implements {@link Ghost_IF}. Instantiates path and
 * handles ghosts colors.
 * 
 * @author kari-antti
 *
 */
public class Ghost implements Character {
	private MovementLogic ml;
	private PathFinder pf;
	private Player player;
	private boolean eaten = false;
	private ArrayList<Point> path = new ArrayList<>();
	private int reader = 0;

	private Point pos = new Point();
	private String ghost;

	public Ghost(MovementLogic ml, Player player, String ghost) {
		pf = new PathFinder(ml);
		this.player = player;
		this.ghost = ghost;
		this.ml = ml;
	}

	public Point getPos() {
		return pos;
	}

	@Override
	public boolean getVulnerable() {
		return player.getVulnerable();
	}

	public String getGhost() {
		return ghost;
	}

	public void setPos(Point pos) {
		if (!this.pos.equals(null)) {
			ml.returnToNormal(this.pos);
		}

		this.pos = pos;
	}

	@Override
	public Point characterSpawn() {

		return ml.ghostHouse();
	}

	@Override
	public void getNextPos() {

		ml.returnToNormal(pos);

		pos = path.get(reader);
		reader++;
		ml.setToMap(pos, getVulnerable() + "," + ghost);
	}

	@Override
	public void findPath() {

		reader = 0;
		path.clear();
		if (!player.getVulnerable()) {

			double rand = Math.random();

			if (rand <= 0.30) {
				Point randpoint = ml.randomPoint();
				path = pf.route(pos, randpoint);

			} else {
				Point point = null;
				if (ml.freespaces().contains(player.getPos())) {
					point = player.getPos();
				} else {
					point = ml.randomPoint();
				}
				path = pf.route(pos, point);

			}
		} else {
			Point randpoint = ml.randomPoint();
			path = pf.route(pos, randpoint);
		}
	}

	@Override
	public int pathlength() {

		return path.size();
	}

	@Override
	public int getReader() {

		return reader;
	}

	@Override
	public boolean isGameEnd() {

		return player.isGameEnd();
	}

	@Override
	public boolean iseaten() {
		// TODO Auto-generated method stub
		return eaten;
	}

	@Override
	public void setEaten(boolean eaten) {
		// TODO Auto-generated method stub
		this.eaten = eaten;
	}

}