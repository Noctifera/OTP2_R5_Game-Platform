package modal;

import java.awt.Point;

import org.junit.Assert;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;
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
		player = new Player(ml, blSize, 0);
		
	}
	
	@Test
	public void testDot() {
		map.readMap("scoretest.txt");
		player.setPos(map.getPlayerSpawn());
		KeyCode right = KeyCode.D;
		player.move(right);
		System.out.println(player.getScore());
		assertEquals(10, player.getScore());
	}
	
	
	@Test
	public void testLargeDot() {
		map.readMap("scoretest.txt");
		player.setPos(map.getPlayerSpawn());
		KeyCode right = KeyCode.D;
		player.move(right);
		player.move(right);
		player.move(right);
		assertEquals(50, player.getScore());
	}		
	
	
	
	
}
