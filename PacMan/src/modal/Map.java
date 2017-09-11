package modal;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map.Entry;

public class Map implements Map_IF {
	private HashMap<Point, String> map = new HashMap<>();
	private File folder = new File("Maps");
	private String[] strings;


	public Map(String[] strings) {
		this.strings = strings;
	}

	public HashMap<Point, String> getMap() {
		return map;
	}

	public void setMap(HashMap<Point, String> map) {
		this.map = map;
	}

	public Point getPlayerSpawn() {
		Point spawn = null;

		for (Entry<Point, String> e : map.entrySet()) {
			//System.out.println(e.getKey());
			//System.out.println(e.getValue());
			if (map.get(e.getKey()).equals(strings[4])) {
				spawn = e.getKey();
			}
		}
		return spawn;

	}

	public Point getGhostHouse() {
		Point house = null;
		for (Entry<Point, String> e : map.entrySet()) {
			if (map.get(e.getKey()).equals(strings[5])) {
				house = e.getKey();
			}

		}
		return house;
	}
	public String entry(Point pos){
		return map.get(pos);
	}

	@SuppressWarnings("unchecked")
	public void readMap(String tiedostonNimi) {
		HashMap<Point, String> map1 = new HashMap<>();
		File file = new File(folder + "\\" + tiedostonNimi);
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream dataIn = new ObjectInputStream(fileIn);
			try {
				map1 = (HashMap<Point, String>) dataIn.readObject();
				System.out.println(map1);
				dataIn.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		//System.out.println(getPlayerSpawn());
		//System.out.println(getGhostHouse());
		map.clear();
		map.putAll(map1);

	}

	public File[] allFiles() {
		File[] files = folder.listFiles();
		return files;
	}

}
