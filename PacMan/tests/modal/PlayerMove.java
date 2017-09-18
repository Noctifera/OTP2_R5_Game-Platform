package modal;

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
	}
	

}
