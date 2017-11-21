package modal;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import characters.Player;
import hibernate.DataBaseConnection;
import javafx.scene.input.KeyCode;
import map.Map;
import map.MovementLogic;
import sounds.Sounds;

public class PlayerMove {
	private static MovementLogic ml;
	private static Map map;
	private static Player player;
	private static Point gSize;
	private static int blSize;
	private static Sounds sounds;
	
	@BeforeClass
	public static void start() {
		gSize = new Point(720, 480);
		String[] strings = {"Dot", "LargeDot", "Wall", "Empty","PlayerSpawn", "GhostHouse"  };
		blSize = 40;
		map = new Map(strings,gSize,blSize);
		ml = new MovementLogic(gSize, blSize, map);
		player = new Player(ml,0);
		DataBaseConnection.getAllMapsFromDataBase();
		map.setMap(DataBaseConnection.readOneMap("PlayerTestMap"));
		
	}
	@Test
	public void moveUp() {
		player.setPos(player.playerSpawn());
		KeyCode up = KeyCode.W;
		 player.path(up);
		 List<Point> list = player.getPath();
		
		assertEquals(new Point(160,120), list.get(list.size()-1));
	}
	@Test
	public void moveLeft() {
		player.setPos(player.playerSpawn());
		KeyCode left = KeyCode.A;
		player.path(left);
		 List<Point> list = player.getPath();
		assertEquals(new Point(80,200), list.get(list.size()-1));
		
	}
	@Test
	public void moveRight() {
		player.setPos(player.playerSpawn());
		KeyCode right = KeyCode.D;
		player.path(right);
		 List<Point> list = player.getPath();
		assertEquals(new Point(280,200), list.get(list.size()-1));
		
	}
	@Test
	public void moveDown() {
		player.setPos(player.playerSpawn());
		KeyCode down = KeyCode.S;
		player.path(down);
		 List<Point> list = player.getPath();
		assertEquals(new Point(160,320),list.get(list.size()-1));
		
	}
	@Test
	public void dontMoveUp() {
		player.setPos(new Point(80, 120));
		KeyCode up = KeyCode.W;
		player.path(up);
		 List<Point> list = player.getPath();
		assertEquals(0,list.size());
		
	}
	@Test
	public void dontMoveDown() {
		player.setPos(new Point(80, 120));
		KeyCode down = KeyCode.S;
		player.path(down);
		 List<Point> list = player.getPath();
		assertEquals(0, list.size());
		
	}
	@Test
	public void dontMoveLeft() {
		player.setPos(new Point(80, 120));
		KeyCode left = KeyCode.A;
		player.path(left);
		 List<Point> list = player.getPath();
		assertEquals(0, list.size());
		
	}
	@Test
	public void dontMoveRight() {
		player.setPos(new Point(80, 120));
		KeyCode right = KeyCode.D;
		player.path(right);
		 List<Point> list = player.getPath();
		assertEquals(0, list.size());
		
	}
	@Test
	public void overleft() {
		player.setPos(new Point(0,0));
		KeyCode left = KeyCode.A;
		player.path(left);
		 List<Point> list = player.getPath();
		assertEquals(new Point(0, 0), list.get(list.size()-1));
	}
	@Test
	public void overRight() {
		Point p = new Point((int)gSize.getX()-blSize,0);
		player.setPos(p);
		KeyCode right = KeyCode.D;
		player.path(right);
		 List<Point> list = player.getPath();
		assertEquals(p, list.get(list.size()-1));
		
	}
	@Test
	public void overTop() {
		Point p = new Point(0,0);
		player.setPos(p);
		KeyCode up = KeyCode.W;
		player.path(up);
		 List<Point> list = player.getPath();
		assertEquals(p, list.get(list.size()-1));
		
	}
	@Test
	public void overBottom() {
		Point p = new Point(0,(int)gSize.getY()-blSize);
		player.setPos(p);
		KeyCode down = KeyCode.S;
		player.path(down);
		 List<Point> list = player.getPath();
		assertEquals(p, list.get(list.size()-1));
		
	}
	

}
