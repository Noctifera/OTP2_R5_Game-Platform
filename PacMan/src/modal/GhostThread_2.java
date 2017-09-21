package modal;

import java.awt.Point;
import java.util.ArrayList;

public class GhostThread_2 extends Thread {
	private Ghost gh;
	private Point target;
	
	private ArrayList<Point> list;
	
	private int index;

	public GhostThread_2(Ghost gh, Point target, ArrayList<Point> list, int index) {
		this.gh = gh;
		this.target = target;
		this.list = list;
		this.index = index;
		
	}

	private volatile boolean suppress = false;

	public void run() {
		// System.out.println("g2");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (!suppress) {
			try {
			//	gh.testPath(target, list);

				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//gh.removeindex(index);

	}

	public void suppress() {
		suppress = true;
	}

}
