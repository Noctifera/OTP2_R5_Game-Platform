package modal;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class FileReader {
	//private final File folder = new File("C:\\Users\\marku\\git\\Game-Platform\\PacMan\\Maps");
		private final File folder = new File("Maps");
		// File folder = new File("/Users/Hanne/git/Game-Platform/PacMan/Maps");
		
		private Map map;
		
		
	
	public FileReader(Map map) {
			this.map = map;
		}



	public HashMap<Point, String> readMapFromFile(String fileName) {
		File file = new File(folder + "//" + fileName);
		HashMap<Point, String> tmp = new HashMap<>();
		try {

			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream dataIn = new ObjectInputStream(fileIn);
			tmp = (HashMap<Point, String>) dataIn.readObject();
			dataIn.close();
			return tmp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public boolean saveMapToFile(String fileName) {
		System.out.println("save: "+fileName);
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

}
