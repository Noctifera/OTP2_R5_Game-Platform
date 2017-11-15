package ghosts;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Extends {@link Thread}, GhostThread runs the ghosts
 * @author kari-antti
 *
 */
public class GhostThread extends Thread {
	private Ghost gh;
	private ArrayList<Point> path = new ArrayList<>();
	//private Node next;

	private volatile boolean supress = false;
	
	private int reader;
	private int wait = 0;

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
		//next = gh.inisPath();
		reader = 0;
		while (!supress) {
			
			
			while (reader < path.size()) {

				gh.setPos(path.get(reader));
				reader++;
				if(gh.vulnerableStatus() == "deactive") {
					try {
						Thread.sleep(250);
						if (wait ==1) house();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				else if (gh.vulnerableStatus() == "active") {
					try {
						Thread.sleep(350);
						if (wait ==1) house();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				}
				
			
			if(reader > 0) {
				reader = 0;
				path.clear();
				path.addAll(gh.insPath());
			}
			
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

	}
	public void house() throws InterruptedException {
		Thread.sleep(3000);
		wait = 0;
		path.addAll(gh.insPath());
	}
	public void supress() {
		supress = true;
	}
	public void returnToHouse() {
		reader = 0;
		path.clear();
		gh.setPos(gh.ghostHouse());
		wait = 1;
	}

}
