package characters;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import map.MovementLogic;
import pathfinding.PathFinder;

/**
 * Extends PathFinder, implements {@link Ghost_IF}. Instantiates path and
 * handles ghosts colors.
 * 
 * @author kari-antti
 *
 */
public class Ghost extends PathFinder implements Character {
	private MovementLogic ml;
	private Player player;
	private ArrayList<Point> path = new ArrayList<>();
	private int reader = 0;

	private Point pos;
	private String ghost;

	public Ghost(MovementLogic ml, Player player, String ghost) {
		super(ml);
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
		this.pos = pos;
	}

	@Override
	public boolean eaten() {
		if(getVulnerable()) {
			System.out.println("player: "+player.getPos()+" Ghost: "+ghost+": "+pos);
			if(player.getPos().equals(pos)) {
				System.out.println("true");
				return true;
		}else {
			return false;
		}
		}else {
			return false;
		}
		
	}

	@Override
	public Point characterSpawn() {
		// TODO Auto-generated method stub
		return ml.ghostHouse();
	}

	@Override
	public void getNextPos() {
		// TODO Auto-generated method stub
		if(pos != characterSpawn()) {
			ml.returnToNormal(pos);
		}
		
		pos = path.get(reader);
		reader++;
		ml.setToMap(pos, getVulnerable()+","+ghost);
	}

	@Override
	public void findPath() {
		// TODO Auto-generated method stub
		reader = 0;
		path.clear();
		if (!player.getVulnerable()) {

			double rand = Math.random();

			if (rand <= 0.30) {
				Point randpoint = ml.randomPoint();
				path = route(pos, randpoint);
				//System.out.println(path);
				
			} else {
				Point point = null;
				if(ml.freespaces().contains(player.getPos())) {
					point = player.getPos();
				}else {
					point = ml.randomPoint();
				}
				path = route(pos, point);
				//System.out.println(path);

			}
		} else {
			Point randpoint = ml.randomPoint();
			path =  route(pos, randpoint);
		}
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