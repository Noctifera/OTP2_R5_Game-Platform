package modal;

import java.awt.Point;
import java.util.List;

import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;

import characters_PacMan.Player;
import hibernate_PacMan.DataBaseConnection;
import javafx.scene.input.KeyCode;
import map_PacMan.Map;
import map_PacMan.MovementLogic;

public class ScoreTest {

	private static MovementLogic ml;
	private static Map map;
	private static Player player;
	private static Point gSize;
	private static int blSize;

	@BeforeClass
	public static void start() {
		gSize = new Point(720, 480);
		String[] strings = { "Dot", "LargeDot", "Wall", "Empty", "PlayerSpawn", "GhostHouse" };
		blSize = 40;
		map = new Map(strings, gSize, blSize);
		ml = new MovementLogic(gSize, blSize, map);
		player = new Player(ml, 0);
		DataBaseConnection.getAllMapsFromDataBase();

	}

	@Test
	public void score() {
		
		map.setMap(DataBaseConnection.readOneMap("PlayerTestMap"));

		player.setPos(player.playerSpawn());
		KeyCode right = KeyCode.S;
		player.path(right);
		List<Point> list = player.getPath();
				
		player.setScore(0);
		for (int i = 0; i < list.size(); i++) {
			player.score(list.get(i));
		}

		assertEquals(30, player.getScore());

	}
	/*
	 * Testataan, että doteista saa 10 pistettä
	 */
	@Test
	public void testDot() {
		map.setMap(DataBaseConnection.readOneMap("PlayerTestMap"));

		player.score(new Point(160, 240));
		assertEquals(10, player.getScore());
	}
	/*
	 * Testataan, että large doteista saa 20 pistettä
	 */
	@Test
	public void testLargeDot() {
		map.setMap(DataBaseConnection.readOneMap("PlayerTestMap"));
		
		player.setScore(0);
		player.score(new Point(160, 280));
		assertEquals(20, player.getScore());
	}
	@Test
	public void postTest() {
		map.setMap(DataBaseConnection.readOneMap("PlayerTestMap"));
		
		player.setScore(100);
		assertEquals(true, player.post("Testplayer"));
		
	}

}
