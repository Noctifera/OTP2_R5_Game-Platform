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
	
	@BeforeClass
	public static void start() {
		System.out.println("before test");
		Point gSize = new Point(720, 480);
		String[] strings = {"Dot", "LargeDot", "Wall", "Empty","PlayerSpawn", "GhostHouse"  };
		int blSize = 40;
		map = new Map(strings);
		ml = new MovementLogic(gSize, blSize, map, strings);
		player = new Player(ml, blSize, strings, 0);
		
	}
	@Test
	public void moveUp() {
		map.readMap("map.txt");
		player.setPos(map.getPlayerSpawn());
		KeyCode up = KeyCode.W;
		player.move(up);
		assertEquals(new Point((int)map.getPlayerSpawn().getX(), (int)map.getPlayerSpawn().getY()-40), player.getPos());
	}
	@Test
	public void moveLeft() {
		map.readMap("map.txt");
		player.setPos(map.getPlayerSpawn());
		KeyCode left = KeyCode.A;
		player.move(left);
		assertEquals(new Point((int)map.getPlayerSpawn().getX()-40, (int)map.getPlayerSpawn().getY()), player.getPos());
		
	}
	@Test
	public void moveRight() {
		map.readMap("map.txt");
		player.setPos(map.getPlayerSpawn());
		KeyCode right = KeyCode.D;
		player.move(right);
		assertEquals(new Point((int)map.getPlayerSpawn().getX()+40, (int)map.getPlayerSpawn().getY()), player.getPos());
		
	}
	@Test
	public void moveDown() {
		map.readMap("map.txt");
		player.setPos(map.getPlayerSpawn());
		KeyCode down = KeyCode.S;
		player.move(down);
		assertEquals(new Point((int)map.getPlayerSpawn().getX(), (int)map.getPlayerSpawn().getY()+40), player.getPos());
		
	}
	@Test
	public void DontMoveUp() {
		map.readMap("impossbleMove.txt");
		player.setPos(map.getPlayerSpawn());
		KeyCode up = KeyCode.W;
		player.move(up);
		assertEquals(map.getPlayerSpawn(), player.getPos());
		
	}
	@Test
	public void DontMoveDown() {
		map.readMap("impossbleMove.txt");
		player.setPos(map.getPlayerSpawn());
		KeyCode down = KeyCode.S;
		player.move(down);
		assertEquals(map.getPlayerSpawn(), player.getPos());
		
	}
	@Test
	public void DontMoveLeft() {
		map.readMap("impossbleMove.txt");
		player.setPos(map.getPlayerSpawn());
		KeyCode left = KeyCode.A;
		player.move(left);
		assertEquals(map.getPlayerSpawn(), player.getPos());
		
	}
	@Test
	public void DontMoveRight() {
		map.readMap("impossbleMove.txt");
		player.setPos(map.getPlayerSpawn());
		KeyCode right = KeyCode.D;
		player.move(right);
		assertEquals(map.getPlayerSpawn(), player.getPos());
		
	}
	

}
