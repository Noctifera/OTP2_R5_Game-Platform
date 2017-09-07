package modal;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class Map implements Map_IF{
	private HashMap<Point, String> map;
	private File folder = new File("Maps");

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
		File file = new File(folder+"\\"+tiedostonNimi);
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream dataIn = new ObjectInputStream(fileIn);
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
		} catch(NullPointerException e){
			e.printStackTrace();
		}
	}
	public File[] allFiles(){
		File[] files = folder.listFiles();
		return files;
	}

}
