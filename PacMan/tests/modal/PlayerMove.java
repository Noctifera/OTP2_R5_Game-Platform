package modal;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import javafx.scene.input.KeyCode;
import modal.Map;
import modal.MovementLogic;

public class PlayerMove {
	private static MovementLogic ml;
	private static Map map;
	private static Player player;
	private static Point gSize;
	private static int blSize;
	
	@BeforeClass
	public static void start() {
		System.out.println("before test");
		gSize = new Point(720, 480);
		String[] strings = {"Dot", "LargeDot", "Wall", "Empty","PlayerSpawn", "GhostHouse"  };
		blSize = 40;
		map = new Map(strings,gSize,blSize);
		ml = new MovementLogic(gSize, blSize, map);
		player = new Player(ml, 0);
		
	}
	@Test
	public void moveUp() {
		map.readMap("level1-fixed.txt");
		Point p = new Point(40,120);
		player.setPos(p);
		KeyCode up = KeyCode.W;
		ArrayList<Point> list = player.move(up);
		
		assertEquals(new Point(40,40), list.get(list.size()-1));
	}
	@Test
	public void moveLeft() {
		map.readMap("level1-fixed.txt");
		Point p = new Point(640,400);
		player.setPos(p);
		KeyCode left = KeyCode.A;
		ArrayList<Point> list = player.move(left);
		assertEquals(new Point(40,400), list.get(list.size()-1));
		
	}
	@Test
	public void moveRight() {
		map.readMap("level1-fixed.txt");
		Point p = new Point(40,400);
		player.setPos(p);
		KeyCode right = KeyCode.D;
		ArrayList<Point> list = player.move(right);
		assertEquals(new Point(640,400), list.get(list.size()-1));
		
	}
	@Test
	public void moveDown() {
		map.readMap("level1-fixed.txt");
		Point p = new Point(240,120);
		player.setPos(p);
		KeyCode down = KeyCode.S;
		ArrayList<Point> list = player.move(down);
		assertEquals(new Point(240,320),list.get(list.size()-1));
		
	}
	@Test
	public void dontMoveUp() {
		map.readMap("impossbleMove.txt");
		player.setPos(player.playerSpawn());
		KeyCode up = KeyCode.W;
		ArrayList<Point> list = player.move(up);
		assertEquals(0,list.size());
		
	}
	@Test
	public void dontMoveDown() {
		map.readMap("impossbleMove.txt");
		player.setPos(player.playerSpawn());
		KeyCode down = KeyCode.S;
		ArrayList<Point> list = player.move(down);
		assertEquals(0, list.size());
		
	}
	@Test
	public void dontMoveLeft() {
		map.readMap("impossbleMove.txt");
		player.setPos(map.getPlayerSpawn());
		KeyCode left = KeyCode.A;
		ArrayList<Point> list = player.move(left);
		assertEquals(0, list.size());
		
	}
	@Test
	public void dontMoveRight() {
		map.readMap("impossbleMove.txt");
		player.setPos(map.getPlayerSpawn());
		KeyCode right = KeyCode.D;
		ArrayList<Point> list = player.move(right);
		assertEquals(0, list.size());
		
	}
	@Test
	public void overleft() {
		map.readMap("impossbleMove.txt");
		player.setPos(new Point(0,0));
		KeyCode left = KeyCode.A;
		ArrayList<Point> list = player.move(left);
		assertEquals(new Point(0, 0), list.get(list.size()-1));
	}
	@Test
	public void overRight() {
		map.readMap("impossbleMove.txt");
		Point p = new Point((int)gSize.getX()-blSize,0);
		player.setPos(p);
		KeyCode right = KeyCode.D;
		ArrayList<Point> list = player.move(right);
		assertEquals(p, list.get(list.size()-1));
		
	}
	@Test
	public void overTop() {
		map.readMap("impossbleMove.txt");
		Point p = new Point(0,0);
		player.setPos(p);
		KeyCode up = KeyCode.W;
		ArrayList<Point> list = player.move(up);
		assertEquals(p, list.get(list.size()-1));
		
	}
	@Test
	public void overBottom() {
		map.readMap("impossbleMove.txt");
		Point p = new Point(0,(int)gSize.getY()-blSize);
		
		player.setPos(p);
		KeyCode down = KeyCode.S;
		ArrayList<Point> list = player.move(down);
		assertEquals(p, list.get(list.size()-1));
		
	}
	

}
