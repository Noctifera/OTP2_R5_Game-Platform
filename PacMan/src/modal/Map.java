package modal;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
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
	public ArrayList<Point> getWalls(){
		ArrayList<Point> walls = new ArrayList<>();
		for (Entry<Point, String> e : map.entrySet()) {
			if(map.get(e.getKey()).equals(strings[2])){
				walls.add(e.getKey());
			}
		}


		return walls;
	}

	public ArrayList<Point> getDots(){
		ArrayList<Point> dots = new ArrayList<>();
		for (Entry<Point, String> e : map.entrySet()) {
			if(map.get(e.getKey()).equals(strings[0])){
				dots.add(e.getKey());
			}
		}


		return dots;
	}
	public ArrayList<Point> getLargeDots(){
		ArrayList<Point> dots = new ArrayList<>();
		for (Entry<Point, String> e : map.entrySet()) {
			if(map.get(e.getKey()).equals(strings[1])){
				dots.add(e.getKey());
			}
		}


		return dots;
	}

	public String[] allFiles() {
		File[] files = folder.listFiles();

		String[] fileNames = new String[files.length];

		for (int i = 0; i < files.length; i++) {
			fileNames[i] = files[i].getName();
		}
		for (String s : fileNames) {
			System.out.println(s);
		}


		return fileNames;
	}

}
