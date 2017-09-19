package modal;

import java.awt.Point;
import java.util.ArrayList;

public class GhostThread_2 extends Thread {
	private Ghost gh;
	private Point target;
	private ArrayList<Point> list;
	
	
	public GhostThread_2(Ghost gh, Point target, ArrayList<Point> list) {
		this.gh = gh;
		this.target = target;
		this.list = list;
	}
	private volatile boolean suppress = false;
	
	public void run() {
	//	System.out.println("g2");
		if(!suppress) {
			gh.testPath(target, list);
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void suppress() {
		suppress = true;
	}
	

}
