package modal;

import java.awt.Point;
import java.util.ArrayList;

public class GhostThread extends Thread {
	private Ghost gh;
	private MovementLogic ml;
	private ArrayList<Point> path = new ArrayList<Point>();

	private volatile boolean supress = false;

	public GhostThread(Ghost gh, MovementLogic ml) {
		this.gh = gh;
		this.ml = ml;
	}

	public void run() {
		gh.setPos(ml.ghostHouse());
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//gh.runAstar();
		path.addAll(gh.insPath());
		int reader = 0;
		while (!supress) {
			
			while (reader < path.size()) {

				gh.setPos(path.get(reader));
				reader++;

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

}
