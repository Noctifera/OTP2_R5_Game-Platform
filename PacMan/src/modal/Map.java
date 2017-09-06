package modal;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class Map implements Map_IF{
	private HashMap<Point, String> map;

	public Map() {
	}

	public HashMap<Point, String> getMap() {
		return map;
	}

	public void setMap(HashMap<Point, String> map) {
		this.map = map;
	}

	@SuppressWarnings("unchecked")
	public void readMap(String tiedostonNimi) {
		try {
			FileInputStream file = new FileInputStream(tiedostonNimi);
			ObjectInputStream dataIn = new ObjectInputStream(file);
			try {
				map = (HashMap<Point, String>) dataIn.readObject();
				System.out.println(map);
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
		}
	}

}
