package modal;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileOut {
	private File folder = new File("SomeFiles");

	public FileOut() {

	}

	public void GhostPathToFile(String fileName, ArrayList<Point> path) {
		File file = new File(folder + "/" + fileName + "String.txt");

		try {
			FileWriter fw = new FileWriter(file, true);

			fw.write(path.toString() + "\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void GhostNodeToFile(String fileName, Node current) {
		File file = new File(folder + "/" + fileName + ".txt");
		try {

			FileWriter writer = new FileWriter(file, true);
			writer.write(current.toString() + "\n");
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
