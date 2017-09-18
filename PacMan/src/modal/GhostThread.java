package modal;

import java.awt.Point;
import java.util.ArrayList;

public class GhostThread extends Thread {
	private Ghost gh;
	private MovementLogic ml;

	private volatile boolean supress = false;

	public GhostThread(Ghost gh, MovementLogic ml) {
		this.gh = gh;
		this.ml = ml;
	}

	public void run() {
		boolean apu = false;
		while (!supress) {
			//if(path)
			
			
			
			try {
				gh.update();
				
			} catch (NullPointerException e) {
				gh.setPos(ml.ghostHouse());
			}

			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
