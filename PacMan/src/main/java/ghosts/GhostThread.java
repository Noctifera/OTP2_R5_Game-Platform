package ghosts;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Extends {@link Thread}, GhostThread runs the ghosts
 * 
 * @author kari-antti
 *
 */
public class GhostThread extends Thread {
	private Ghost gh;
	private ArrayList<Point> path = new ArrayList<>();

	private volatile boolean supress = false;

	private int reader;

	public GhostThread(Ghost gh) {
		this.gh = gh;
	}

	public void run() {
		
		System.out.println(gh.getPos());
		
		path.addAll(gh.insPath());

		while (!supress) {

			while (reader < path.size()) {

				gh.setPos(path.get(reader));
				reader++;
				if (gh.vulnerableStatus() == "deactive") {
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				else if (gh.vulnerableStatus() == "active") {
					try {
						Thread.sleep(350);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			if (reader > 0) {
				path.clear();
				path.addAll(gh.insPath());
				reader = 0;
			}

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	public void supress() {
		supress = true;
	}

	public void returnToHouse() {
		path.clear();
		gh.setPos(gh.ghostHouse());
		reader = 0;

	}

}
