package modal;

import java.awt.Point;
import java.util.ArrayList;

public class MovementLogic implements MovementLogic_IF {
	private Point gSize;
	private Map map;
	private int blSize;

	public MovementLogic(Point gSize, int blSize, Map map) {
		this.gSize = gSize;
		this.blSize = blSize;
		this.map = map;
	}

	public Point ghostHouse() {
		return map.getGhostHouse();
	}

	public Point playerSpawn() {
		return map.getPlayerSpawn();
	}
	public Point up(Point point) {
		return new Point((int) point.getX(), (int) point.getY() - blSize);
	}

	public Point down(Point point) {
		return new Point((int) point.getX(), (int) point.getY() + blSize);
	}

	public Point left(Point point) {
		return new Point((int) point.getX() - blSize, (int) point.getY());
	}

	public Point right(Point point) {
		return new Point((int) point.getX() + blSize, (int) point.getY());

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
	
	public ArrayList<Point> dots(){
		return map.getDots();
	}
	public ArrayList<Point> largeDots(){
		return map.getLargeDots();
	}
	public ArrayList<Point> walls(){
		return map.getWalls();
	}
	public ArrayList<Point> freespaces(){
		return map.freeSpaces();
	}
}
