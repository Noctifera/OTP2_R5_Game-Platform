package modal;

import java.awt.Point;
import java.util.HashMap;

public class Map {
	private HashMap<Point,String> map = new HashMap<Point,String>();

	public void addToMap(Point point, String item){
		map.put(point, item);
	}

}
