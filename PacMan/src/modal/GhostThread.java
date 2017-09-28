package modal;

import java.awt.Point;
import java.util.ArrayList;

public class GhostThread extends Thread {
	private Ghost gh;
	private ArrayList<Point> path = new ArrayList<Point>();

	private volatile boolean supress = false;

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
		while (!supress) {
			path.addAll(gh.insPath());
			int reader = 0;
			
			while (reader < path.size()) {

				gh.setPos(path.get(reader));
				reader++;

				try {
					Thread.sleep(500);
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

}
