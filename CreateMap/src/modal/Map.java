package modal;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Map {
	private HashMap<Point,String> map = new HashMap<Point,String>();
	private int x;
	private int y;
	private int tileSize;
	private String[] strings;

	public Map(int x, int y, int tileSize, String[] strings){
		this.x = x;
		this.y = y;
		this.tileSize = tileSize;
		this.strings = strings;

	}
	public void initializeMap(){
		for(int i = 0; i<x;){
			for(int j = 0; j<y;){
				Point point = new Point(i,j);
				map.put(point, strings[3]);
				j = j +tileSize;
			}
			i = i+ tileSize;
		}

	}
	public void addToMap(Point point, String item){
		map.put(point, item);
	}

	public void SaveMapToFile(String fileName){

		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream dataOut = new ObjectOutputStream(fileOut);
			dataOut.writeObject(map);
			dataOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@SuppressWarnings("unchecked")
	public void GetMapFromFile(String fileName){
		HashMap<Point,String> map1 = new HashMap<Point,String>();
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			map1 = (HashMap<Point, String>) objectIn.readObject();
			objectIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.clear();
		map.putAll(map1);

	}

}
