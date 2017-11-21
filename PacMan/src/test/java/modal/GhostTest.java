package modal;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;

import characters.Ghost;
import characters.Player;
import hibernate.DataBaseConnection;
import map.Map;
import map.MovementLogic;
	
public class GhostTest {

	private static MovementLogic ml;
	private static Map map;
	private static Player player;
	private static Point gSize;
	private static int blSize;
	private static Ghost gh;
	
	@BeforeClass
	public static void start() {
		gSize = new Point(720, 480);
		String[] strings = {"Dot", "LargeDot", "Wall", "Empty","PlayerSpawn", "GhostHouse" };
		String[] ghosts = {"Blinky","Speedy","Bashful","Pokey"};
		blSize = 40;
		map = new Map(strings, gSize, blSize);
		ml = new MovementLogic(gSize, blSize, map);
		player = new Player(ml,0);
		gh = new Ghost(ml, player,ghosts[0]);
		
		DataBaseConnection.getAllMapsFromDataBase();
		map.setMap(DataBaseConnection.readOneMap("GhostTestMap"));
		
	}
	
	@Test
	public void testGhostMovement() {
		Point SPoint = new Point(gh.characterSpawn());
		Point TPoint = new Point(280, 240);
		ArrayList<Point> test = gh.route(SPoint, TPoint);
		assertEquals(TPoint, test.get(test.size()-1));			
	}
	
	@Test
	public void testPathSize() {
		Point SPoint = new Point(gh.characterSpawn());
		Point TPoint = new Point(280, 240);
		ArrayList<Point> test = gh.route(SPoint, TPoint);
		assertEquals(12, test.size());
	}
	
	@Test
	public void testGhostMovementLong() {		
		Point SPoint = new Point(gh.characterSpawn());
		Point TPoint = new Point(680, 440);
		ArrayList<Point> test = gh.route(SPoint, TPoint);
		assertEquals(TPoint, test.get(test.size()-1));
	}
	
	@Test
	public void testPathSizeLong() {
		Point SPoint = new Point(gh.characterSpawn());
		Point TPoint = new Point(680, 440);
		ArrayList<Point> test = gh.route(SPoint, TPoint);
		assertEquals(69, test.size());
	}
	
	@Test
	public void testGhostImpossibleMovement() {
		Point SPoint = new Point(gh.characterSpawn());
		Point TPoint = new Point(200, 40);
		ArrayList<Point> test = gh.route(SPoint, TPoint);
		assertEquals(null, test);
	}
	

	
	
	
	
}
