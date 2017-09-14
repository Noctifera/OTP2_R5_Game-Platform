package modal;

public class GhostThread extends Thread {
	Ghost gh;
	MovementLogic ml;

	private volatile boolean supress = false;

	public GhostThread(Ghost gh, MovementLogic ml) {
		this.gh = gh;
		this.ml = ml;
	}

	public void run() {
		while (!supress) {
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
