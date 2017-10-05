package modal;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class FileIn {
	private File folder = new File("SomeFiles");

	public ArrayList<ArrayList<Node>> ghostPathFromFile(String fileName) {
		File file = new File(folder + "/" + fileName + "String.txt");
		ArrayList<ArrayList<Node>> paths = new ArrayList<>();

		String line;
		try {

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {
				ArrayList<Node> p = new ArrayList<>();
				//System.out.println(line);
				String[] strings = line.split("[^0-9]+");
				//System.out.println("length: "+strings.length);
				int x = 0;
				int y = 0;
				for (int i = 1; i < strings.length; i++) {
					//System.out.println(strings[i]);
					
					
					switch (i % 2) {
					case 0:
						y = Integer.parseInt(strings[i]);
						break;
					case 1:
						x = Integer.parseInt(strings[i]);
						break;
					default:
						System.out.println("virhe");
					}
					if(x > 0 && y > 0) {
						Node point = new Node(new Point(x, y));
						//System.out.println("point: "+point);
						p.add(point);
						x = 0;
						y = 0;
					}

					
				}
				
				paths.add(p);

			}
			br.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paths;
	}

}
