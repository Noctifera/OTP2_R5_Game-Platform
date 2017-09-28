package modal;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import modal.Map;
import modal.MovementLogic;
	
public class ScoreTest {

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
		map = new Map(strings, gSize, blSize);
		ml = new MovementLogic(gSize, blSize, map);
		player = new Player(ml, 0);
		
	}
	@Test
	public void score() {
		map.readMap("scoretest.txt");
		player.setScore(0);
		player.setPos(player.playerSpawn());
		map.setLargeDots();
		map.setDots();
		KeyCode right = KeyCode.D;
		ArrayList<Point> list = player.move(right);
		
		for(int i = 0; i<list.size(); i++) {
			player.score(list.get(i));
		}

		assertEquals(50, player.getScore());
		
		
		
		
	}
	
	@Test
	public void testDot() {
		map.readMap("scoretest.txt");
		player.setScore(0);
		map.setDots();
		player.score(new Point(160,160));
		assertEquals(10, player.getScore());
	}
	
	
	@Test
	public void testLargeDot() {
		map.readMap("scoretest.txt");
		player.setScore(0);
		map.setLargeDots();
		player.score(new Point(200,160));
		assertEquals(20, player.getScore());
	}		
	
	
	
	
}
