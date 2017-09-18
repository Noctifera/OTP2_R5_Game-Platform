package modal;

import java.awt.Point;
import java.util.ArrayList;

public class Ghost_Thread2 extends Thread {
	Ghost gh;
	ArrayList<Point> list;
	Point target;
	
	public Ghost_Thread2(Ghost gh, ArrayList<Point> list, Point target) {
		this.gh = gh;
		this.list = list;
		this.target = target;
	}

	public void run() {
		
		gh.testPath(target, list);
		
	}

}
