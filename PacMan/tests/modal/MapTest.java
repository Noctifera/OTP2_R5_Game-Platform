package modal;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class MapTest {
	private static Map map;
	final static  String[] strings = {"Dot", "LargeDot", "Wall", "Empty","PlayerSpawn", "GhostHouse"};
	@BeforeClass
	public static void start() {
		map = new Map(strings);
		map.readMap("Level1-fixed.txt");
		
	}

	@Test
	public void readMap() {
		assertTrue(map.getMap() != null);
	}
	@Test
	public void dot() {
		assertTrue(map.getMap().containsValue(strings[0]));
	}
	@Test
	public void Largedot() {
		assertTrue(map.getMap().containsValue(strings[1]));
	}
	@Test
	public void wall() {
		assertTrue(map.getMap().containsValue(strings[2]));
	}
	@Test
	public void empty() {
		assertTrue(map.getMap().containsValue(strings[3]));
	}
	@Test
	public void playerSpawn() {
		assertTrue(map.getMap().containsValue(strings[4]));
	}
	@Test
	public void ghostHouse() {
		assertTrue(map.getMap().containsValue(strings[5]));
	
	}
	

}
