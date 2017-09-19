package modal;

import static org.junit.Assert.*;

import java.awt.Point;

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
		map = new Map(strings);
		ml = new MovementLogic(gSize, blSize, map);
		player = new Player(ml, blSize, 0);
		
	}
	@Test
	public void moveUp() {
		map.readMap("map.txt");
		player.setPos(map.getPlayerSpawn());
		KeyCode up = KeyCode.W;
		player.move(up);
		assertEquals(new Point((int)map.getPlayerSpawn().getX(), (int)map.getPlayerSpawn().getY()-blSize), player.getPos());
	}
	@Test
	public void moveLeft() {
		map.readMap("map.txt");
		player.setPos(map.getPlayerSpawn());
		KeyCode left = KeyCode.A;
		player.move(left);
		assertEquals(new Point((int)map.getPlayerSpawn().getX()-blSize, (int)map.getPlayerSpawn().getY()), player.getPos());
		
	}
	@Test
	public void moveRight() {
		map.readMap("map.txt");
		player.setPos(map.getPlayerSpawn());
		KeyCode right = KeyCode.D;
		player.move(right);
		assertEquals(new Point((int)map.getPlayerSpawn().getX()+blSize, (int)map.getPlayerSpawn().getY()), player.getPos());
		
	}
	@Test
	public void moveDown() {
		map.readMap("map.txt");
		player.setPos(map.getPlayerSpawn());
		KeyCode down = KeyCode.S;
		player.move(down);
		assertEquals(new Point((int)map.getPlayerSpawn().getX(), (int)map.getPlayerSpawn().getY()+blSize), player.getPos());
		
	}
	@Test
	public void dontMoveUp() {
		map.readMap("impossbleMove.txt");
		player.setPos(map.getPlayerSpawn());
		KeyCode up = KeyCode.W;
		player.move(up);
		assertEquals(map.getPlayerSpawn(), player.getPos());
		
	}
	@Test
	public void dontMoveDown() {
		map.readMap("impossbleMove.txt");
		player.setPos(map.getPlayerSpawn());
		KeyCode down = KeyCode.S;
		player.move(down);
		assertEquals(map.getPlayerSpawn(), player.getPos());
		
	}
	@Test
	public void dontMoveLeft() {
		map.readMap("impossbleMove.txt");
		player.setPos(map.getPlayerSpawn());
		KeyCode left = KeyCode.A;
		player.move(left);
		assertEquals(map.getPlayerSpawn(), player.getPos());
		
	}
	@Test
	public void dontMoveRight() {
		map.readMap("impossbleMove.txt");
		player.setPos(map.getPlayerSpawn());
		KeyCode right = KeyCode.D;
		player.move(right);
		assertEquals(map.getPlayerSpawn(), player.getPos());
		
	}
	@Test
	public void overleft() {
		map.readMap("impossbleMove.txt");
		player.setPos(new Point(0,0));
		KeyCode left = KeyCode.A;
		player.move(left);
		assertEquals(new Point((int)gSize.getX()-blSize, 0), player.getPos());
	}
	@Test
	public void overRight() {
		map.readMap("impossbleMove.txt");
		player.setPos(new Point((int)gSize.getX()-blSize,0));
		KeyCode right = KeyCode.D;
		player.move(right);
		assertEquals(new Point(0,0), player.getPos());
		
	}
	@Test
	public void overTop() {
		map.readMap("impossbleMove.txt");
		player.setPos(new Point(0,0));
		KeyCode up = KeyCode.W;
		player.move(up);
		assertEquals(new Point(0, (int)gSize.getY()-blSize), player.getPos());
		
	}
	@Test
	public void overBottom() {
		map.readMap("impossbleMove.txt");
		player.setPos(new Point(0,(int)gSize.getY()-blSize));
		KeyCode down = KeyCode.S;
		player.move(down);
		assertEquals(new Point(0,0), player.getPos());
		
	}
	

}
