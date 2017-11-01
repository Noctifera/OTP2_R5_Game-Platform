package modal;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileReader {
	// private final File folder = new
	// File("C:\\Users\\marku\\git\\Game-Platform\\PacMan\\Maps");
	private final File folder = new File("Maps");
	// File folder = new File("/Users/Hanne/git/Game-Platform/PacMan/Maps");
	private List<MapsTable> mapList = null;
	private Map map;

	public FileReader(Map map) {
		this.map = map;
	}

	public void getAllMapsFromFile() {
		mapList = new ArrayList<>();
		File[] files = folder.listFiles();
		for(File f: files) {
			mapList.add(new MapsTable(f.getName(),readMapFromFile(f)));
		}
	}

	private HashMap<Point, String> readMapFromFile(File fileName) {

		HashMap<Point, String> tmp = new HashMap<>();
		try {

			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream dataIn = new ObjectInputStream(fileIn);
			tmp = (HashMap<Point, String>) dataIn.readObject();
			dataIn.close();
			return tmp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	public HashMap<Point, String> getMap(String mapName){
		for (MapsTable m : mapList) {
			if (m.getMapName().equals(mapName)) {
				System.out.println(m.getMapData());
				return m.getMapData();
			}
		}
		return null;
		
	}

	public boolean saveMapToFile(String fileName) {
		if (!fileName.contains("txt")) {
			fileName = fileName + ".txt";
		}
		File file = new File(folder + "//" + fileName);
		ObjectOutputStream obj = null;

		try {
			obj = new ObjectOutputStream(new FileOutputStream(file));
			obj.writeObject(map.getMap());
			obj.flush();
			obj.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<String> GetMapNamesFromFile() {
		List<String> list = new ArrayList<>();
		for (MapsTable mt : mapList) {
			list.add(mt.getMapName());
		}
		return list;
	}

	public List<MapsTable> getMapList() {
		return mapList;
	}

	public void setMapList(List<MapsTable> mapList) {
		this.mapList = mapList;
	}

}
