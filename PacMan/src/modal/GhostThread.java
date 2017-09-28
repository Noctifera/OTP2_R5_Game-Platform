package modal;

import java.awt.Point;
import java.util.ArrayList;

public class GhostThread extends Thread {
	private Ghost gh;
	private ArrayList<Point> path = new ArrayList<Point>();

	private volatile boolean supress = false;
	
	private int reader;

	public GhostThread(Ghost gh) {
		this.gh = gh;
	}

	public void run() {
		gh.setPos(gh.ghostHouse());
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		path.addAll(gh.insPath());
		reader = 0;
		while (!supress) {
			
			while (reader < path.size()) {

				gh.setPos(path.get(reader));
				reader++;

				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if(reader > 0) {
				reader = 0;
				path.clear();
				path.addAll(gh.insPath());
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

	}
	public void supress() {
		supress = true;
	}
	public void returnToHouse() {
		reader = 0;
		path.clear();
		path.addAll(gh.path(gh.getPos(), gh.ghostHouse()));
	}

}
