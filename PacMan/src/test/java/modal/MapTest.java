package modal;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.BeforeClass;
import org.junit.Test;

import hibernate_PacMan.DataBaseConnection;
import map_PacMan.Map;

public class MapTest {
	private static Map map;
	final static  String[] strings = {"Dot", "LargeDot", "Wall", "Empty","PlayerSpawn", "GhostHouse"};
	@BeforeClass
	public static void start() {
		Point gSize = new Point(720, 480);
		int blSize = 40;
		
		map = new Map(strings,gSize, blSize);
		DataBaseConnection.getAllMapsFromDataBase();
		map.setMap(DataBaseConnection.readOneMap("GhostTestMap"));
		
	}

	/*
	 * Testataan, että ohjelma lukee kartan
	 */
	@Test
	public void readMap() {
		assertTrue(map.getMap() != null);
	}

	/*
	 * Testataan dotit
	 */
	@Test
	public void dot() {
		assertTrue(map.getMap().containsValue(strings[0]));
	}

	/*
	 * Testataan large dotit
	 */
	@Test
	public void Largedot() {
		assertTrue(map.getMap().containsValue(strings[1]));
	}

	/*
	 * Testataan seinät
	 */
	@Test
	public void wall() {
		assertTrue(map.getMap().containsValue(strings[2]));
	}
	
	/*
	 * Testataan tyhjät ruudut
	 */
	@Test
	public void empty() {
		assertTrue(map.getMap().containsValue(strings[3]));
	}
	/*
	 * Testataan pelaajan spawnpoint
	 */
	@Test
	public void playerSpawn() {
		assertTrue(map.getMap().containsValue(strings[4]));
	}
	/*
	 * Testataan ghostien spawnpoint
	 */
	@Test
	public void ghostHouse() {
		assertTrue(map.getMap().containsValue(strings[5]));
	
	}
	

}
