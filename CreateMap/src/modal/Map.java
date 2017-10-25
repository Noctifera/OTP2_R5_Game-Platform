package modal;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Map {
	private HashMap<Point, String> map = new HashMap<Point, String>();
	
	private int x;
	private int y;
	private int tileSize;
	private String[] strings;
	File folder = new File("C:\\Users\\marku\\git\\Game-Platform\\PacMan\\Maps");
	// File folder = new File("/Users/Hanne/git/Game-Platform/PacMan/Maps");

	public Map(int x, int y, int tileSize, String[] strings) {
		this.x = x;
		this.y = y;
		this.tileSize = tileSize;
		this.strings = strings;
	}

	public void initializeMap() {
		for (int i = 0; i < x;) {
			for (int j = 0; j < y;) {
				Point point = new Point(i, j);
				map.put(point, strings[3]);
				j = j + tileSize;
			}
			i = i + tileSize;
		}

	}

	public void addToMap(Point point, String item) {
		map.put(point, item);
	}

	public HashMap<Point, String> getMap() {
		return map;
	}

	public void setMap(HashMap<Point, String> map) {
		this.map = map;
	}

	public void onlyOne(Point point, String item) {
		for (Point p : map.keySet()) {
			if (map.get(p).equals(item)) {
				map.replace(p, strings[3]);
				addToMap(point, item);
			} else {
				addToMap(point, item);
			}
		}
	}

	public boolean mapContains() {
		ArrayList<String> items = new ArrayList<>();
		String mis = "";
		for (int i = 0; i < strings.length; i++) {
			if (map.containsValue(strings[i])) {
				items.add(strings[i]);
			} else {
				mis = mis + strings[i] + ", ";
			}
		}
		if (items.size() == strings.length) {
			return true;
		} else {
			System.out.println("these are missing for it to be saved" + mis);
			return false;
		}
	}

}
