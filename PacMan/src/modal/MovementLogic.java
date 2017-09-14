package modal;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class MovementLogic implements MovementLogic_IF {
	private Point gSize;
	private Map map;
	private int blSize;
	private String[] strings;

	public MovementLogic(Point gSize, int blSize, Map map, String[] strings) {
		this.gSize = gSize;
		this.blSize = blSize;
		this.map = map;
		this.strings = strings;
	}

	public Point ghostHouse() {
		return map.getGhostHouse();
	}

	public Point playerSpawn() {
		return map.getPlayerSpawn();
	}

	public Point yli(Point newpos) {
		Point piste = newpos;
		if (newpos.getX() >= gSize.getX()) {
			piste.setLocation(0, newpos.getY());
		} // oikea reuna
		if (newpos.getX() < 0) {
			piste.setLocation(gSize.getX() - blSize, newpos.getY());
		} // vasen reuna
		if (newpos.getY() >= gSize.getY()) {
			piste.setLocation(newpos.getX(), 0);
		} // alareuna
		if (newpos.getY() < 0) {
			piste.setLocation(newpos.getX(), gSize.getY() - blSize);
		} // yläreuna
		return piste;
	}

	public boolean avoidWall(Point pos) {
		// TODO Auto-generated method stub
		if (!map.getMap().get(pos).equals(strings[2])) {
			// System.out.println("ml.pm: "+pos);
			// System.out.println("ml.pm: "+map.getMap().get(pos));
			return true;
		} else {
			return false;
		}
		// System.out.println(onko);
	}

	public ArrayList<Point> freeSpaces() {
		ArrayList<Point> points = new ArrayList<>();
		for (Entry<Point, String> e : map.getMap().entrySet()) {
			if (!map.getMap().get(e.getKey()).equals(strings[2])) {
				points.add(e.getKey());
			}
		}
		return points;
	}

	public String score(Point pos) {
		String apu = strings[3];
		if (map.getMap().get(pos).equals(strings[0])) {
			map.getMap().replace(pos, strings[3]);
			apu = strings[0];

		}if (map.getMap().get(pos).equals(strings[1])) {
			map.getMap().replace(pos, strings[3]);
			apu = strings[1];
		}
		System.out.println(apu);
		return apu;
	}
}
