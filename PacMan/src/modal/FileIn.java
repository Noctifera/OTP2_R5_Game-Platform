package modal;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class FileIn {
	private File folder = new File("SomeFiles");

	public ArrayList<ArrayList<Point>> ghostPathFromFile(String fileName) {
		File file = new File(folder + "/" + fileName + "String.txt");
		ArrayList<ArrayList<Point>> paths = new ArrayList<>();

		String line;
		try {

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {
				ArrayList<Point> p = new ArrayList<>();
				//System.out.println(line);
				String[] strings = line.split("[^0-9]+");
				//System.out.println("length: "+strings.length);
				int x = 0;
				int y = 0;
				for (int i = 1; i < strings.length; i++) {
					//System.out.println(strings[i]);
					
					
					switch (i % 2) {
					case 0:
						x = Integer.parseInt(strings[i]);
						break;
					case 1:
						y = Integer.parseInt(strings[i]);
						break;
					default:
						System.out.println("virhe");
					}
					if(x > 0 && y > 0) {
						Point point = new Point(x, y);
						//System.out.println("point: "+point);
						p.add(point);
						x = 0;
						y = 0;
					}

					
				}
				
				paths.add(p);

			}
			br.close();
			System.out.println(paths.size());
			System.out.println(paths);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paths;
	}

}
