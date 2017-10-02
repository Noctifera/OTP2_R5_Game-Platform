package modal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import com.mysql.fabric.xmlrpc.base.Array;
import com.sun.javafx.scene.paint.GradientUtils.Point;

public class FileIn {
	private File folder = new File("SomeFiles");

	public void ghostPathFromFile(String fileName) {
		File file = new File(folder + "/" + fileName + ".txt");
		
		String line;
		try {
		
		InputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		BufferedReader br = new BufferedReader(ois);
		
		while ((line = br.readLine()) != null) {
			ArrayList<Point> path = new ArrayList<>();
			path.add(line);
		}
		br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
